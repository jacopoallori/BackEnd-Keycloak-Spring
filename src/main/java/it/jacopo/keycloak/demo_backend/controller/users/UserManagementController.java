package it.jacopo.keycloak.demo_backend.controller.users;

import org.springframework.web.bind.annotation.*;

/**
 * Gestisce il ciclo di vita dell'utente(Anagrafica e stato), Azioni:
 * Get di un utente con filtri e non
 * Dettaglio di un utente
 * Crea un nuovo utente
 * Aggiorrnamento di un utente
 * Eliminazione di un utente
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserManagementController {

    /**
     * Estrazione di una lista utenti con filtri e non
     * TODO valutazione paginazione
     */
    @GetMapping("")
    public void getUtenti(){

    }

    /**
     * Estrazione dati di un utente specifico
     */
    @GetMapping("/{userId}")
    public void getUtente(){

    }

    /**
     * Creazione di un nuovo utente
     */
    @PostMapping("/create")
    public void createUtente(){

    }

    /**
     * Aggiornamento di un utente
     */
    @PutMapping("/{userId}")
    public void updateUtente(){

    }

    /**
     * Eliminazione di un utente
     */
    @DeleteMapping("/{userId}")
    public void deleteUtente(){

    }
}