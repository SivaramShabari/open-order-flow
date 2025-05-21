package com.openorderflow.inventory.controller;

import com.openorderflow.common.dto.inventory.AddInventoryItemRequest;
import com.openorderflow.common.dto.inventory.InventoryItemResponse;
import com.openorderflow.common.dto.inventory.UpdateInventoryItemRequest;
import com.openorderflow.inventory.service.InventoryItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory/inventory-item")
@RequiredArgsConstructor
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;

    @PostMapping
    public ResponseEntity<InventoryItemResponse> create(@RequestBody @Valid AddInventoryItemRequest request) {
        return ResponseEntity.ok(inventoryItemService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryItemResponse> update(@PathVariable UUID id, @RequestBody UpdateInventoryItemRequest request) {
        return ResponseEntity.ok(inventoryItemService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(inventoryItemService.getById(id));
    }

    @GetMapping("/inventory/{inventoryId}")
    public ResponseEntity<List<InventoryItemResponse>> getAllByInventory(@PathVariable UUID inventoryId) {
        return ResponseEntity.ok(inventoryItemService.getAllByInventory(inventoryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        inventoryItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
