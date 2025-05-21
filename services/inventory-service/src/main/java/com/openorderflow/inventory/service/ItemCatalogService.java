package com.openorderflow.inventory.service;

import com.openorderflow.inventory.entity.ItemCatalog;
import com.openorderflow.inventory.repository.ItemCatalogRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemCatalogService {

    private final ItemCatalogRepository itemCatalogRepository;

    public ItemCatalog create(ItemCatalog item) {
        return itemCatalogRepository.save(item);
    }

    public ItemCatalog update(UUID id, ItemCatalog updated) {
        ItemCatalog existing = itemCatalogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ItemCatalog not found"));

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setUnit(updated.getUnit());
        existing.setItemType(updated.getItemType());
        existing.setSubcategory(updated.getSubcategory());

        return itemCatalogRepository.save(existing);
    }

    public ItemCatalog getById(UUID id) {
        return itemCatalogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ItemCatalog not found"));
    }

    public List<ItemCatalog> getAll() {
        return itemCatalogRepository.findAll();
    }

    public void delete(UUID id) {
        itemCatalogRepository.deleteById(id);
    }
}
