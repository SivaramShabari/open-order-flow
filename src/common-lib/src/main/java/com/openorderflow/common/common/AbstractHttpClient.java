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

    protected <T> Mono<T> get(String baseUrl, String path, ParameterizedTypeReference<T> responseType) {
        return webClientBuilder.baseUrl(baseUrl).build()
                .get()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::is2xxSuccessful, this::handleSuccess)
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(responseType);
    }

    protected <T> Mono<T> post(String baseUrl, String path, Object body, ParameterizedTypeReference<T> responseType) {
        return webClientBuilder.baseUrl(baseUrl).build()
                .post()
                .uri(path)
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::is2xxSuccessful, this::handleSuccess)
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(responseType);
    }

    protected <T> Mono<T> put(String baseUrl, String path, Object body, ParameterizedTypeReference<T> responseType) {
        return webClientBuilder.baseUrl(baseUrl).build()
                .put()
                .uri(path)
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::is2xxSuccessful, this::handleSuccess)
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(responseType);
    }

    protected Mono<Void> delete(String baseUrl, String path) {
        return webClientBuilder.baseUrl(baseUrl).build()
                .delete()
                .uri(path)
                .retrieve()
                .onStatus(HttpStatusCode::is2xxSuccessful, this::handleSuccess)
                .onStatus(HttpStatusCode::isError, this::handleError)
                .bodyToMono(Void.class);
    }

    protected Mono<? extends Throwable> handleSuccess(ClientResponse response) {
        return response.bodyToMono(String.class).flatMap(body -> {
            log.info("Default HTTP success handler: {} - {}", response.statusCode(), body);
            return Mono.empty();
        });
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

    protected <T> ParameterizedTypeReference<T> typeOf() {
        return new ParameterizedTypeReference<T>() {};
    }
}
