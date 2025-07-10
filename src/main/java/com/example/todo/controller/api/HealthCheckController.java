package com.example.todo.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    @GetMapping
    public Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();

        response.put("status", "UP");
        response.put("message", "Application is healthy");
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));

        return response;
    }
}
