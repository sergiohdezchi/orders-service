package com.helier.orders.orders_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequestDTO {
    @NotBlank(message = "Password cannot be blank")
    @Size(max = 14, message = "Email should not exceed 12 characters")
    @Size(min = 5, message = "Email should be at least 5 characters")
    private String password;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;
}
