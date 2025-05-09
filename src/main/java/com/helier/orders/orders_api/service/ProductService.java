package com.helier.orders.orders_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.helier.orders.orders_api.dto.response.ProductResponseDTO;
import com.helier.orders.orders_api.mapper.ProductMapper;
import com.helier.orders.orders_api.model.Product;
import com.helier.orders.orders_api.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        
        return products.map(productMapper::toResponseDto);
    }
}
