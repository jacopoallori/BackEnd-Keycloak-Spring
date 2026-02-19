package it.jacopo.keycloak.demo_backend.controller.groups;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato alla gestione dei gruppi.
 * Get lista dei gruppi
 * Get dettaglio gruppo per id
 * Creazione di un gruppo
 * Aggiornamento di un gruppo
 * Eliminazione di un gruppo
 */
@RestController
@RequestMapping("/api/admin/groups")
public class GroupsManagementController {

    /**
     * Get lista dei gruppi
     */
    @GetMapping("")
    public void getGroups() {

    }

    /**
    * Get dettaglio gruppo per id
    */
    @GetMapping("/{groupId}")
    public void getGroup() {

    }

    /**
     * Creazione di un gruppo
     */
    @PostMapping("/create")
    public void createGroup() {

    }

    /**
     * Aggiornamento di un gruppo
     */
    @PostMapping("/{groupId}/update")
    public void updateGroup() {

    }

    /**
     * Eliminazione di un gruppo
     */
    @DeleteMapping("/{groupId}")
    public void deleteGroup() {

    }
}