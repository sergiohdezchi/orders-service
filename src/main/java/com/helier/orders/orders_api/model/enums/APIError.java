package com.helier.orders.orders_api.model.enums;

import org.springframework.http.HttpStatus;

public enum APIError {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "There are attributes that are not valid"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST, "The format of the request is not valid");


    private final HttpStatus httpStatus;
    private final String message;

    APIError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
    
}
