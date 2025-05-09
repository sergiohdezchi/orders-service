package com.helier.orders.orders_api.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
    @KafkaListener(topics = "orders", groupId = "order-group")
    public void consume(String message) {
        System.out.println("Received message from Kafka: " + message);
    }
}
