package it.jacopo.keycloak.demo_backend.controller.roles;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato alla gestione dei ruoli a livello di clients.
 * Mostra quali sono i ruoli disponibili a livello di clients
 * Creazione nuovo rulo a livello di clients
 * Eliminazione nuovo ruolo a livello di clients
 */
@RestController
@RequestMapping("/api/admin/roles")
public class RolesClientsController {

    /**
     * restutuisce tutti i ruoli disponibili a livello di realm
     */
    @GetMapping("clients/getRoles")
    public void getRoles() {

    }

    /**
     * crea un nuovo ruolo a livello di realm
     */
    @PostMapping("clients/createRole")
    public void createRole() {

    }

    /**
     * elimina un ruolo a livello di realm
     */
    @DeleteMapping("clients/deleteRole")
    public void deleteRole() {

    }
}
