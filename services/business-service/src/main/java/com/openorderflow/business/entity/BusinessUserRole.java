package com.openorderflow.business.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUserRole {
    @OneToOne
    @JoinColumn(name = "business_admin_id", nullable = false, foreignKey = @ForeignKey(name = "business_role_admin_profile"))
    BusinessUserProfile businessUserProfile;

    @Enumerated
    @Column(name = "role", nullable = false)
    BusinessAdminRoleEnum businessAdminRole;

    @Column(name ="created_at")
    private Instant createdAt;

    @OneToOne(mappedBy = "id")
    @JoinColumn(name = "assigned_by", nullable = false)
    private BusinessUserProfile assignedBy;

    public enum  BusinessAdminRoleEnum {
        OWNER,
        ADMIN,
        INVENTORY_MANAGER,
        ORDER_MANAGER,
        MENU_MANAGER,
    }
}
