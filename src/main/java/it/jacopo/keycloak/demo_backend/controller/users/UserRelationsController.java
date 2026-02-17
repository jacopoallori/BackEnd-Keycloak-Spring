package it.jacopo.keycloak.demo_backend.controller.users;

import org.springframework.web.bind.annotation.*;

/**
 * Relazioni dell'utente, azioni:
 * Get dei gruppi a cui appartiene
 * Get dei ruoli a cui appartiene
 * Get delle sessioni attive
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserRelationsController {

    /**
     * Get dei gruppi a cui appartiene
     */
    @GetMapping("/{userId}/groups")
    public void getGroups(){

    }

    /**
     * Get dei ruoli a cui appartiene
     */
    @GetMapping("/{userId}/roles")
    public void getRoles(){

    }

    /**
     * Get delle sessioni attive
     */
    @GetMapping("/{userId}/sessions")
    public void getSessions(){

    }
}