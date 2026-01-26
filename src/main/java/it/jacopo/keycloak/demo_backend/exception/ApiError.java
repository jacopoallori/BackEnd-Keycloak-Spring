package it.jacopo.keycloak.demo_backend.exception;

import java.time.OffsetDateTime;

public record ApiError(
        String code,
        String message,
        int status,
        String timestamp
) {
    public static ApiError of(String code, String message, int status) {
        return new ApiError(code, message, status, OffsetDateTime.now().toString());
    }
}