package it.jacopo.keycloak.demo_backend.controller.admin;

import it.jacopo.keycloak.demo_backend.dto.KeycloakUserDTO;
import it.jacopo.keycloak.demo_backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("Chiamata Controller: {}, GET - {} chiamato", "AdminController" ,"/api/admin/getUsers");

        List<KeycloakUserDTO> response = userService.getAllUsers();

        log.info("Response: {}", response);
        return response;
    }

    //*
    //Ottiene la lisa si tutti gli utenti filtrandoli
    //*
}