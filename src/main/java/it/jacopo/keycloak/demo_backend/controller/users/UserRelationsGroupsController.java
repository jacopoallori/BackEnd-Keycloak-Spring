package it.jacopo.keycloak.demo_backend.controller.users;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato alle relazioni dell'utente, azioni per gruppi.
 * Get dei gruppi a cui appartiene
 * Aggiungi utente a un gruppo
 * Rimuovi utente da un gruppo
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserRelationsGroupsController {

    /**
     * Get dei gruppi a cui appartiene
     */
    @GetMapping("groups/{userId}/")
    public void getGroups(){

    }

    /**
     * Aggiungi utente a un gruppo
     */
    @PostMapping("groups/{userId}/{groupId}")
    public void addUserToGroup(){

    }

    /**
     * Rimuovi utente da un gruppo
     */
    @DeleteMapping("groups/{userId}/{groupId}")
    public void removeUserToGroup() {

    }
}