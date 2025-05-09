package com.helier.orders.orders_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.helier.orders.orders_api.dto.response.ProductResponseDTO;
import com.helier.orders.orders_api.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    
    @GetMapping("/page")
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(@PageableDefault(size = 5) Pageable pageable) {
        Page<ProductResponseDTO> products = productService.getAllProducts(pageable);
        
        return ResponseEntity.ok(products);
    }
}
