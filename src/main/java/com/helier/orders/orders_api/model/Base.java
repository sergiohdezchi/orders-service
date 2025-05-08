package com.helier.orders.orders_api.model;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Base {
    @Id
    @Field("_id")
    private ObjectId id;

    @CreatedDate
    private LocalDateTime createdAt;
}
