package it.jacopo.keycloak.demo_backend.dto.users;

import java.util.List;
import java.util.Map;

public class UserDetailResponse {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private boolean emailVerified;
    private Long createdTimestamp;
    private Map<String, List<String>> attributes;
}
