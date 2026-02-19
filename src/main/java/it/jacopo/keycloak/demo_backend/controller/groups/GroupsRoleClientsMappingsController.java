package it.jacopo.keycloak.demo_backend.controller.groups;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato alla gestione delle role realm mappings dei gruppi.
 * Mostra quali clients roles sono assegnati ad un gruppo
 * Assegna nuovi clients roles ad un gruppo
 * Rimuove clients roles ad un gruppo
 */
@RestController
@RequestMapping("/api/admin/groups")
public class GroupsRoleClientsMappingsController {

    /**
     * Mostra quali clients roles sono assegnati ad un gruppo
     */
    @GetMapping("/{groupId}/clients")
    public void getClientsRolesGroup() {

    }

    /**
     * Assegna nuovi clients roles ad un gruppo
     */
    @PostMapping("/{groupId}/clients/{clientId}")
    public void addClientsRolesGroup() {

    }

    /**
     * Rimuove clients roles ad un gruppo
     */
    @DeleteMapping("/{groupId}/clients/{clientId}")
    public void removeClientsRolesGroup() {

    }
}