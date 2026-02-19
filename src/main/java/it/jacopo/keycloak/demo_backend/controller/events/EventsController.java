package it.jacopo.keycloak.demo_backend.controller.events;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller destinato alla gestione degli eventi.
 * Get lista degli eventi
 * Get lista eventi amministrativi(utente admin) ==> Eventi tipo: Creazione utente, modifica ruolo
 * Cancellazione di un evento
 */
@RestController
@RequestMapping("/api/admin/events")
public class EventsController {

    /**
     * Get lista degli eventi a livello di utenti
     * Eventi tipo: Login, login error, Logout, Registrazione, Reset password, etc
     */
    @GetMapping("/")
    public void getEvents() {

    }

    /**
     * Get lista eventi amministrativi(utente admin)
     * Eventi tipo: Crud utente, Crud ruolo, Crud client, Crud gruppo, etc
     */
    @GetMapping("/admin")
    public void getAdminEvents() {

    }

    /**
    * elimina un evento dato il suo id
    */
    @DeleteMapping("/{eventId}/delete")
    public void deleteEvent() {

    }
}
