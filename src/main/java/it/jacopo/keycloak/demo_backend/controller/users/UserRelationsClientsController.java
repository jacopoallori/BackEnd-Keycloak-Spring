package it.jacopo.keycloak.demo_backend.controller.users;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato a gestire le relazioni di un utente a livello di clients.
 * Get dei ruoli
 * Aggiunta ruolo
 * Rimozione ruolo
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserRelationsClientsController {
    /**
     * Get dei ruoli a livello di clients di un utente
     */
    @GetMapping("clients/{userId}/")
    public void getRole(){

    }

    /**
     * Aggiungi ruolo a un utente a livello di clients
     */
    @PostMapping("clients/{userId}/{groupId}")
    public void addRoleToUser(){

    }

    /**
     * Rimuovi ruolo a un utente a livello di clients
     */
    @DeleteMapping("clients/{userId}/{groupId}")
    public void removeRoleToUser() {

    }
}