package com.openorderflow.inventory.service;

import com.openorderflow.common.dto.inventory.AddInventoryItemRequest;
import com.openorderflow.common.dto.inventory.InventoryItemResponse;
import com.openorderflow.common.dto.inventory.UpdateInventoryItemRequest;
import com.openorderflow.inventory.entity.BusinessItem;
import com.openorderflow.inventory.entity.Inventory;
import com.openorderflow.inventory.entity.InventoryItem;
import com.openorderflow.inventory.mapper.InventoryMapper;
import com.openorderflow.inventory.repository.BusinessItemRepository;
import com.openorderflow.inventory.repository.InventoryItemRepository;
import com.openorderflow.inventory.repository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryItemService {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryRepository inventoryRepository;
    private final BusinessItemRepository businessItemRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryItemResponse add(AddInventoryItemRequest request) {
        Inventory inventory = inventoryRepository.findById(request.getInventoryId())
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        BusinessItem businessItem = businessItemRepository.findById(request.getBusinessItemId())
                .orElseThrow(() -> new EntityNotFoundException("Business item not found"));

        boolean alreadyExists = inventoryItemRepository
                .findByInventoryIdAndBusinessItemId(request.getInventoryId(), request.getBusinessItemId())
                .isPresent();

        if (alreadyExists) {
            throw new IllegalStateException("InventoryItem already exists for given business item in this inventory.");
        }

        InventoryItem entity = inventoryMapper.toEntity(request);
        entity.setInventory(inventory);
        entity.setBusinessItem(businessItem);

        return inventoryMapper.toResponse(inventoryItemRepository.save(entity));
    }

    public InventoryItemResponse update(UUID id, UpdateInventoryItemRequest request) {
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InventoryItem not found"));

        if (request.getQuantity() != null) item.setQuantity(request.getQuantity());
        if (request.getPrice() != null) item.setPrice(request.getPrice());
        if (request.getArrivalDate() != null) item.setArrivalDate(request.getArrivalDate());
        if (request.getExpiryDate() != null) item.setExpiryDate(request.getExpiryDate());

        return inventoryMapper.toResponse(inventoryItemRepository.save(item));
    }

    public InventoryItemResponse getById(UUID id) {
        return inventoryItemRepository.findById(id)
                .map(inventoryMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("InventoryItem not found"));
    }

    public List<InventoryItemResponse> getAllByInventory(UUID inventoryId) {
        return inventoryItemRepository.findByInventoryId(inventoryId)
                .stream()
                .map(inventoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void delete(UUID id) {
        inventoryItemRepository.deleteById(id);
    }

    public void reduceQuantity(UUID inventoryId, UUID businessItemId, int qty) {
        InventoryItem item = inventoryItemRepository.findByInventoryIdAndBusinessItemId(inventoryId, businessItemId)
                .orElseThrow(() -> new EntityNotFoundException("Inventory item not found"));

        if (item.getQuantity() < qty) {
            throw new IllegalStateException("Insufficient stock");
        }

        item.setQuantity(item.getQuantity() - qty);
        inventoryItemRepository.save(item);
    }
}
