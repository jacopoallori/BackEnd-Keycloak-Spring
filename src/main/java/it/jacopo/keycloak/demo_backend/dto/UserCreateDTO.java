package it.jacopo.keycloak.demo_backend.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    String username;
    String firstName;
    String lastName;
    String email;
    Boolean emailVerified;
    String createdTimestamp;
    Boolean enabled;
}
