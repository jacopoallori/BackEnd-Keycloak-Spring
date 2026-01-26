package it.jacopo.keycloak.demo_backend.service;

import it.jacopo.keycloak.demo_backend.client.KeycloakAdminClient;
import it.jacopo.keycloak.demo_backend.client.KeycloakTokenClient;
import it.jacopo.keycloak.demo_backend.dto.KeycloakUserDTO;
import it.jacopo.keycloak.demo_backend.dto.external.KeycloakUserExternalDTO;
import it.jacopo.keycloak.demo_backend.mapper.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final KeycloakTokenClient tokenClient;
    private final KeycloakAdminClient keycloakAdminClient;
    private final UserDTOMapper mapper;

    public List<KeycloakUserDTO> getAllUsers() {
        String token = tokenClient.getAdminAccessToken();
        List<KeycloakUserExternalDTO> reponse = keycloakAdminClient.getUsers(token);

        return mapper.fromDTOExternalToDTO(reponse);
    }
}