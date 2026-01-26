package it.jacopo.keycloak.demo_backend.dto.external;

import lombok.Data;

@Data
public class KeycloakRoleExternalDTO {
    private String id;
    private String name;
    private String description;
    private String composite;
    private String clientRole;
    private String containerId;
}
