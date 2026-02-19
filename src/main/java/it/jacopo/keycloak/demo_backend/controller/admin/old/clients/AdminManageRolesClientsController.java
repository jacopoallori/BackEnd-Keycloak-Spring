package it.jacopo.keycloak.demo_backend.controller.admin.old.clients;

import it.jacopo.keycloak.demo_backend.dto.KeycloakRoleDTO;
import it.jacopo.keycloak.demo_backend.dto.KeycloakUserRoleDTO;
import it.jacopo.keycloak.demo_backend.service.admin.clients.AdminClientsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/clientRealm")
@RequiredArgsConstructor
public class AdminManageRolesClientsController {
    private static final Logger log = LoggerFactory.getLogger(AdminManageRolesClientsController.class);
    private final AdminClientsService adminClientService;

    //*
    //Dato un id utente ottiene i suoi ruoli a livello di Clients
    //*
    @GetMapping(path = "/{id}/getRoleUser")
    public List<KeycloakRoleDTO> getRolesUser(@PathVariable("id") String userId) {
        log.info("Controller: {}, GET - {}", "AdminManageRolesClientsController" ,"/api/admin/rolesRealm/"+userId+"/getRoleUser");

        return adminClientService.getRolesUser(userId);
    }

    //*
    //Assegna X ruoli ad un utente a livello di Clients
    //*
    @PostMapping("/{id}/addRoles")
    public void addRolesToUser(@PathVariable("id") String userId, @RequestBody List<KeycloakUserRoleDTO> listRoles) {
        log.info("Controller: {}, GET - {} chiamato", "AdminManageRolesClientsController" ,"/api/admin/rolesRealm/"+userId+"/addRoles");
        log.info("Request Body: roles={}", listRoles.toString());

        adminClientService.addRealmRolesToUser(userId, listRoles);
    }

    //*
    //Rimuovi X ruoli ad un utente a livello di Clients
    //*
    @PostMapping("/{id}/removeRoles")
    public void removeRolesToUser(@PathVariable("id") String userId, @RequestBody List<KeycloakUserRoleDTO> listRoles) {
        log.info("Controller: {}, GET - {} chiamato", "AdminManageRolesClientsController" ,"/api/admin/rolesRealm/"+userId+"/addRoles");
        log.info("Request Body: roles={}", listRoles.toString());

        adminClientService.removeRealmRolesToUser(userId, listRoles);
    }

    //*
    // Ottiene la lista per i ruoli disponibili a livello di Clients
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
