package com.openorderflow.order.service;

import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsRejectedEventV1;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidatedEventV1;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidationRequestV1;
import com.openorderflow.common.kafka.events.v1.order.OrderPlacedEventV1;
import com.openorderflow.common.kafka.events.v1.order.RequestOrderEventV1;
import com.openorderflow.order.entity.Order;
import com.openorderflow.order.entity.OrderEventHistory;
import com.openorderflow.order.entity.OrderItem;
import com.openorderflow.order.entity.OrderStatusEnum;
import com.openorderflow.order.kafka.producer.OrderProducer;
import com.openorderflow.order.mapper.OrderMapper;
import com.openorderflow.order.repository.OrderEventHistoryRepository;
import com.openorderflow.order.repository.OrderItemRepository;
import com.openorderflow.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderEventHistoryRepository eventHistoryRepository;

    private final OrderMapper orderMapper;

    private final OrderProducer kafkaProducer;

    // === 1. Handle Order Creation Requested ===
    public void handleOrderCreationRequested(RequestOrderEventV1 event) {
        log.info("Received order.creation.requested for customer: {}", event.getCustomerId());

        // Persist partial order
        Order order = orderMapper.toEntityFromRequestEvent(event);
        order.setOrderStatus(OrderStatusEnum.CREATION_REQUESTED);
        order = orderRepository.save(order);

        // Persist order items
        List<OrderItem> items = orderMapper.toOrderItemsFromRequest(event.getItems());
        Order finalOrder = order;
        items.forEach(item -> item.setOrder(finalOrder));
        orderItemRepository.saveAll(items);

        // Emit inventory.validation.requested.v1
        List<OrderItemsValidationRequestV1.OrderItem> validationItems = event.getItems().stream()
                .map(i -> OrderItemsValidationRequestV1.OrderItem.builder()
                        .businessItemId(i.getBusinessItemId())
                        .quantity(i.getQuantity())
                        .build())
                .toList();

        OrderItemsValidationRequestV1 validationRequest = OrderItemsValidationRequestV1.builder()
                .orderId(order.getOrderId())
                .businessOutletId(event.getBusinessOutletId())
                .items(validationItems)
                .build();

        kafkaProducer.sendInventoryValidationRequested(validationRequest);
        log.info("Emitted inventory.validation.requested for order {}", order.getOrderId());

        saveEvent(order, "CREATION_REQUESTED", "Order received and validation triggered.");
    }

    // === 2. Handle Inventory Validated ===
    public void handleInventoryValidated(OrderItemsValidatedEventV1 event) {
        log.info("Inventory validated for order: {}", event.getOrderId());

        Order order = getOrderOrThrow(event.getOrderId());
        order.setOrderStatus(OrderStatusEnum.INVENTORY_VALIDATED);
        order.setUpdatedAt(Instant.now());
        orderRepository.save(order);

        // Emit order.placed.v1
        List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
        OrderPlacedEventV1 eventPayload = orderMapper.toOrderPlacedEvent(order);
        eventPayload.setItems(orderMapper.toOrderPlacedItemEvents(orderItems));
        eventPayload.setPlacedAt(Instant.now());

        kafkaProducer.sendOrderPlaced(eventPayload);
        log.info("Emitted order.placed for order {}", order.getOrderId());

        saveEvent(order, "INVENTORY_VALIDATED", "Order marked as placed and emitted.");
    }

    // === 3. Handle Inventory Invalidated ===
    public void handleInventoryInvalidated(OrderItemsRejectedEventV1 event) {
        log.warn("Inventory invalidated for order: {}", event.getOrderId());

        Order order = getOrderOrThrow(event.getOrderId());
        order.setOrderStatus(OrderStatusEnum.INVENTORY_REJECTED);
        order.setUpdatedAt(Instant.now());
        orderRepository.save(order);

        saveEvent(order, "INVENTORY_REJECTED", event.getReason());
        // TODO: emit `order.failed` or notify CustomerService
    }

    // === Util ===
    private Order getOrderOrThrow(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + orderId));
    }

    private void saveEvent(Order order, String statusLabel, String comment) {
        OrderEventHistory history = OrderEventHistory.builder()
                .order(order)
                .timestamp(Instant.now())
                .updatedBy(OrderEventHistory.OrderUpdationPersonaEnum.SYSTEM)
                .comments(comment)
                .statusId(UUID.nameUUIDFromBytes(statusLabel.getBytes()))
                .build();
        eventHistoryRepository.save(history);
    }
}
