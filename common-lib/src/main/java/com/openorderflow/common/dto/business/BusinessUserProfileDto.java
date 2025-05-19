package com.openorderflow.common.dto.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserProfileDto {

    private UUID id;
    private String phone;
    private String name;
    private String email;
    private boolean isOwner;
    private boolean canUpdateInventory;
    private boolean canApproveOrders;

    private List<RoleDto> roles;
    private List<UUID> createdBusinessIds;
    private List<UUID> updatedBusinessIds;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoleDto {
        private UUID roleId;
        private String roleName;
    }
}