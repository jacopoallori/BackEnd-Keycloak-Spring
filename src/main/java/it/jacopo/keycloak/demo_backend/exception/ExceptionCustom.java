package it.jacopo.keycloak.demo_backend.exception;

public class ExceptionCustom {

    public static class KeycloakRoleNotFoundException extends RuntimeException {
        public KeycloakRoleNotFoundException(String message) { super(message); }
    }

    public static class KeycloakClientException extends RuntimeException {
        public KeycloakClientException(String message) { super(message); }
    }

    public static class KeycloakUnauthorizedException extends RuntimeException {
        public KeycloakUnauthorizedException(String message) {
        }
    }

    public static class KeycloakGenericException extends RuntimeException {
        public KeycloakGenericException(String message) {
        }
    }
}
