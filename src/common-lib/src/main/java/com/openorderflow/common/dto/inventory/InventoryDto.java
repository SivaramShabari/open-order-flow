package com.openorderflow.common.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDto {
    private UUID id;
    private UUID businessOutletId;
    private String locationName;
    private String inventoryType;
    private UUID createdBy;
    private Instant updatedAt;
}
