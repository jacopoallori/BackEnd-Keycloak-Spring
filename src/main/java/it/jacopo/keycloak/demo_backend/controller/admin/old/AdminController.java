package it.jacopo.keycloak.demo_backend.controller.admin.old;

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
    private final AdminService adminService;

    //*
    //Ottiene La lista di tutti gli utenti
    //*
    @GetMapping(path = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<KeycloakUserDTO> getUsers() {
        log.info("Chiamata Controller: {}, GET - {}", "AdminController" ,"/api/admin/getUsers");

        List<KeycloakUserDTO> response = adminService.getAllUsers();

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

        List<KeycloakUserDTO> response = adminService.getUsersFiltered(id, username, firstName, lastName, email, emailVerified, createdTimestamp, enabled,first, max);

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

        adminService.createUser(newUser);
    }

    //*
    // Aggiorna dati utente
    //*
    @PutMapping(path = "/user/update/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@PathVariable String userId, @RequestBody KeycloakUserDTO updateUser) {
        log.info("Controller: {}, PUT - {}, per UserId: {}", "AdminController" ,"/api/admin/user/update/{userId}", userId);
        log.info("Request Body: userId={}, updateUser={}", userId, updateUser.toString());

        adminService.updateUser(userId, updateUser);
    }

    //*
    // Elimina utente
    //*
    @DeleteMapping("user/delete/{userId}")
    public void deleteUser(@PathVariable String userId) {
        log.info("Controller: {}, Delete - {}, per UserId: {}", "AdminController" ,"/api/admin/user/delete/{userId}", userId);

        adminService.deleteUser(userId);
    }

    //*
    // Abilita utente
    //*
    @PutMapping("user/{userId}/enable")
    public void enableUser(@PathVariable String userId) {
        log.info("Controller: {}, Put - {}, per UserId: {}", "AdminController" ,"/api/admin/user/{userId}/enable", userId);
        adminService.setUserEnabled(userId, true);
    }

    //*
    // Disabilita utente
    //*
    @PutMapping("user/{userId}/disable")
    public void disableUser(@PathVariable String userId) {
        log.info("Controller: {}, Put - {}, per UserId: {}", "AdminController" ,"/api/admin/user/{userId}/disable", userId);
        adminService.setUserEnabled(userId, false);
    }

    //*
    // Reset Password
    //*
    @PutMapping("user/resetPassword/email/{userId}")
    //public Mono<ResponseEntity<Void>> sendResetPasswordEmail(
    public void sendResetPasswordEmail(@PathVariable String userId) {
        log.info("Controller: {}, Put - {}, per UserId: {}", "AdminController" ,"/api/admin/user/resetPassword/email/{userId}", userId);
        adminService.sendResetPasswordEmail(userId);
    }

    //*
    // Verifica mail - inviare all’utente una mail di verifica dell’indirizzo email
    //*
    @PutMapping("user/sendVerifyEmail/{userId}")
    public void sendVerifyEmail(@PathVariable String userId){
        log.info("Controller: {}, Put - {}, per UserId: {}", "AdminController" ,"/api/admin/user/sendVerifyEmail/{userId}", userId);
        adminService.sendVerifyEmail(userId);
    }

    //*
    // Verifica mail - Admin confermata indirizzo email utente
    //*
    @PutMapping("user/adminVerifyEmail/{userId}")
    public void adminVerifyEmail(@PathVariable String userId){
        log.info("Controller: {}, Put - {}, per UserId: {}", "AdminController" ,"/api/admin/user/adminVerifyEmail/{userId}", userId);
        adminService.sendVerifyEmailForAdmin(userId);
    }
}