
package com.klef.soa.endsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.endsem.dto.RegisterRequest;
import com.klef.soa.endsem.dto.LoginRequest;
import com.klef.soa.endsem.dto.AuthResponse;
import com.klef.soa.endsem.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController
{
    @Autowired
    private AuthService service;

    // Register User
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request)
    {
        String result = service.register(request);

        if(result.equals("Username already exists"))
        {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request)
    {
        AuthResponse response = service.login(request);

        return ResponseEntity.ok(response);
    }
}
