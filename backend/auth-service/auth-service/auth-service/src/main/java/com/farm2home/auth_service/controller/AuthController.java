package com.farm2home.auth_service.controller;

import com.farm2home.auth_service.dto.AuthResponse;
import com.farm2home.auth_service.dto.LoginRequest;
import com.farm2home.auth_service.dto.RegisterRequest;
import com.farm2home.auth_service.entity.User;
import com.farm2home.auth_service.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @Validated @RequestBody RegisterRequest request
            ){
        User register = authService.register(request);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Validated @RequestBody LoginRequest request
            ){
        String token = authService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
