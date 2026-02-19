package it.jacopo.keycloak.demo_backend.controller.users;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato a gestire le relazioni di un utente a livello di realm.
 * Get dei ruoli di un utente
 * Aggiungi ruolo a utente
 * Rimuovi ruolo a utente
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserRelationsRealmController {
    /**
     * Get dei ruoli a livello di realm di un utente
     */
    @GetMapping("realm/{userId}/")
    public void getRole(){

    }

    /**
     * Aggiungi ruolo a un utente a livello di realm
     */
    @PostMapping("realm/{userId}/{groupId}")
    public void addRoleToUser(){

    }

    /**
     * Rimuovi ruolo a un utente a livello di realm
     */
    @DeleteMapping("realm/{userId}/{groupId}")
    public void removeRoleToUser() {

    }
}