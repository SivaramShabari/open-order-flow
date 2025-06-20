package com.openorderflow.business.controller;

import com.openorderflow.business.service.BusinessInventoryService;
import com.openorderflow.common.auth.exception.UnauthorizedException;
import com.openorderflow.common.dto.inventory.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Slf4j
public class BusinessInventoryController {

    private final BusinessInventoryService businessInventoryService;

    @PostMapping("/business-item")
    public ResponseEntity<BusinessItemDto> addBusinessItem(@RequestBody AddBusinessItemRequest request) throws UnauthorizedException {
        var result = businessInventoryService.addBusinessItem(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryRequest request) {
        var result = businessInventoryService.createInventoryForOutlet(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ItemCategoryDto>> getItemCategories() {
        var result = businessInventoryService.getItemCategories();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/business-items")
    public ResponseEntity<List<BusinessItemDto>> getBusinessItems(UUID businessId) {
        var result = businessInventoryService.getBusinessItems(businessId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/inventory-item")
    public ResponseEntity<Void> saveInventoryItem(@RequestBody InventoryItemDto dto) {
        businessInventoryService.saveInventoryItem(dto);
        return ResponseEntity.ok().build();
    }
}
