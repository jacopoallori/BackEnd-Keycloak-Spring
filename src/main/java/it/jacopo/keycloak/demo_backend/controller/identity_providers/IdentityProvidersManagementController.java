package it.jacopo.keycloak.demo_backend.controller.identity_providers;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato alla manipolazione degli Identity Providers.
 * Get lista di tutti gli Identity Providers configurati a livello di realm
 * Get dettaglio di un Identity Provider dato il suo id
 * Creazione di un Identity Provider
 * Aggiornamento di un Identity Provider dato il suo id
 * Eliminazione di un Identity Provider dato il suo id
 */
@RestController
@RequestMapping("/api/admin/identityProviders")
public class IdentityProvidersManagementController {

    /**
     * restutuisce tutti gli Identity Providers configurati a livello di realm
     */
    @GetMapping("/")
    public void getIdentityProviders() {

    }

    /**
     * restutuisce il dettaglio di un Identity Provider dato il suo id
     */
    @GetMapping("/{identityProviderId}")
    public void getIdentityProviderById() {

    }

    /**
     * crea un nuovo Identity Provider
     */
    @PostMapping("/create")
    public void createIdentityProvider() {

    }

    /**
     * aggiorna un Identity Provider dato il suo id
     */
    @PutMapping("/{identityProviderId}/update")
    public void updateIdentityProvider() {

    }

    /**
     * elimina un Identity Provider dato il suo id
     */
    @DeleteMapping("/{identityProviderId}/delete")
    public void deleteIdentityProvider() {

    }
}