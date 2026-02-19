package it.jacopo.keycloak.demo_backend.controller.groups;

import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato alla gestione delle membership dei gruppi.
 * Estrazione dei membri di un gruppo
 * Aggiunta di un membro ad un gruppo
 * Rimozione di un membro da un gruppo
*/
@RestController
@RequestMapping("/api/admin/groups")
public class GroupsMembershipController {

    /**
     * Estrazione dei membri di un gruppo
     */
    @GetMapping("/{groupId}/members")
    public void getMembersGroup() {

    }

    /**
     * Aggiunta di un membro ad un gruppo
     */
    @PostMapping("/{groupId}/addMembers")
    public void addMembersGroup() {

    }

    /**
     * Rimozione di un membro da un gruppo
     */
    @DeleteMapping("/{groupId}/removeMembers")
    public void removeMembersGroup() {

    }
}