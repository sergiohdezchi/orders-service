package com.helier.orders.orders_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDTO {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
