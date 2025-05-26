package com.openorderflow.business.mapper;

import com.openorderflow.business.entity.OrderQueue;
import com.openorderflow.business.entity.OrderQueueItem;
import com.openorderflow.common.dto.business.OrderQueueResponseDto;
import com.openorderflow.common.dto.inventory.BusinessItemDto;
import com.openorderflow.common.kafka.events.v1.inventory.OrderItemsValidatedEventV1;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderQueueMapper {

    OrderQueueResponseDto toDto(OrderQueue entity);

    OrderQueueResponseDto.OrderQueueItemDto toItemDto(OrderQueueItem item);

    List<OrderQueueResponseDto.OrderQueueItemDto> toItemDtoList(List<OrderQueueItem> items);

    BusinessItemDto toDto(OrderQueueItem item);

    OrderQueueItem toEntity(BusinessItemDto dto);

    List<BusinessItemDto> toEntityFromDto(List<OrderQueueItem> items);

    List<OrderQueueItem> toEntityFromEvent(List<OrderItemsValidatedEventV1.OrderQueueItem> items);

}
