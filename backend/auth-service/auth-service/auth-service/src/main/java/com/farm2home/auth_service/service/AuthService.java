package com.farm2home.auth_service.service;

import com.farm2home.auth_service.dto.LoginRequest;
import com.farm2home.auth_service.dto.RegisterRequest;
import com.farm2home.auth_service.entity.User;

public interface AuthService {
    public User register(RegisterRequest request);
    public User login(LoginRequest request);
}
