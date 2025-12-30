package com.university.auth.controller;

import com.university.auth.dto.LoginRequest;
import com.university.auth.dto.RegisterRequest;
import com.university.auth.entity.User;
import com.university.auth.security.JwtUtil;
import com.university.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        return JwtUtil.generateToken(user.getEmail(), user.getRole());
    }

}

