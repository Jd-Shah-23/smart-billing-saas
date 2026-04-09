package com.jaydeep.backend.controller;

import com.jaydeep.backend.dto.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/api/health")
    public HealthResponse getHealth()
    {
        return new HealthResponse("UP","Application is running");
    }

}
