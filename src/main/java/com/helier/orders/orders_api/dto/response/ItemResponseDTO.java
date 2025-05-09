package com.helier.orders.orders_api.dto.response;

import lombok.Data;

@Data
public class ItemResponseDTO {
    private String id;
    private double unitPrice;
    private int quantity;
    private String createdAt;
    private String updatedAt;
}
