package it.jacopo.keycloak.demo_backend.controller.admin.realm;

import it.jacopo.keycloak.demo_backend.dto.KeycloakRoleDTO;
import it.jacopo.keycloak.demo_backend.dto.KeycloakUserRoleDTO;
import it.jacopo.keycloak.demo_backend.service.admin.realm.AdminRealmService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/rolesRealm")
@RequiredArgsConstructor
public class AdminManageRolesRealmController {
    private static final Logger log = LoggerFactory.getLogger(AdminManageRolesRealmController.class);
    private final AdminRealmService adminRealmService;

    @GetMapping(path = "/{id}/getRoleUser")
    public List<KeycloakRoleDTO> getRolesUser(@PathVariable("id") String userId) {
        log.info("Controller: {}, GET - {} chiamato", "AdminManageRolesRealmController" ,"/api/admin/rolesRealm/"+userId+"/getRoleUser");

        List<KeycloakRoleDTO> response = adminRealmService.getRolesUser(userId);
        log.info("Response: {}", response);
        return response;
    }

    @PostMapping("/{id}/addRoles")
    public void addRolesToUser(@PathVariable("id") String userId, @RequestBody List<KeycloakUserRoleDTO> listRoles) {
        log.info("Controller: {}, GET - {} chiamato", "AdminManageRolesRealmController" ,"/api/admin/rolesRealm/"+userId+"/addRoles");
        log.info("Request Body: roles={}", listRoles.toString());


        adminRealmService.addRealmRolesToUser(userId, listRoles);
    }

    @PostMapping("/{id}/removeRoles")
    public void removeRolesToUser(@PathVariable("id") String userId, @RequestBody List<KeycloakUserRoleDTO> listRoles) {
        log.info("Controller: {}, GET - {} chiamato", "AdminManageRolesRealmController" ,"/api/admin/rolesRealm/"+userId+"/addRoles");
        log.info("Request Body: roles={}", listRoles.toString());

        adminRealmService.removeRealmRolesToUser(userId, listRoles);
    }
}
