package com.helier.orders.orders_api.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "items")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) 
public class Item extends Base {
    @NotNull(message = "orderId cannot be null")
    private String orderId;
    @NotNull(message = "ProductId cannot be null")
    private String productId;
    @NotNull(message = "Quantity cannot be null")
    private int quantity;
    @NotNull(message = "UnitaryPrice cannot be null")
    private double unitPrice;
}
