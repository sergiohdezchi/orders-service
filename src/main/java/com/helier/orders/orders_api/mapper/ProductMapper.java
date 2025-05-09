package com.helier.orders.orders_api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.helier.orders.orders_api.dto.response.ProductResponseDTO;
import com.helier.orders.orders_api.model.Product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductMapper {

    private final ModelMapper modelMapper;

    public ProductResponseDTO toResponseDto(Product product) {
      return modelMapper.map(product, ProductResponseDTO.class);
    }

    public List<ProductResponseDTO> toResponseDtoList(List<Product> products) {
      return products.stream()
        .map(this::toResponseDto)
        .toList();
    }
}
