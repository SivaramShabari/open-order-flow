package com.openorderflow.business.controller;

import com.openorderflow.business.service.BusinessInventoryService;
import com.openorderflow.common.dto.inventory.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/business/inventory")
@RequiredArgsConstructor
@Slf4j
public class BusinessInventoryController {

    private final BusinessInventoryService businessInventoryService;

    @PostMapping("/items")
    public Mono<ResponseEntity<BusinessItemDto>> addBusinessItem(@RequestBody AddBusinessItemRequest request) {
        return businessInventoryService
                .addBusinessItem(request)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<InventoryDto>> createInventory(@RequestBody InventoryRequest request) {
        return businessInventoryService
                .createInventoryForOutlet(request)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/categories")
    public Mono<ResponseEntity<List<ItemCategoryDto>>> getItemCategories() {
        return businessInventoryService
                .getItemCategories()
                .map(ResponseEntity::ok);
    }

    @PostMapping("/items/inventory")
    public Mono<ResponseEntity<Void>> saveInventoryItem(@RequestBody InventoryItemDto dto) {
        return businessInventoryService.saveInventoryItem(dto)
                .thenReturn(ResponseEntity.ok().build());
    }
}
