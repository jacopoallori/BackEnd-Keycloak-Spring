package it.jacopo.keycloak.demo_backend.service.admin.clients;

import it.jacopo.keycloak.demo_backend.client.admin.KeycloakTokenClient;
import it.jacopo.keycloak.demo_backend.client.admin.clients.KeycloakAdminClientsClient;
import it.jacopo.keycloak.demo_backend.dto.KeycloakRoleDTO;
import it.jacopo.keycloak.demo_backend.dto.KeycloakUserRoleDTO;
import it.jacopo.keycloak.demo_backend.dto.external.KeycloakRoleExternalDTO;
import it.jacopo.keycloak.demo_backend.dto.external.RoleRepresentationExternalDTO;
import it.jacopo.keycloak.demo_backend.mapper.RoleUserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminClientsService {
    private final KeycloakTokenClient tokenClient;
    private final KeycloakAdminClientsClient keycloakAdminRealmClient;

    private final RoleUserDTOMapper keycloakRoleUserDTOMapper;

    public List<KeycloakRoleDTO> getRolesUser(String userId) {
        String token = tokenClient.getAdminAccessToken();
        List<KeycloakRoleExternalDTO> response = keycloakAdminRealmClient.getRolesUser(token, userId);

        return keycloakRoleUserDTOMapper.fromDTOExternalToDTO(response);
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

        keycloakAdminRealmClient.addRealmRolesToUser(token, userId, list);
    }

    public void removeRealmRolesToUser(String userId, List<KeycloakUserRoleDTO> roles) {
        String token = tokenClient.getAdminAccessToken();
        List<RoleRepresentationExternalDTO> list = new ArrayList<>();

        //MAPPER DA LISTA DI KeycloakUserRoleDTO A LISTA DI RoleRepresentationExternalDTO
        for(KeycloakUserRoleDTO roleDTO : roles) {
            RoleRepresentationExternalDTO roleRepresentationExternalDTO = new RoleRepresentationExternalDTO();
            roleRepresentationExternalDTO.setId(roleDTO.getRolesId());
            roleRepresentationExternalDTO.setName(roleDTO.getRolesName());
            list.add(roleRepresentationExternalDTO);

        }
        keycloakAdminRealmClient.removeRealmRolesToUser(token, userId, list);
    }
}