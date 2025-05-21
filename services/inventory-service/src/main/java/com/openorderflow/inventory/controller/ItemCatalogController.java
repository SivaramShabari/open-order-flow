package com.openorderflow.inventory.controller;

import com.openorderflow.inventory.entity.ItemCatalog;
import com.openorderflow.inventory.service.ItemCatalogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory/item-catalog")
@RequiredArgsConstructor
public class ItemCatalogController {

    private final ItemCatalogService itemCatalogService;

    @PostMapping
    public ResponseEntity<ItemCatalog> create(@RequestBody @Valid ItemCatalog request) {
        return ResponseEntity.ok(itemCatalogService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCatalog> update(@PathVariable UUID id, @RequestBody @Valid ItemCatalog request) {
        return ResponseEntity.ok(itemCatalogService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCatalog> get(@PathVariable UUID id) {
        return ResponseEntity.ok(itemCatalogService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemCatalog>> getAll() {
        return ResponseEntity.ok(itemCatalogService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        itemCatalogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
