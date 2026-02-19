package it.jacopo.keycloak.demo_backend.controller.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller destinato al monitoraggio di audit.
 * Traccia le azioni di un utente
 * Storico accessi di un utente
 * Storico accessi falliti di un utente
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserAuditController {

    /**
     * Azioni di un utente (browser/dispositivi).
     */
    @GetMapping("/{userId}/events")
    public void getUserEvents(){

    }

    /**
     * Storico login attivi dell’utente (browser/dispositivi).
     */
    @GetMapping("/{userId}/loginHistory")
    public void getLoginHistory(){

    }

    /**
     * Storico login falliti da un utente
     */
    @GetMapping("/{userId}/loginFailures")
    public void getLoginFailures(){

    }
}