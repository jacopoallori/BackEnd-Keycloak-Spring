package it.jacopo.keycloak.demo_backend.controller.roles;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato alla gestione dei ruoli a livello di realm.
 * Mostra quali sono i ruoli disponibili a livello di realm
 * Creazione nuovo rulo a livello di realm
 * Eliminazione nuovo ruolo a livello di realm
 */
@RestController
@RequestMapping("/api/admin/roles")
public class RolesRealmController {

    /**
     * restutuisce tutti i ruoli disponibili a livello di realm
     */
    @GetMapping("realm/getRoles")
    public void getRoles() {

    }

    /**
     * crea un nuovo ruolo a livello di realm
     */
    @PostMapping("realm/createRole")
    public void createRole() {

    }

    /**
     * elimina un ruolo a livello di realm
     */
    @DeleteMapping("realm/deleteRole")
    public void deleteRole() {

    }
}
