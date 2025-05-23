package com.openorderflow.inventory.mapper;

import com.openorderflow.common.dto.inventory.*;
import com.openorderflow.inventory.entity.BusinessItem;
import com.openorderflow.inventory.entity.Inventory;
import com.openorderflow.inventory.entity.InventoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    Inventory toEntity(InventoryRequest request);
    InventoryDto toResponse(Inventory inventory);

    BusinessItem toEntity(AddBusinessItemRequest request);
    BusinessItemDto toResponse(BusinessItem entity);

    @Mapping(target = "inventory.id", source = "inventoryId")
    @Mapping(target = "businessItem.id", source = "businessItemId")
    InventoryItem toEntity(AddInventoryItemRequest request);

    @Mapping(source = "inventory.id", target = "inventoryId")
    @Mapping(source = "businessItem.id", target = "businessItemId")
    InventoryItemDto toResponse(InventoryItem entity);
}

