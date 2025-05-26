package com.openorderflow.business.service;

import com.openorderflow.business.dto.BusinessDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryClient {
    private final WebClient businessWebClient;

    public Mono<BusinessDto> getBusinessById(String id) {
        log.info("Calling Inventory service for ID={}", id);
        return businessWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/Inventory/{id}").build(id))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        response.bodyToMono(String.class)
                                .flatMap(body -> {
                                    log.warn("Business not found for id={}, body={}", id, body);
                                    throw new RuntimeException("Business not found: " + body);
                                }))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        response.bodyToMono(String.class)
                                .flatMap(body -> {
                                    log.error("Internal server error for id={}, body={}", id, body);
                                    throw new RuntimeException("Internal error: " + body);
                                }))
                .bodyToMono(BusinessDto.class);
    }
}
