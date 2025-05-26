package com.openorderflow.common.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ItemCategoryDto {
    private UUID id;
    private String name;
    private String description;
    private String unit;
    private String itemType;
    private boolean isActive;
    private String category;
}
