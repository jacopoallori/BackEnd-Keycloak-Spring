package it.jacopo.keycloak.demo_backend.controller.users;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato a gestire le azioni operative dell'utente.
 * Abilitazione di un utente
 * Disabilitazione di un utente
 * Reset password di un utente
 * Invio email di verifica email di un utente
 * Azione che permette di verificare l'email di un utente da partet dell'amministratore
 * Logout di un utente
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserActionsController {

    /**
     * Abilitazione di un utente
     */
    @PutMapping("{userId}/enable")
    public void enableUser(){

    }

    /**
     * Disabilitazione di un utente
     */
    @PutMapping("{userId}/disable")
    public void disableUser(){

    }

    /**
     * Reset password di un utente
     */
    @PostMapping("{userId)/resetPassword")
    public void resetPassword(){

    }

    /**
     * Invio email di verifica email di un utente
     */
    @PostMapping("{userId}/sendVerifyEmail")
    public void sendVerifyEmail(){

    }

    /**
     * Azione che permette di verificare l'email di un utente da partet dell'amministratore
     */
    @PostMapping("{userId}/adminVerifyEmail")
    public void adminVerifyEmail(){

    }

    /**
     * Logout di un utente
     */
    @PostMapping("{userId}/logout")
    public void logoutUser(){

    }
}