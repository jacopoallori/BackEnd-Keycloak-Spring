package it.jacopo.keycloak.demo_backend.logging.webclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class WebClientLoggingFilter {
    private static final Logger log = LoggerFactory.getLogger(WebClientLoggingFilter.class);
    private static final DefaultDataBufferFactory BUFFER_FACTORY = new DefaultDataBufferFactory();

    public static ExchangeFilterFunction logAll() {
        log.warn("✅ WebClientLoggingFilter ATTIVO (se vedi questo, la classe è caricata)");
        return (request, next) -> {
            // REQUEST
            log.info("➡️  HTTP {} {}", request.method(), request.url());
            log.debug("➡️  REQUEST HEADERS {}", request.headers());

            return next.exchange(request)
                    .flatMap(WebClientLoggingFilter::logAndRebuildResponse);
        };
    }

    private static Mono<ClientResponse> logAndRebuildResponse(ClientResponse response) {
        HttpHeaders headers = response.headers().asHttpHeaders();

        log.info("⬅️  RESPONSE STATUS {}", response.statusCode().value());
        log.debug("⬅️  RESPONSE HEADERS {}", headers);

        return response.bodyToMono(byte[].class)
                .defaultIfEmpty(new byte[0])
                .map(bytes -> {
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    log.debug("⬅️  RESPONSE BODY {}", body);

                    // Ricostruzione: body si può leggere una volta sola
                    DataBuffer buffer = BUFFER_FACTORY.wrap(bytes);

                    return ClientResponse.create(response.statusCode())
                            .headers(h -> h.addAll(headers))
                            .cookies(c -> c.addAll(response.cookies()))
                            .body(Flux.just(buffer))
                            .build();
                });
    }
}