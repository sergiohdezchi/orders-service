package com.helier.orders.orders_api.dto.response;

import org.bson.types.ObjectId;
import lombok.Data;

@Data
public class UserResponseDTO {
    private ObjectId id;
    private String username;
    private String email;
    private String role;
    private String createdAt;
}
