package com.openorderflow.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "item_catalog")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemCatalog {

    @Id
    @GeneratedValue
    @Column(name = "item_catalog_id")
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String unit;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "is_active")
    private boolean isActive;

    private String subcategory;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
