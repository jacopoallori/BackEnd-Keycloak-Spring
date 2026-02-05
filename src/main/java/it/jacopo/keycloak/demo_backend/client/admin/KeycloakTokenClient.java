package it.jacopo.keycloak.demo_backend.client.admin;

import it.jacopo.keycloak.demo_backend.dto.external.KeycloakTokenExternalDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class KeycloakTokenClient {
    private static final Logger log = LoggerFactory.getLogger(KeycloakTokenClient.class);

    private final WebClient webClient ;

    @Value("${keycloak.server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.admin-client.client-id}")
    private String clientId;

    @Value("${keycloak.admin-client.client-secret}")
    private String clientSecret;

    public String getAdminAccessToken() {
        try {
            KeycloakTokenExternalDTO response =
                    webClient.post()
                            .uri(serverUrl + "/realms/" + realm + "/protocol/openid-connect/token")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .body(BodyInserters
                                    .fromFormData("grant_type", "client_credentials")
                                    .with("client_id", clientId)
                                    .with("client_secret", clientSecret))
                            .retrieve()
                            .bodyToMono(KeycloakTokenExternalDTO.class)
                            .block();

            if (response != null){
                log.debug("Token di accesso admin ottenuto con successo, token={}", response.getAccessToken());
                return response.getAccessToken();
            } else {
                throw new RuntimeException("Risposta nulla durante la richiesta del token di accesso admin");
            }
        }catch (Exception e){
            throw new RuntimeException("Errore durante la richiesta del token di accesso admin: " + e.getMessage());
        }
    }
}