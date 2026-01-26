package it.jacopo.keycloak.demo_backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoController {
    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/junior/azione")
    public Map<String, String> junior() {
        log.info("Controller: {}, GET - {}", "DemoController" ,"/api/junior/azione");

        Map<String, String> response = new HashMap<>();
        response.put("message", "AZIONI Ruolo JUNIOR");

        log.info("Response: {}", response);
        return response;
    }

    @GetMapping("/senior/azione")
    public Map<String, String> senior() {
        log.info("Controller: {}, GET - {}", "DemoController" ,"/api/senior/azione");

        Map<String, String> response = new HashMap<>();
        response.put("message", "Azioni Ruolo SENIOR");

        log.info("Response: {}", response);
        return response;
    }

    @GetMapping("/capo/azione")
    public Map<String, String> capo() {
        log.info("Controller: {}, GET - {}", "DemoController" ,"/api/capo/azione");

        Map<String, String> response = new HashMap<>();
        response.put("message", "AZIONI Ruolo CAPO");

        log.info("Response: {}", response);
        return response;
    }
}