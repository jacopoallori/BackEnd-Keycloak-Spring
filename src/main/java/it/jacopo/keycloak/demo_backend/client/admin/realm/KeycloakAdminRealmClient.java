package it.jacopo.keycloak.demo_backend.client.admin.realm;

import it.jacopo.keycloak.demo_backend.client.admin.KeycloakTokenClient;
import it.jacopo.keycloak.demo_backend.dto.KeycloakRoleDTO;
import it.jacopo.keycloak.demo_backend.dto.external.RoleRepresentationExternalDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static it.jacopo.keycloak.demo_backend.exception.util.MapKeycloakError.mapKeycloakError;

@Component
@RequiredArgsConstructor
public class KeycloakAdminRealmClient {
    private static final Logger log = LoggerFactory.getLogger(KeycloakAdminRealmClient.class);

    private final WebClient webClient ;
    private final KeycloakTokenClient tokenClient;

    private final String serverUrl = "http://localhost:8080";
    private final String realm = "Keycloack_Login_Prova";

    //*
    // Ottieni i ruoli assegnati a un singolo utenti dal Keycloak a livello di Realm
    //*
    public List<KeycloakRoleDTO> getRolesUser(String token, String userId) {
        log.debug("Richiesta lista utente userId={}", userId);

        try {
            List<KeycloakRoleDTO> response = webClient.get()
                    .uri(serverUrl + "/admin/realms/" + realm
                            + "/users/" + userId
                            + "/role-mappings/realm/composite")
                    .headers(h -> h.setBearerAuth(token))
                    .retrieve()
                    .bodyToFlux(KeycloakRoleDTO.class)
                    .collectList()
                    .block();

            log.debug("Keycloak risposta lista ruoli per UserId:{} - Response: {}", response, userId);
            return response;
        }catch (Exception e){
            log.error("Errore durante la richiesta dei ruoli per userId={}: {}", userId, e.getMessage());
            throw new RuntimeException("Errore durante la richiesta dei ruoli: " + e.getMessage());
        }
    }

    //*
    // Assegna ruoli a un singolo utente dal Keycloak a livello di Realm
    //*
    public void addRealmRolesToUser(String token, String userId, List<RoleRepresentationExternalDTO> roles) {
        log.debug("Aggiunti ruoli: {}, per utente userId: {}", roles.toString(), userId);

        try {
            webClient.post()
                    .uri(serverUrl + "/admin/realms/" + realm
                            + "/users/" + userId
                            + "/role-mappings/realm")
                    .headers(h -> h.setBearerAuth(token))
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(roles) // array di RoleRepresentation
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, resp ->
                            resp.bodyToMono(String.class)
                                    .defaultIfEmpty("")
                                    .flatMap(body -> Mono.error(mapKeycloakError(resp.statusCode().value(), body, userId)))
                    )
                    .toBodilessEntity()
                    .block();
        }catch (Exception e){
            log.error("Errore durante l'aggiunta dei ruoli per userId={}: {}", userId, e.getMessage());
            throw e;
        }

        log.debug("Ruoli aggiunti con successo.");
    }

    //*
    // Rimuovi ruoli a un singolo utente dal Keycloak a livello di Realm
    //*
    public void removeRealmRolesToUser(String token, String userId, List<RoleRepresentationExternalDTO> roles) {
        log.debug("Rimossi ruoli: {}, per utente userId: {}", roles.toString(), userId);

        try {
            webClient.method(HttpMethod.DELETE)
                    .uri(serverUrl + "/admin/realms/" + realm
                            + "/users/" + userId
                            + "/role-mappings/realm")
                    .headers(h -> h.setBearerAuth(token))
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(roles) // array di RoleRepresentation
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, resp ->
                            resp.bodyToMono(String.class)
                                    .defaultIfEmpty("")
                                    .flatMap(body -> Mono.error(mapKeycloakError(resp.statusCode().value(), body, userId)))
                    )
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            log.error("Errore durante la rimozione dei ruoli per userId={}: {}", userId, e.getMessage());
            throw new RuntimeException("Errore durante la rimozione dei ruoli: " + e.getMessage());
        }
        log.debug("Ruoli rimossi con successo.");
    }
}