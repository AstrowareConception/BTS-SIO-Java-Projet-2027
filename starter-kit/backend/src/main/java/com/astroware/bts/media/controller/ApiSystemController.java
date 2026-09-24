package com.astroware.bts.media.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiSystemController {
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok", "service", "java-media-starter");
    }
}
