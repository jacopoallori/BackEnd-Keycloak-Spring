package it.jacopo.keycloak.demo_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/junior/azione")
    public Map<String, String> junior() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "AZIONI Ruolo JUNIOR");
        return response;
    }

    @GetMapping("/senior/azione")
    public Map<String, String> senior() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Azioni Ruolo SENIOR");
        return response;
    }

    @GetMapping("/capo/azione")
    public Map<String, String> capo() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "AZIONI Ruolo CAPO");
        return response;
    }
}