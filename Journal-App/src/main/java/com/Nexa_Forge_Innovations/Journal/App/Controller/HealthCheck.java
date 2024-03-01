package com.Nexa_Forge_Innovations.Journal.App.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/health")
    public String healthCheck() {
        return "Healthy";
    }
}
