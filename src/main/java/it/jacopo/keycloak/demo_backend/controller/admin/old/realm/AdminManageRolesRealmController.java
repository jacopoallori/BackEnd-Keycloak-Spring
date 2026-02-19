package it.jacopo.keycloak.demo_backend.controller.admin.old.realm;

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

    //*
    //Dato un id utente ottiene i suoi ruoli a livello di Realm
    //*
    @GetMapping(path = "/{id}/getRoleUser")
    public List<KeycloakRoleDTO> getRolesUser(@PathVariable("id") String userId) {
        log.info("Controller: {}, GET - {} chiamato", "AdminManageRolesRealmController" ,"/api/admin/rolesRealm/"+userId+"/getRoleUser");

        List<KeycloakRoleDTO> response = adminRealmService.getRolesUser(userId);
        log.info("Response: {}", response);
        return response;
    }

    //*
    //Assegna X ruoli ad un utente a livello di Realm
    //*
    @PostMapping("/{id}/addRoles")
    public void addRolesToUser(@PathVariable("id") String userId, @RequestBody List<KeycloakUserRoleDTO> listRoles) {
        log.info("Controller: {}, GET - {} chiamato", "AdminManageRolesRealmController" ,"/api/admin/rolesRealm/"+userId+"/addRoles");
        log.info("Request Body: roles={}", listRoles.toString());


        adminRealmService.addRealmRolesToUser(userId, listRoles);
    }

    //*
    //Rimuovi X ruoli ad un utente a livello di Realm
    //*
    @PostMapping("/{id}/removeRoles")
    public void removeRolesToUser(@PathVariable("id") String userId, @RequestBody List<KeycloakUserRoleDTO> listRoles) {
        log.info("Controller: {}, GET - {} chiamato", "AdminManageRolesRealmController" ,"/api/admin/rolesRealm/"+userId+"/addRoles");
        log.info("Request Body: roles={}", listRoles.toString());

        adminRealmService.removeRealmRolesToUser(userId, listRoles);
    }

    //*
    // Ottiene la lista di tutti i ruoli disponibili a livello di Realm
    //*

    //*
    // Creazione di un nuovo ruolo a livello di Realm
    //*

    //*
    // Eliminazione di un ruolo a livello di Realm
    //*

    //*
    // Dettaglio di un ruolo a livello di Realm
    //*
}
