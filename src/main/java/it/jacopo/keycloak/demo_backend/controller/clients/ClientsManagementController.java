package it.jacopo.keycloak.demo_backend.controller.clients;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato alla gestione dei clients.
 * Get lista dei clients
 * Get dettaglio client per id
 * Creazione di un client
 * Aggiornamento di un client
 * Eliminazione di un client
 */
@RestController
@RequestMapping("/api/admin/clients")
public class ClientsManagementController {

    /**
     * restutuisce tutti i clients disponibili a livello di realm
     */
    @GetMapping("/")
    public void getClients() {

    }

    /**
     * restutuisce il dettaglio di un client dato il suo id
     */
    @GetMapping("/{clientId}")
    public void getClientById() {

    }

    /**
    * crea un nuovo client
    */
    @PostMapping("/create")
    public void createClient() {

    }

    /**
     * aggiorna un client dato il suo id
     */
    @PutMapping("/{clientId}/update")
    public void updateClient() {

    }

    /**
     * elimina un client dato il suo id
     */
    @DeleteMapping("/{clientId}/delete")
    public void deleteClient() {

     }
}
