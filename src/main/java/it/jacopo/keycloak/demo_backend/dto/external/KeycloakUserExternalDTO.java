package it.jacopo.keycloak.demo_backend.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class KeycloakUserExternalDTO {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean emailVerified;
    private String createdTimestamp;
    private Boolean enabled;
    private Boolean totp;
    private List<String> disableableCredentialTypes;
    private List<String> requiredActions;
    private int notBefore;
    private Access access;

    @Data
    public static class Access {
        private Boolean manageGroupMembership;
        private Boolean view;
        private Boolean mapRoles;
        private Boolean impersonate;
        private Boolean manage;
    }
}
