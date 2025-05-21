package com.openorderflow.inventory.controller;

import com.openorderflow.common.dto.inventory.InventoryRequest;
import com.openorderflow.common.dto.inventory.InventoryResponse;
import com.openorderflow.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> create(@RequestBody @Valid InventoryRequest request) {
        return ResponseEntity.ok(inventoryService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> update(@PathVariable UUID id, @RequestBody @Valid InventoryRequest request) {
        return ResponseEntity.ok(inventoryService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(inventoryService.getById(id));
    }

    @GetMapping("/business-outlet/{outletId}")
    public ResponseEntity<InventoryResponse> getAllByBusinessOutlet(@PathVariable UUID outletId) {
        return ResponseEntity.ok(inventoryService.getByBusinessOutlet(outletId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) throws Exception {
        throw new Exception("Not implemented");
        //inventoryService.delete(id);
        //return ResponseEntity.noContent().build();
    }
}
