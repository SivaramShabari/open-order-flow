package com.openorderflow.business.service;

import com.openorderflow.business.entity.BusinessOutlet;
import com.openorderflow.business.entity.BusinessUserProfile;
import com.openorderflow.business.entity.OrderQueue;
import com.openorderflow.business.mapper.OrderQueueMapper;
import com.openorderflow.business.repository.BusinessUserProfileRepository;
import com.openorderflow.business.repository.OrderQueueRepository;
import com.openorderflow.common.auth.exception.UnauthorizedException;
import com.openorderflow.common.auth.util.CurrentUserContext;
import com.openorderflow.common.dto.business.OrderQueueActionRequestDto;
import com.openorderflow.common.dto.business.OrderQueueResponseDto;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidatedEventV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BusinessOrderService {
    private final OrderQueueRepository orderQueueRepository;
    private final BusinessUserProfileRepository businessUserProfileRepository;
    private final OrderQueueMapper orderQueueMapper;

    public List<OrderQueueResponseDto> getPendingOrders(UUID businessOutletId) throws UnauthorizedException {
        validateUser(businessOutletId);
        var orders = orderQueueRepository.findByBusinessOutletIdAndStatus(businessOutletId, OrderQueue.OrderBusinessStatusEnum.RECEIVED);
        return orders.stream().map(orderQueueMapper::toDto).toList();
    }

    public void orderQueueAction(OrderQueueActionRequestDto requestDto)  throws UnauthorizedException, Exception {
        validateUser(requestDto.getBusinessOutletId());
        if (requestDto.getAction() == OrderQueueActionRequestDto.Action.REJECTED && requestDto.getRejectionReason().isBlank())
            throw new Exception("Cannot reject order without reason");

        var order = orderQueueRepository.findById(requestDto.getOrderQueueId()).get();
        var status = OrderQueue.OrderBusinessStatusEnum.valueOf(requestDto.getAction().toString());
        order.setStatus(status);
        // TODO Future: Do create order queue change history table to track who approved/rejected
        orderQueueRepository.save(order);
    }

    public void addToOrderQueue(OrderItemsValidatedEventV1 event) {
        var orderQueue = new OrderQueue().toBuilder()
                .orderId(event.getOrderId())
                .businessOutlet(BusinessOutlet.builder().id(event.getBusinessId()).build())

    }

    private void validateUser(UUID businessOutletId) throws UnauthorizedException {
        var authenticatedUser = CurrentUserContext.get();
        var user = businessUserProfileRepository.findById(UUID.fromString(authenticatedUser.userId())).get();
        if (user.getBusinessOutlet().getId() != businessOutletId)
            throw new UnauthorizedException("Business user is not authorized to access this info");

        if (user.getRole() == BusinessUserProfile.BusinessAdminRoleEnum.MENU_MANAGER ||
                user.getRole() == BusinessUserProfile.BusinessAdminRoleEnum.INVENTORY_MANAGER)
            throw new UnauthorizedException("Business user is not authorized to access this info");
    }
}
