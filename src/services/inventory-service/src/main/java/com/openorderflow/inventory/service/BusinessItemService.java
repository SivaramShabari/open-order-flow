package com.openorderflow.inventory.service;

import com.openorderflow.common.dto.inventory.AddBusinessItemRequest;
import com.openorderflow.common.dto.inventory.BusinessItemDto;
import com.openorderflow.common.dto.inventory.UpdateBusinessItemRequest;
import com.openorderflow.inventory.entity.BusinessItem;
import com.openorderflow.inventory.mapper.InventoryMapper;
import com.openorderflow.inventory.repository.BusinessItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BusinessItemService {

    private final BusinessItemRepository businessItemRepository;
    private final InventoryMapper inventoryMapper;

    public BusinessItemDto add(AddBusinessItemRequest request) {
        BusinessItem entity = inventoryMapper.toEntity(request);
        entity.setUpdatedAt(Instant.now());
        businessItemRepository.save(entity);
        return inventoryMapper.toResponse(entity);
    }

    public BusinessItemDto update(UUID id, UpdateBusinessItemRequest request) {
        BusinessItem item = businessItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BusinessItem not found"));

        if (request.getBasePrice() != null) item.setBasePrice(request.getBasePrice());
        if (request.getIsAvailable() != null) item.setIsAvailable(request.getIsAvailable());
        item.setUpdatedAt(Instant.now());

        businessItemRepository.save(item);
        return inventoryMapper.toResponse(item);
    }

    public BusinessItemDto getById(UUID id) {
        return businessItemRepository.findById(id)
                .map(inventoryMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("BusinessItem not found"));
    }

    public List<BusinessItemDto> getAllByIds(Map<UUID, Integer> items) {
        return businessItemRepository.findAllByIdIn(items.keySet().stream().toList())
                .stream()
                .map(inventoryMapper::toResponse)
                .peek(item -> item.setQuantity(items.get(item.getId())))
                .toList();
    }

    public void delete(UUID id) {
        businessItemRepository.deleteById(id);
    }

    public List<BusinessItemDto> getAllByBusinessId(UUID businessId) {
        return businessItemRepository.findAllByBusinessId(businessId)
                .stream()
                .map(inventoryMapper::toResponse)
                .toList();
    }
}
