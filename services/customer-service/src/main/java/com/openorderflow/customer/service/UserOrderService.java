package com.openorderflow.customer.service;

import com.openorderflow.common.auth.util.CurrentUserContext;
import com.openorderflow.common.dto.order.CustomerOrderCreationRequest;
import com.openorderflow.common.kafka.events.v1.order.RequestOrderEventV1;
import com.openorderflow.customer.kafka.OrderEventProducer;
import com.openorderflow.customer.mapper.OrderRequestMapper;
import com.openorderflow.customer.repository.CustomerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserOrderService {
    private final OrderRequestMapper orderRequestMapper;
    private final OrderEventProducer orderEventProducer;
    private final CustomerProfileRepository customerProfileRepository;

    public void requestOrderCreation(CustomerOrderCreationRequest customerOrderCreationRequest) throws AccountNotFoundException {
        RequestOrderEventV1 reqOrderEvent = orderRequestMapper.toEvent(customerOrderCreationRequest);
        var customerProfile = customerProfileRepository.findById(UUID.fromString(CurrentUserContext.userId()));
        if (customerProfile.isEmpty())
            throw new AccountNotFoundException("User not found in profile");
        // TODO add redis API rate limiter for customer order limits, for ensuring idempotency
        var enrichedEvent = reqOrderEvent.toBuilder()
                .orderId(UUID.randomUUID())
                .customerId(customerProfile.get().getId())
                .customerName(customerProfile.get().getName())
                .customerEmail(customerProfile.get().getEmail())
                .customerPhone(customerProfile.get().getPhone())
                .build();

        orderEventProducer.sendOrderPlacedEvent(enrichedEvent);
    }

}
