package com.farm2home.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 20, message = "Name must be between 2 to 20 characters")
    private String name;

    @NotBlank(message = "Mobile is required")
    @Size(min = 10, max = 10, message = "Mobile number must be exactly 10 digits")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Mobile number must be valid 10-digit Indian number"
    )
    private String mobile;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @NotBlank(message = "Role is required")
    @Pattern(
            regexp = "FARMER|BUYER|ADMIN",
            message = "Role must be FARMER, BUYER, or ADMIN"
    )
    private String role;
}
