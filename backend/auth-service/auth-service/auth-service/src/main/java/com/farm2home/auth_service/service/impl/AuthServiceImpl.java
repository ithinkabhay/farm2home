package com.farm2home.auth_service.service.impl;

import com.farm2home.auth_service.dto.LoginRequest;
import com.farm2home.auth_service.dto.RegisterRequest;
import com.farm2home.auth_service.entity.Role;
import com.farm2home.auth_service.entity.User;
import com.farm2home.auth_service.repository.UserRepository;
import com.farm2home.auth_service.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .mobile(request.getMobile())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public User login(LoginRequest request) {
        User user = userRepository.findByMobile(request.getMobile())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }
}
