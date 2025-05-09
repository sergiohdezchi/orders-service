package com.helier.orders.orders_api.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class OrderResponseDTO {
    private String id;
    private String userId;
    private List<ItemResponseDTO> items;
    private Double totalPrice;
    private String createdAt;
    private String updatedAt;
}
