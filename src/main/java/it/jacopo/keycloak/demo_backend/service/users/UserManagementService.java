package it.jacopo.keycloak.demo_backend.service.users;

import it.jacopo.keycloak.demo_backend.dto.common.PagedResponse;
import it.jacopo.keycloak.demo_backend.dto.users.UserDetailResponse;
import it.jacopo.keycloak.demo_backend.dto.users.UserSummaryResponse;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    public PagedResponse<UserSummaryResponse> getUsers(int page, int size, String search, Boolean enabled) {
        int first = page * size;

        UsersResource usersResource = keycloak.realm(realm).users();

        List<UserRepresentation> users;
        int total;

        boolean hasSearch = StringUtils.hasText(search);
        boolean hasEnabledFilter = enabled != null;

        if (hasSearch || hasEnabledFilter) {
            users = usersResource.search(search, enabled, first, size);
            total = usersResource.count(search, null, null, null, null, null, enabled, null);
        } else {
            users = usersResource.list(first, size);
            total = usersResource.count();
        }

        List<UserSummaryResponse> content = users.stream()
                .map(this::toResponse)
                .toList();

        return PagedResponse.<UserSummaryResponse>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(total)
                .totalPages(calculateTotalPages(total, size))
                .first(page == 0)
                .last((page + 1) >= calculateTotalPages(total, size))
                .empty(content.isEmpty())
                .build();
    }

    private UserSummaryResponse toResponse(UserRepresentation user) {
        return UserSummaryResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .enabled(Boolean.TRUE.equals(user.isEnabled()))
                .emailVerified(Boolean.TRUE.equals(user.isEmailVerified()))
                .createdTimestamp(user.getCreatedTimestamp())
                .build();
    }

    //FALLO statico
    private int calculateTotalPages(int totalElements, int size) {
        if (totalElements == 0) {
            return 0;
        }
        return (int) Math.ceil((double) totalElements / size);
    }

    public UserDetailResponse getUserById(String userId) {
        try {
            UserRepresentation user = getUserResource(userId).toRepresentation();
            return toDetailResponse(user);
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Utente non trovato con id: " + userId,
                    ex
            );
        }
    }
}