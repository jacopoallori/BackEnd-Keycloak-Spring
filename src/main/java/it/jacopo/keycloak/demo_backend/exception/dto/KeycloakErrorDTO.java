package it.jacopo.keycloak.demo_backend.exception.dto;

import lombok.Data;

@Data
public class KeycloakErrorDTO {
    String error;
    String error_description;
}
