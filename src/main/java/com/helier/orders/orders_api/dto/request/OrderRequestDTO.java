package com.helier.orders.orders_api.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class OrderRequestDTO {
    @NotEmpty(message = "Items cannot be empty")
    private List<ProductRequestDTO> items;
}
