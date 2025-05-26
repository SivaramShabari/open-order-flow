package com.openorderflow.inventory.repository;

import com.openorderflow.inventory.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, UUID> {
    List<InventoryItem> findByInventoryId(UUID inventoryId);

    Optional<InventoryItem> findByInventoryIdAndBusinessItemId(UUID inventoryId, UUID businessItemId);
}