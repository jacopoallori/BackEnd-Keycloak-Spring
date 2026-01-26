package it.jacopo.keycloak.demo_backend.service.admin.realm;

import it.jacopo.keycloak.demo_backend.client.KeycloakTokenClient;
import it.jacopo.keycloak.demo_backend.client.realm.KeycloakAdminRealmClient;
import it.jacopo.keycloak.demo_backend.dto.KeycloakRoleDTO;
import it.jacopo.keycloak.demo_backend.dto.KeycloakUserRoleDTO;
import it.jacopo.keycloak.demo_backend.dto.external.RoleRepresentationExternalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminRealmService {
    private final KeycloakTokenClient tokenClient;
    private final KeycloakAdminRealmClient keycloakAdminClient;

    public List<KeycloakRoleDTO> getRolesUser(String userId) {
        String token = tokenClient.getAdminAccessToken();

        return keycloakAdminClient.getRolesUser(token, userId);
    }

    public void addRealmRolesToUser(String userId, List<KeycloakUserRoleDTO> roles) {
        String token = tokenClient.getAdminAccessToken();

        List<RoleRepresentationExternalDTO> list = new ArrayList<>();

        //MAPPER DA LISTA DI KeycloakUserRoleDTO A LISTA DI RoleRepresentationExternalDTO
        for(KeycloakUserRoleDTO roleDTO : roles) {
            RoleRepresentationExternalDTO roleRepresentationExternalDTO = new RoleRepresentationExternalDTO();
            roleRepresentationExternalDTO.setId(roleDTO.getRolesId());
            roleRepresentationExternalDTO.setName(roleDTO.getRolesName());
            list.add(roleRepresentationExternalDTO);

        }

        keycloakAdminClient.addRealmRolesToUser(token, userId, list);
    }

    public void removeRealmRolesToUser(String userId, List<KeycloakUserRoleDTO> roles ) {
        String token = tokenClient.getAdminAccessToken();

        List<RoleRepresentationExternalDTO> list = new ArrayList<>();

        //MAPPER DA LISTA DI KeycloakUserRoleDTO A LISTA DI RoleRepresentationExternalDTO
        for(KeycloakUserRoleDTO roleDTO : roles) {
            RoleRepresentationExternalDTO roleRepresentationExternalDTO = new RoleRepresentationExternalDTO();
            roleRepresentationExternalDTO.setId(roleDTO.getRolesId());
            roleRepresentationExternalDTO.setName(roleDTO.getRolesName());
            list.add(roleRepresentationExternalDTO);

        }

        keycloakAdminClient.removeRealmRolesToUser(token, userId, list);
    }
}