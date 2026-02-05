package it.jacopo.keycloak.demo_backend.dto.external;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UpdateUserExternalDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
    private Boolean emailVerified;
    private Map<String, List<String>> attributes;
}