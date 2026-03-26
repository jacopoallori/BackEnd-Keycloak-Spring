package it.jacopo.keycloak.demo_backend._old.dto;

import lombok.Data;

@Data
public class KeycloakRoleDTO {
    private String id;
    private String name;
    private String description;
    private String composite;
    private String clientRole;
    private String containerId;
}
