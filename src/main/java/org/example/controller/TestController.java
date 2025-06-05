package org.example.controller;
import lombok.RequiredArgsConstructor;
import org.example.business.ImageService;
import org.example.model.ImageRequest;
import org.example.model.ImageResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/public/test")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok(" Health check: The Media Service is running. Public test passes.");
    }

    @GetMapping("/private/test")
    public ResponseEntity<String> authCheck() {
        return ResponseEntity.ok(" Health check: The Media Service is running. Private test passes.");
    }
}
