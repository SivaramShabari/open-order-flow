package com.openorderflow.common.dto.inventory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryRequest {
    @NotNull
    private UUID businessOutletId;

    @NotNull
    private String name;

    @NotBlank
    private String locationName;

    @NotBlank
    private String inventoryType;

    @NotNull
    private UUID createdBy;
}
