package it.jacopo.keycloak.demo_backend.controller.admin;

import it.jacopo.keycloak.demo_backend.dto.KeycloakUserDTO;
import it.jacopo.keycloak.demo_backend.dto.UserCreateDTO;
import it.jacopo.keycloak.demo_backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private final AdminService userService;

    //*
    //Ottiene La lista di tutti gli utenti
    //*
    @GetMapping(path = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<KeycloakUserDTO> getUsers() {
        log.info("Chiamata Controller: {}, GET - {}", "AdminController" ,"/api/admin/getUsers");

        List<KeycloakUserDTO> response = userService.getAllUsers();

        log.info("Response: {}", response);
        return response;
    }

    //*
    //Ricerca lista utenti con filtro
    //*
    @GetMapping(path = "/getUsers/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<KeycloakUserDTO> getUsersFiltered(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean emailVerified,
            @RequestParam(required = false) String createdTimestamp,
            @RequestParam(required = false) Boolean enabled,
            @RequestParam(defaultValue = "0") int first,
            @RequestParam(defaultValue = "50") int max
    ) {
        log.info("Chiamata Controller: {}, GET - {}, con filtri: id={}, username={}, firstName={}, lastName={}, email={}, emailVerified={}, createdTimestamp={}, enabled={}, first={}, max={}",
                "AdminController" ,
                "/api/admin/getUsers/filter",
                id, username, firstName, lastName, email, emailVerified, createdTimestamp, enabled,first, max);

        List<KeycloakUserDTO> response = userService.getUsersFiltered(id, username, firstName, lastName, email, emailVerified, createdTimestamp, enabled,first, max);

        log.info("Response: {}", response);
        return response;
    }

    //*
    // Creazione nuovo utente
    //*
    @PostMapping(path = "/user/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserCreateDTO newUser) {
        log.info("Controller: {}, GET - {}", "AdminController" ,"/api/admin/user/create");
        log.info("Request Body: roles={}", newUser.toString());

        userService.createUser(newUser);
    }

    //*
    // Aggiorna dati utente
    //*
    @PutMapping(path = "/user/update/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@PathVariable String userId, @RequestBody KeycloakUserDTO updateUser) {
        log.info("Controller: {}, PUT - {}, per UserId: {}", "AdminController" ,"/api/admin/user/update/{userId}", userId);
        log.info("Request Body: userId={}, updateUser={}", userId, updateUser.toString());

        userService.updateUser(userId, updateUser);
    }

    //*
    // Elimina utente
    //*
    @DeleteMapping("user/delete/{userId}")
    public void deleteUser(@PathVariable String userId) {
        log.info("Controller: {}, Delete - {}, per UserId: {}", "AdminController" ,"/api/admin/user/delete/{userId}", userId);

        userService.deleteUser(userId);
    }

    //*
    // Abilita/disabilita utente
    //*

    //*
    // Reset Password
    //*

    //*
    // Verifica mail
    //*
}