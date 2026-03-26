package it.jacopo.keycloak.demo_backend._old.exception.dto;

import lombok.Data;

@Data
public class KeycloakErrorDTO {
    String error;
    String error_description;
}
