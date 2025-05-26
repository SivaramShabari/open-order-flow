package com.openorderflow.business.service;

import com.openorderflow.common.common.AbstractHttpClient;
import com.openorderflow.common.dto.inventory.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class InventoryClient extends AbstractHttpClient {

    @Value("${inventory-service.base-url}")
    private String baseUrl;

    protected InventoryClient(WebClient.Builder webClientBuilder) {
        super(webClientBuilder);
    }

    public Mono<BusinessItemDto> createItem(AddBusinessItemRequest request) {
        return post(baseUrl, "/business-item", request, typeOf());
    }

    public Mono<InventoryDto> addInventory(InventoryRequest request) {
        return post(baseUrl, "/inventory", request, typeOf());
    }

    public Mono<List<ItemCategoryDto>> getItemCategories() {
        return get(baseUrl, "/item-catalog", typeOf());
    }

    public Mono<List<InventoryItemDto>> getAllByInventory(UUID inventoryId) {
        return get(baseUrl, "/inventory/" + inventoryId.toString(), typeOf());
    }

    public Mono<Void> addInventoryItem(InventoryItemDto dto) {
        return post(baseUrl, "/inventory-item", dto, typeOf());
    }

    public Mono<Void> updateInventoryItem(InventoryItemDto dto) {
        return put(baseUrl, "/inventory-item/" + dto.getId(), dto, typeOf());
    }
}
