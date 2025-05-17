package com.openorderflow.business.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUserProfile {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "is_owner", nullable = false)
    private boolean isOwner;

    @Column(name = "can_update_inventory", nullable = false)
    private boolean canUpdateInventory;

    @Column(name = "can_approve_orders", nullable = false)
    private boolean canApproveOrders;

    @OneToMany(mappedBy = "businessAdminProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusinessUserRole> roles;

    @OneToMany(mappedBy = "createdBy")
    private List<Business> createdBusinesses;

    @OneToMany(mappedBy = "updatedBy")
    private List<Business> updatedBusinesses;
}
