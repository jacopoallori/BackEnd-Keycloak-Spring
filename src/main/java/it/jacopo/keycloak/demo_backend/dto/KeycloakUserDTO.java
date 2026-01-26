package it.jacopo.keycloak.demo_backend.dto;

import lombok.Data;

@Data
public class KeycloakUserDTO {
    private String id;
    private String username;
    private String email;
    private Boolean enabled;
}
