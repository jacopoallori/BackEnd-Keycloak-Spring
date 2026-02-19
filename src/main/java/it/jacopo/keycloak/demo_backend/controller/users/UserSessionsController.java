package it.jacopo.keycloak.demo_backend.controller.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller destinato a gestire le sessioni di un utente.
 * Get delle sessioni attive di un utente
 * Get delle sessioni offline di un utente
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserSessionsController {

    /**
     * Get delle sessioni attive
     * Obbiettivo: vedere le sessioni attivi di un utente (browser/dispositivi).
     */
    @GetMapping("{userId}/sessions")
    public void getUserSessions(){

    }

    /**
     * Get delle sessioni attive
     * Obbiettivo: vedere le sessioni offline di un utente (browser/dispositivi).
     */
    @GetMapping("{userId}/sessions/offline/{clientUuid}")
    public void getUserOflineSessions(){

    }

}
