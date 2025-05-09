package com.helier.orders.orders_api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotNull(message = "Product ID cannot be null")
    private String id;
    @NotNull(message = "Quantity name cannot be null")
    private int quantity;
}
