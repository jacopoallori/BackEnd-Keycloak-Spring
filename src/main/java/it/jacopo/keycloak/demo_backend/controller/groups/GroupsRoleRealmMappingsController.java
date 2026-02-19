package it.jacopo.keycloak.demo_backend.controller.groups;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato alla gestione delle role realm mappings dei gruppi.
 * Mostra quali realm roles sono assegnati ad un gruppo
 * Assegna nuovi realm roles ad un gruppo
 * Rimuove realm roles ad un gruppo
 */
@RestController
@RequestMapping("/api/admin/groups")
public class GroupsRoleRealmMappingsController {

    /**
     * Mostra quali realm roles sono assegnati ad un gruppo
     */
    @GetMapping("/{groupId}/realm")
    public void getRealmRolesGroup() {

    }

    /**
     * Assegna nuovi realm roles ad un gruppo
     */
    @PostMapping("/{groupId}/realm/{realmId}")
    public void addRealmRolesGroup() {

    }

    /**
     * Rimuove realm roles ad un gruppo
     */
    @DeleteMapping("/{groupId}/realm/{realmId}")
    public void remopveRealmRolesGroup() {

    }
}