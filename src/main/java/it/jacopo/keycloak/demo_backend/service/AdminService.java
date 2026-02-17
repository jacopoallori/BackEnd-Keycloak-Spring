package it.jacopo.keycloak.demo_backend.service;

import it.jacopo.keycloak.demo_backend.client.admin.KeycloakAdminClient;
import it.jacopo.keycloak.demo_backend.client.admin.KeycloakTokenClient;
import it.jacopo.keycloak.demo_backend.dto.KeycloakUserDTO;
import it.jacopo.keycloak.demo_backend.dto.UserCreateDTO;
import it.jacopo.keycloak.demo_backend.dto.UserFilterDTO;
import it.jacopo.keycloak.demo_backend.dto.external.CreateUserExternalDTO;
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

    //*
    // Crea un nuovo utente
    //*
    public void createUser(UserCreateDTO userCreateDTO) {
        String token = tokenClient.getAdminAccessToken();
        //Mapper a UserCreateDTO a CreateUserExternalDTO
        keycloakAdminClient.createUser(token, new CreateUserExternalDTO());
    }

    //*
    //Aggiorna un utente
    //*
    public void updateUser(String userId, KeycloakUserDTO updateUser){
        String token = tokenClient.getAdminAccessToken();
        //Mapper da null a KeycloakUserExternalDTO
        keycloakAdminClient.updateUser(token, userId, new KeycloakUserExternalDTO());
    }

    //*
    // Elimina un utente
    //*
    public void deleteUser(String userId){
        String token = tokenClient.getAdminAccessToken();
        keycloakAdminClient.deleteUser(token, userId);
    }

    //*
    // Abilita/Disabilita un utente
    //*
    public void setUserEnabled(String userId, boolean enabled){
        String token = tokenClient.getAdminAccessToken();
        keycloakAdminClient.setUserEnabled(token, userId, enabled);
    }

    //*
    // Reset password utente
    //*
    public void sendResetPasswordEmail(String userId){
        String token = tokenClient.getAdminAccessToken();
        keycloakAdminClient.sendResetPasswordEmail(token, userId,1,null, null);
    }

    //*
    // Reset password utente per mail
    //*
    public void sendVerifyEmail(String userId){
        String token = tokenClient.getAdminAccessToken();
        keycloakAdminClient.sendVerifyEmail(token, userId,1,null, null);
    }

    //*
    // Reset password utente for admin
    //*
    public void sendVerifyEmailForAdmin(String userId){
        String token = tokenClient.getAdminAccessToken();
        keycloakAdminClient.sendVerifyEmailForAdmin(token, userId);
    }
}