package com.openorderflow.inventory.service;

import com.openorderflow.common.dto.inventory.AddBusinessItemRequest;
import com.openorderflow.common.dto.inventory.BusinessItemResponse;
import com.openorderflow.common.dto.inventory.UpdateBusinessItemRequest;
import com.openorderflow.inventory.entity.BusinessItem;
import com.openorderflow.inventory.mapper.InventoryMapper;
import com.openorderflow.inventory.repository.BusinessItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BusinessItemService {

    private final BusinessItemRepository businessItemRepository;
    private final InventoryMapper inventoryMapper;

    public BusinessItemResponse add(AddBusinessItemRequest request) {
        BusinessItem entity = inventoryMapper.toEntity(request);
        entity.setUpdatedAt(Instant.now());
        businessItemRepository.save(entity);
        return inventoryMapper.toResponse(entity);
    }

    public BusinessItemResponse update(UUID id, UpdateBusinessItemRequest request) {
        BusinessItem item = businessItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BusinessItem not found"));

        if (request.getBasePrice() != null) item.setBasePrice(request.getBasePrice());
        if (request.getIsAvailable() != null) item.setAvailable(request.getIsAvailable());
        item.setUpdatedAt(Instant.now());

        businessItemRepository.save(item);
        return inventoryMapper.toResponse(item);
    }

    public BusinessItemResponse getById(UUID id) {
        return businessItemRepository.findById(id)
                .map(inventoryMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("BusinessItem not found"));
    }

    public void delete(UUID id) {
        businessItemRepository.deleteById(id);
    }
}
