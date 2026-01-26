package it.jacopo.keycloak.demo_backend.dto;

import lombok.Data;

@Data
public class KeycloakUserRoleDTO {
    private String rolesId;
    private String rolesName;
}
