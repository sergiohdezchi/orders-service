package com.helier.orders.orders_api.dto.response;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private UserResponseDTO user;
}
