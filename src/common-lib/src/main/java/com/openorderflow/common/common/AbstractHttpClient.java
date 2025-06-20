package com.openorderflow.common.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public abstract class AbstractHttpClient {

    private final WebClient.Builder webClientBuilder;

    protected AbstractHttpClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    protected <T> T get(String baseUrl, String path, ParameterizedTypeReference<T> responseType) {
        var response = webClientBuilder.baseUrl(baseUrl).build()
                .get()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(responseType)
                .block();
        log.info("GET {} response: {}", path, response != null ? response.toString() : null);
        return response;
    }

    protected <T> T post(String baseUrl, String path, Object body, ParameterizedTypeReference<T> responseType) {
        var response =  webClientBuilder.baseUrl(baseUrl).build()
                .post()
                .uri(path)
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(responseType)
                .block();

        log.info("POST {} response: {}", path, response != null ? response.toString() : null);
        return response;
    }

    protected <T> T put(String baseUrl, String path, Object body, ParameterizedTypeReference<T> responseType) {
        var response =   webClientBuilder.baseUrl(baseUrl).build()
                .put()
                .uri(path)
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(responseType)
                .block();
        log.info("POST {} response: {}", path, response != null ? response.toString() : null);
        return response;
    }

    protected void delete(String baseUrl, String path) {
        webClientBuilder.baseUrl(baseUrl).build()
                .delete()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(Void.class)
                .block();
    }

    /**
     * Override this in child class if you want custom error handling.
     */
    protected Mono<? extends Throwable> handleError(ClientResponse response) {
        return response.bodyToMono(String.class).flatMap(body -> {
            log.error("Default HTTP error handler: {} - {}", response.statusCode(), body);
            return Mono.error(new RuntimeException("HTTP " + response.statusCode() + ": " + body));
        });
    }
}
