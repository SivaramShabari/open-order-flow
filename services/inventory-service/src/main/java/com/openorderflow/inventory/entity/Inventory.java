package com.openorderflow.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "inventory")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue
    @Column(name = "inventory_id")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "business_id", nullable = false)
    private UUID businessId;

    @Column(name = "business_outlet_id", nullable = false)
    private UUID businessOutletId;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @Column(name = "inventory_type", nullable = false)
    private String inventoryType = "DEFAULT";

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    @PreUpdate
    protected void updateTimestamp() {
        this.updatedAt = Instant.now();
    }
}
