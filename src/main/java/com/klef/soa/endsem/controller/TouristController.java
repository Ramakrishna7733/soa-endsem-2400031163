
package com.klef.soa.endsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.endsem.entity.Tourist;
import com.klef.soa.endsem.service.TouristService;
import com.klef.soa.endsem.security.JwtUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/tourists")
@CrossOrigin("*")
public class TouristController
{
    @Autowired
    private TouristService service;

    @Autowired
    private JwtUtil jwtUtil;

    // Home API
    @GetMapping("/")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> home(
            @RequestHeader(value = "Authorization", required = false) String token)
    {
        if(!isValid(token))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or invalid token");
        }

        return ResponseEntity.ok("Tourist Management Project");
    }

    // Test API
    @GetMapping("/test")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> test(
            @RequestHeader(value = "Authorization", required = false) String token)
    {
        if(!isValid(token))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or invalid token");
        }

        return ResponseEntity.ok("Test API Call");
    }

    // Save Tourist
    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> saveTourist(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestBody Tourist tourist)
    {
        if(!isValid(token))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or invalid token");
        }

        Tourist savedTourist = service.saveTourist(tourist);

        return new ResponseEntity<>(savedTourist, HttpStatus.CREATED);
    }

    // Validate JWT Token
    private boolean isValid(String token)
    {
        if(token == null || !token.startsWith("Bearer "))
        {
            return false;
        }

        String jwt = token.substring(7);

        return jwtUtil.validateToken(jwt);
    }
}
