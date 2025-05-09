package com.helier.orders.orders_api.dto.response;

import lombok.Data;

@Data
public class UserResponseDTO {
    private String id;
    private String email;
    private String role;
    private String createdAt;
    private String updatedAt;
}
