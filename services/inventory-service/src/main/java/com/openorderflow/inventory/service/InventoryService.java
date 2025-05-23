package com.openorderflow.inventory.service;

import com.openorderflow.common.dto.inventory.InventoryDto;
import com.openorderflow.common.dto.inventory.InventoryRequest;
import com.openorderflow.inventory.repository.InventoryRepository;
import com.openorderflow.inventory.entity.Inventory;
import com.openorderflow.inventory.mapper.InventoryMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryDto create(InventoryRequest request) {
        Inventory inventory = inventoryMapper.toEntity(request);
        inventory = inventoryRepository.save(inventory);
        return inventoryMapper.toResponse(inventory);
    }

    public InventoryDto update(UUID id, InventoryRequest request) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        inventory.setBusinessId(request.getBusinessOutletId());
        inventory.setLocationName(request.getLocationName());
        inventory.setCapacity(request.getCapacity());
        inventory.setInventoryType(request.getInventoryType());
        inventory.setCreatedBy(request.getCreatedBy());
        inventory.setUpdatedAt(Instant.now());

        inventory = inventoryRepository.save(inventory);
        return inventoryMapper.toResponse(inventory);
    }

    public InventoryDto getById(UUID id) {
        return inventoryRepository.findById(id)
                .map(inventoryMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));
    }

    public InventoryDto getByBusinessOutlet(UUID outletId) {
        return inventoryMapper.toResponse(inventoryRepository.findByBusinessOutletId(outletId));
    }

}
