package it.jacopo.keycloak.demo_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFilterDTO {
    String id;
    String username;
    String firstName;
    String lastName;
    String email;
    Boolean emailVerified;
    String createdTimestamp;
    Boolean enabled;
}
