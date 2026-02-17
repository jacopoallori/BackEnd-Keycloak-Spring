package it.jacopo.keycloak.demo_backend.client.admin;

import it.jacopo.keycloak.demo_backend.dto.UserFilterDTO;
import it.jacopo.keycloak.demo_backend.dto.external.CreateUserExternalDTO;
import it.jacopo.keycloak.demo_backend.dto.external.KeycloakUserExternalDTO;
import it.jacopo.keycloak.demo_backend.dto.external.UpdateUserExternalDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KeycloakAdminClient {
    private static final Logger log = LoggerFactory.getLogger(KeycloakAdminClient.class);
    private final String serverUrl = "http://localhost:8080";
    private final String realm = "Keycloack_Login_Prova";

    private final WebClient webClient;

    //*
    // Ottieni tutti gli utenti dal Keycloak
    //*
    public List<KeycloakUserExternalDTO> getUsers(String token) {
        log.debug("Richiesta lista utenti");

        try {
            List<KeycloakUserExternalDTO> response = webClient.get()
                    .uri(serverUrl + "/admin/realms/" + realm + "/users")
                    .headers(h -> h.setBearerAuth(token))
                    .retrieve()
                    .bodyToFlux(KeycloakUserExternalDTO.class)
                    .collectList()
                    .block();

            if(response == null){
                throw new RuntimeException("Risposta nulla durante la richiesta della lista utenti");
            }
            log.debug("Keycloak risposta: utenti={}", response);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Errore durante la richiesta della lista utenti: " + e.getMessage());
        }
    }


    //*
    // Ottieni utenti con filtro campo Id
    //*
    public List<KeycloakUserExternalDTO> searchUsersForId(String token,
                                                     String id,
                                                     int first,
                                                     int max) {


            KeycloakUserExternalDTO user = webClient.get()
                    .uri(serverUrl + "/admin/realms/" + realm + "/users/" + id)
                    .headers(h -> h.setBearerAuth(token))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(KeycloakUserExternalDTO.class)
                    .block();

            return user == null ? List.of() : List.of(user);
    }

    //*
    // Ottieni utenti con filtri
    //*
    public List<KeycloakUserExternalDTO> searchUsers(String token,
                                             UserFilterDTO userFilterDTO,
                                             int first,
                                             int max) {

        UriComponentsBuilder b = UriComponentsBuilder
                .fromUriString(serverUrl)
                .path("/admin/realms/{realm}/users")
                .queryParam("first", first)
                .queryParam("max", max);


        addIfPresent(b, "username", userFilterDTO.getUsername());
        addIfPresent(b, "firstName", userFilterDTO.getFirstName());
        addIfPresent(b, "lastName", userFilterDTO.getLastName());
        addIfPresent(b, "email", userFilterDTO.getEmail());
        addIfPresent(b, "enabled", userFilterDTO.getEnabled());
        addIfPresent(b, "emailVerified", userFilterDTO.getEmailVerified());

        // Exact (se lo vuoi)
        b.queryParam("exact", false);

        String uri = b.buildAndExpand(realm).toUriString();

        return webClient.get()
                .uri(uri)
                .headers(h -> h.setBearerAuth(token))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(KeycloakUserExternalDTO.class)
                .collectList()
                .block();
    }

    //*
    // Creazione di un nuovo utente
    //*
    public Mono<String> createUser(String token, CreateUserExternalDTO dto) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/{realm}/users")
                        .build(realm))
                .headers(h -> h.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return Mono.just(
                                response.headers()
                                        .asHttpHeaders()
                                        .getFirst("Location")
                        );
                    }
                    return response.createException().flatMap(Mono::error);
                });
    }

    //*
    // Aggiornamento dati di utente
    //*
    public Mono<Void> updateUser(String token, String userId, KeycloakUserExternalDTO dto) {
        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/{realm}/users/{userId}")
                        .build(realm, userId))
                .headers(h -> h.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(Void.class);
    }

    //*
    // Eliminazione di un utente
    //*
    public Mono<Void> deleteUser(String token, String userId) {
        return webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/{realm}/users/{userId}")
                        .build(realm, userId))
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(Void.class);
    }

    //*
    // Abilitazione/Disabilitazione di un utente
    //*
    public Mono<Void> setUserEnabled(String token, String userId, boolean enabled) {
        Map<String, Object> body = Map.of("enabled", enabled);

        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/{realm}/users/{userId}")
                        .build(realm, userId))
                .headers(h -> h.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Void.class);
    }

    //*
    // Reset Password
    //*
    public Mono<Void> sendResetPasswordEmail(String token, String userId, int lifespanSeconds,
                                             String clientId, String redirectUri) {
        List<String> actions = List.of("UPDATE_PASSWORD");

        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/{realm}/users/{userId}/execute-actions-email")
                        .queryParam("lifespan", lifespanSeconds)
                        .queryParam("client_id", clientId)
                        .queryParam("redirect_uri", redirectUri)
                        .build(realm, userId))
                .headers(h -> h.setBearerAuth(token))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(actions)
                .retrieve()
                .bodyToMono(Void.class);
    }

    //*
    // Invio verifica email
    //*
    public Mono<Void> sendVerifyEmail(String token, String userId, Integer lifespanSec,
                                String clientId, String redirectUri){
        return webClient.put()
                .uri(uriBuilder -> {
                    var b = uriBuilder
                            .path("/admin/realms/{realm}/users/{userId}/send-verify-email");

                    if (lifespanSec != null) b = b.queryParam("lifespan", lifespanSec);
                    if (clientId != null && !clientId.isBlank()) b = b.queryParam("client_id", clientId);
                    if (redirectUri != null && !redirectUri.isBlank()) b = b.queryParam("redirect_uri", redirectUri);

                    return b.build(realm, userId);
                })
                .headers(h -> h.setBearerAuth(token))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class);
    }

    //*
    // Reset password utente for admin
    //*
    public Mono<Void> sendVerifyEmailForAdmin(String token, String userId){
        return null;
    }

    private void addIfPresent(UriComponentsBuilder b, String name, String value) {
        if (value != null && !value.isBlank()) b.queryParam(name, value);
    }

    private void addIfPresent(UriComponentsBuilder b, String name, Boolean value) {
        if (value != null) b.queryParam(name, value);
    }

}