package com.helier.orders.orders_api.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helier.orders.orders_api.dto.response.OrderResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final String TOPIC = "orders";

    public void sendOrderCreatedMessage(OrderResponseDTO orderResponseDTO) {
        try {
            String message = objectMapper.writeValueAsString(orderResponseDTO); // 🔁 conversión a JSON
            kafkaTemplate.send(TOPIC, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing OrderResponseDTO", e);
        }
    }
}
