package com.helier.orders.orders_api.dto.response;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private String id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    private int stock;
}
