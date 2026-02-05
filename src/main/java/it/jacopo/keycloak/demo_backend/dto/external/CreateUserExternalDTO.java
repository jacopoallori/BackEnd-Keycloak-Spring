package it.jacopo.keycloak.demo_backend.dto.external;

import lombok.Data;

@Data
public class CreateUserExternalDTO {
    private String username;
    private Boolean enabled;
    private String email;
    private Boolean emailVerified;
    private String firstName;
    private String lastName;
}
