package it.jacopo.keycloak.demo_backend.client;

import it.jacopo.keycloak.demo_backend.dto.external.KeycloakUserExternalDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

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
}