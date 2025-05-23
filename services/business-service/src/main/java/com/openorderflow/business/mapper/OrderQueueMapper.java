package com.openorderflow.business.mapper;

import com.openorderflow.business.entity.OrderQueue;
import com.openorderflow.business.entity.OrderQueueItem;
import com.openorderflow.common.dto.business.OrderQueueResponseDto;
import com.openorderflow.common.dto.inventory.BusinessItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderQueueMapper {

    OrderQueueResponseDto toDto(OrderQueue entity);

    @Mapping(target = "itemId", source = "item.itemId")
    OrderQueueResponseDto.OrderQueueItemDto toItemDto(OrderQueueItem item);

    List<OrderQueueResponseDto.OrderQueueItemDto> toItemDtoList(List<OrderQueueItem> items);

    BusinessItemDto toEntity(OrderQueueItem item);
    List<BusinessItemDto> toEntity(List<OrderQueueItem> items);
}
