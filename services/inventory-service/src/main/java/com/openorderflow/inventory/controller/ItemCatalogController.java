package com.openorderflow.inventory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item-catalog")
@RequiredArgsConstructor
public class ItemCatalogController {

    private final ItemCatalogService itemCatalogService;

    @PostMapping
    public ResponseEntity<ItemCatalogResponse> create(@RequestBody @Valid ItemCatalogRequest request) {
        return ResponseEntity.ok(itemCatalogService.createItem(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCatalogResponse> update(@PathVariable UUID id, @RequestBody @Valid ItemCatalogRequest request) {
        return ResponseEntity.ok(itemCatalogService.updateItem(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemCatalogResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(itemCatalogService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemCatalogResponse>> getAll() {
        return ResponseEntity.ok(itemCatalogService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        itemCatalogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
