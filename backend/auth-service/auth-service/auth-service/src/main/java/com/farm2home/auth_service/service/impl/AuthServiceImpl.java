package com.farm2home.auth_service.service.impl;

import com.farm2home.auth_service.dto.LoginRequest;
import com.farm2home.auth_service.dto.RegisterRequest;
import com.farm2home.auth_service.entity.Role;
import com.farm2home.auth_service.entity.User;
import com.farm2home.auth_service.repository.UserRepository;
import com.farm2home.auth_service.service.AuthService;
import com.farm2home.auth_service.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
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
    public String login(LoginRequest request) {
        User user = userRepository.findByMobile(request.getMobile())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(user.getId(), user.getRole().name());
    }
}
