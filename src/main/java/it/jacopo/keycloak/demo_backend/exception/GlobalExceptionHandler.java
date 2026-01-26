package it.jacopo.keycloak.demo_backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ExceptionCustom.KeycloakRoleNotFoundException.class)
    public ResponseEntity<ApiError> handleRoleNotFound(ExceptionCustom.KeycloakRoleNotFoundException ex) {
        return ResponseEntity.status(404).body(
                ApiError.of("KC_ROLE_NOT_FOUND", ex.getMessage(), 404)
        );
    }

    @ExceptionHandler(ExceptionCustom.KeycloakClientException.class)
    public ResponseEntity<ApiError> handleKeycloakGeneric(ExceptionCustom.KeycloakClientException ex) {
        log.error("Keycloak error: {}", ex.getMessage());
        return ResponseEntity.status(502).body(
                ApiError.of("KC_ERROR", ex.getMessage(), 502)
        );
    }

}
