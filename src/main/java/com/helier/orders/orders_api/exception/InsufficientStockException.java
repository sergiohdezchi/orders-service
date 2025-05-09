package com.helier.orders.orders_api.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException() {
        super();
    }

    public InsufficientStockException(String message) {
        super(message);
    }
}
