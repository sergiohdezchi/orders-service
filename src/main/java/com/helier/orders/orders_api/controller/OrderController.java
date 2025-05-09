package com.helier.orders.orders_api.controller;

import java.util.List;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helier.orders.orders_api.dto.request.OrderRequestDTO;
import com.helier.orders.orders_api.dto.response.OrderResponseDTO;
import com.helier.orders.orders_api.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Validated OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponse = orderService.createOrder(orderRequestDTO);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<OrderResponseDTO>> getAllOrders(@PageableDefault(sort = "userId", size = 5) Pageable pageable, @RequestParam(required = false) String userId) {
        
        Page<OrderResponseDTO> orders = userId != null
            ? orderService.getAllOrdersByUserId(userId, pageable)
            : orderService.getAllOrders(pageable);
        
        return ResponseEntity.ok(orders);
    }
}
