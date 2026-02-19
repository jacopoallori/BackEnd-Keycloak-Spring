package it.jacopo.keycloak.demo_backend.controller.identity_providers;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato alla gestione(mapper) degli Identity Providers.
 * permette di definire delle regole in base a determinate condizioni, esempio:
 * se l'emai fa parte del dominio "@example.com" allora assegna il ruolo "user" altrimenti assegna il ruolo "guest"
 * oppure se utilizza il provider X assegna il gruppo "gruppoX" altrimenti assegna il gruppo "gruppoY"
 *
 * Lista mapper configurata per un Identity Provider
 * Creazione di un mapper per un Identity Provider
 * Aggiornamento di un mapper per un Identity Provider
 * Eliminazione di un mapper per un Identity Provider
 */
@RestController
@RequestMapping("/api/admin/identityProviders")
public class IdentityProvidersMapperController {
    /**
     * Lista dei mapper configurati per un Identity Provider
     */
    @GetMapping("/mappers")
    public void getMappers() {

    }

    /**
     * Creazione di un mapper per un Identity Provider
     */
    @GetMapping("/mappers/create")
    public void createMapper() {

    }

    /**
     * Aggiornamento di un mapper per un Identity Provider
     */
    @PutMapping("/mappers/update")
    public void updateMapper() {

    }

    /**
     * Eliminazione di un mapper per un Identity Provider
     */
    @DeleteMapping("/mappers/delete")
    public void deleteMapper() {

    }
}