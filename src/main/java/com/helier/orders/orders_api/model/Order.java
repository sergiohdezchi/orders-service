package com.helier.orders.orders_api.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) 
public class Order extends Base {
    @NotNull(message = "UserId cannot be null")
    private String userId;
    @NotEmpty(message = "Items cannot be empty")
    private List<String> itemIds;
    private double totalPrice;
}
