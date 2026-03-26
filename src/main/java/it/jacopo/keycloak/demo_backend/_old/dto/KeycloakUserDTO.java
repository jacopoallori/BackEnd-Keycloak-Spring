package it.jacopo.keycloak.demo_backend._old.dto;

import lombok.Data;

@Data
public class KeycloakUserDTO {
    private String id;
    private String username;
    private String email;
    private Boolean enabled;
}
