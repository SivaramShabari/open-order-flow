package com.openorderflow.inventory.repository;

import com.openorderflow.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    Inventory findByBusinessOutletId(UUID businessOutletId);
    //Inventory findByBusinessOutletIdWithItems(UUID businessOutletId);
    List<Inventory> findByBusinessId(UUID businessId);
}
