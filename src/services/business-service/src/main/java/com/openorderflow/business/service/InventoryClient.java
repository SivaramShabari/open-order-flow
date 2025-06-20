package com.openorderflow.business.service;

import com.openorderflow.common.common.AbstractHttpClient;
import com.openorderflow.common.dto.inventory.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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

    public BusinessItemDto createItem(AddBusinessItemRequest request) {
        BusinessItemDto response = post(baseUrl, "/business-item", request, new ParameterizedTypeReference<BusinessItemDto>() {});
        return response;
    }

    public InventoryDto addInventory(InventoryRequest request) {
        return post(baseUrl, "/inventory", request, new ParameterizedTypeReference<InventoryDto>() {});
    }

    public List<ItemCategoryDto> getItemCategories() {
        return get(baseUrl, "/item-catalog", new ParameterizedTypeReference<List<ItemCategoryDto>>() {});
    }

    public List<InventoryItemDto> getAllByInventory(UUID inventoryId) {
        return get(baseUrl, "/inventory" + inventoryId.toString(), new ParameterizedTypeReference<List<InventoryItemDto>>() {});
    }

    public void addInventoryItem(InventoryItemDto dto) {
        post(baseUrl, "/inventory-item", dto, new ParameterizedTypeReference<Void>() {});
    }

    public void updateInventoryItem(InventoryItemDto dto) {
        put(baseUrl, "/inventory-item/" + dto.getId(), dto, new ParameterizedTypeReference<Void>() {});
    }

    public BusinessItemDto updateBusinessItem(BusinessItemDto request) {
        return put(baseUrl, "/business-item/" + request.getId(), request, new ParameterizedTypeReference<BusinessItemDto>() {});
    }

    public List<BusinessItemDto> getBusinessItems(UUID businessId) {
        return get(baseUrl, "/business-item/all?businessId=" + businessId, new ParameterizedTypeReference<List<BusinessItemDto>>() {});
    }
}