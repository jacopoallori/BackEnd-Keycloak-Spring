package it.jacopo.keycloak.demo_backend.controller.users;

import it.jacopo.keycloak.demo_backend.dto.common.PagedResponse;
import it.jacopo.keycloak.demo_backend.dto.users.UserDetailResponse;
import it.jacopo.keycloak.demo_backend.dto.users.UserSummaryResponse;
import it.jacopo.keycloak.demo_backend.service.users.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller destinato al gestire il ciclo di vita degli utenti
 * Get di un utente con filtri e non
 * Dettaglio di un utente
 * Crea un nuovo utente
 * Aggiorrnamento di un utente
 * Eliminazione di un utente
 */
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserManagementController {

    private final UserManagementService userManagementService;

    /**
     * Estrazione di una lista utenti con filtri + paginazione
     */
    @GetMapping("")
    public PagedResponse<UserSummaryResponse> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean enabled
    ){
        return userManagementService.getUsers(page, size, search, enabled);
    }

    /**
     * Estrazione dati di un utente specifico
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailResponse> getUser(@PathVariable String userId){
        return ResponseEntity.ok(userManagementService.getUserById(userId));
    }

    /**
     * Creazione di un nuovo utente
     */
    @PostMapping("")
    public void createUser(){
//        CreateUserResponse response = userManagementService.createUser(request);
//
//        return ResponseEntity
//                .created(URI.create("/api/admin/users/" + response.getUserId()))
//                .body(response);
    }

    /**
     * Aggiornamento di un utente
     */
    @PatchMapping("/{userId}")
    public void updateUser(){

    }

    /**
     * Eliminazione di un utente
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(){

    }
}