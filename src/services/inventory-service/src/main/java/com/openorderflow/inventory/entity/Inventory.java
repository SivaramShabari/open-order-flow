package com.openorderflow.inventory.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
    private UUID id;

    @Column(name = "business_id", nullable = false)
    private UUID businessId;

    @Column(name = "business_outlet_id", nullable = false)
    private UUID businessOutletId;

    @Column(nullable = false)
    private String name;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @Column(name = "inventory_type", nullable = false)
    private String inventoryType = "DEFAULT";


    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    @PreUpdate
    protected void updateTimestamp() {
        this.updatedAt = Instant.now();
    }

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<InventoryItem> inventoryItems = new ArrayList<>();
}
