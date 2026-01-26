package it.jacopo.keycloak.demo_backend.client;

import it.jacopo.keycloak.demo_backend.dto.KeycloakUserDTO;
import it.jacopo.keycloak.demo_backend.dto.UserFilterDTO;
import it.jacopo.keycloak.demo_backend.dto.external.KeycloakUserExternalDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

    private void addIfPresent(UriComponentsBuilder b, String name, String value) {
        if (value != null && !value.isBlank()) b.queryParam(name, value);
    }

    private void addIfPresent(UriComponentsBuilder b, String name, Boolean value) {
        if (value != null) b.queryParam(name, value);
    }

}