package com.helier.orders.orders_api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.helier.orders.orders_api.dto.response.ItemResponseDTO;
import com.helier.orders.orders_api.model.Item;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ItemMapper {
    private final ModelMapper modelMapper;
  
    public ItemResponseDTO toResponseDto(Item item) {
      return modelMapper.map(item, ItemResponseDTO.class);
    }
  
    public List<ItemResponseDTO> toResponseDtoList(List<Item> items) {
      return items.stream()
        .map(this::toResponseDto)
        .toList();
    }
}
