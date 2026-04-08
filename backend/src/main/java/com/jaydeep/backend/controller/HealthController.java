package com.jaydeep.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {
    @GetMapping("/api/health")
    public Map getHealth()
    {
        Map<String,String> res=new HashMap<>();
        res.put("status","UP");
        res.put("message","Application is running");

        return res;
    }

}
