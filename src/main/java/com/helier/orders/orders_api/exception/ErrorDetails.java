package com.helier.orders.orders_api.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}

