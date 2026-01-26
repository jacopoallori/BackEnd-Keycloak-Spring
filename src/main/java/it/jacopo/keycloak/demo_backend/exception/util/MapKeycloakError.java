package it.jacopo.keycloak.demo_backend.exception.util;

import it.jacopo.keycloak.demo_backend.exception.ExceptionCustom;
import it.jacopo.keycloak.demo_backend.exception.dto.KeycloakErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.ObjectMapper;

public class MapKeycloakError {
    private static final Logger log = LoggerFactory.getLogger(MapKeycloakError.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static RuntimeException mapKeycloakError(int status, String body, String userId) {
        KeycloakErrorDTO err = tryParseKeycloakError(body);

        String kcError = err != null ? err.getError() : null;
        String kcDesc  = err != null ? err.getError_description() : null;

        //GESTIONE SPECIFICA PER RUOLO INESISTENTE
        if ((status == 404 || status == 400) && kcError != null && kcError.equalsIgnoreCase("Role not found")) {
            log.error("Keycloak: Role not found (userId={}) body={}", userId, body);
            return new ExceptionCustom.KeycloakRoleNotFoundException(
                    "Ruolo non trovato durante la rimozione (userId=" + userId + ")"
            );
        }

        // GESTIONE SPECIFICA PER TOKEN NON VALIDO O SCADUTO
        if (status == 401 || status == 403) {
            log.error("Keycloak auth error (status={}, userId={}) body={}", status, userId, body);
            return new ExceptionCustom.KeycloakUnauthorizedException(
                    "Non autorizzato su Keycloak (token scaduto o permessi insufficienti)"
            );
        }

        log.error("Keycloak error (status={}, userId={}) body={}", status, userId, body);
        String msg = "Errore Keycloak " + status
                + (kcDesc != null && !kcDesc.isBlank() ? (": " + kcDesc) : "")
                + (body != null && !body.isBlank() ? (" | body=" + body) : "");

        return new ExceptionCustom.KeycloakGenericException(msg);
    }

    private static KeycloakErrorDTO tryParseKeycloakError(String body) {
        try {
            if (body == null || body.isBlank()) return null;
            return objectMapper.readValue(body, KeycloakErrorDTO.class);
        } catch (Exception ignored) {
            return null;
        }
    }
}
