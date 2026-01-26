package it.jacopo.keycloak.demo_backend.service;

import it.jacopo.keycloak.demo_backend.client.KeycloakAdminClient;
import it.jacopo.keycloak.demo_backend.client.KeycloakTokenClient;
import it.jacopo.keycloak.demo_backend.dto.KeycloakUserDTO;
import it.jacopo.keycloak.demo_backend.dto.UserFilterDTO;
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

    //*
    // Ottieni tutti gli utenti
    //*
    public List<KeycloakUserDTO> getAllUsers() {
        String token = tokenClient.getAdminAccessToken();
        List<KeycloakUserExternalDTO> reponse = keycloakAdminClient.getUsers(token);

        return mapper.fromDTOExternalToDTO(reponse);
    }

    //*
    // Ottieni utenti con filtri
    //*
    public List<KeycloakUserDTO> getUsersFiltered(String id,
                                                  String username,
                                                  String firstName,
                                                  String lastName,
                                                  String email,
                                                  Boolean emailVerified,
                                                  String createdTimestamp,
                                                  Boolean enabled,
                                                  int first,
                                                  int max) {

        String token = tokenClient.getAdminAccessToken();

        if (id != null && !id.isBlank()) {
            List<KeycloakUserExternalDTO> reponse = keycloakAdminClient.searchUsersForId(token, id, first, max);
            return mapper.fromDTOExternalToDTO(reponse);
        }else {
            UserFilterDTO userFilterDTO = new UserFilterDTO(id, username, firstName, lastName, email, emailVerified, createdTimestamp, enabled);
            List<KeycloakUserExternalDTO> reponse = keycloakAdminClient.searchUsers(token, userFilterDTO, first, max);
            return mapper.fromDTOExternalToDTO(reponse);
        }
    }
}