package com.openorderflow.inventory.controller;

import com.openorderflow.common.dto.inventory.AddBusinessItemRequest;
import com.openorderflow.common.dto.inventory.BusinessItemDto;
import com.openorderflow.common.dto.inventory.UpdateBusinessItemRequest;
import com.openorderflow.inventory.service.BusinessItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inventory/business-item")
@RequiredArgsConstructor
public class BusinessItemController {

    private final BusinessItemService businessItemService;

    @PostMapping
    public ResponseEntity<BusinessItemDto> add(@RequestBody @Valid AddBusinessItemRequest request) {
        return ResponseEntity.ok(businessItemService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessItemDto> update(@PathVariable UUID id, @RequestBody UpdateBusinessItemRequest request) {
        return ResponseEntity.ok(businessItemService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessItemDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(businessItemService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        businessItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
