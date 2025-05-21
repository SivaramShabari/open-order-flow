package com.openorderflow.inventory.repository;

import com.openorderflow.inventory.entity.ItemCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemCatalogRepository extends JpaRepository<ItemCatalog, UUID> {
}
