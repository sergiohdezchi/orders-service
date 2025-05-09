package com.helier.orders.orders_api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.helier.orders.orders_api.dto.request.OrderRequestDTO;
import com.helier.orders.orders_api.dto.response.OrderResponseDTO;
import com.helier.orders.orders_api.model.Item;
import com.helier.orders.orders_api.model.Order;
import com.helier.orders.orders_api.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderMapper {
    private final ModelMapper modelMapper;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    public Order toEntity(OrderRequestDTO orderRequestDTO) {
      return modelMapper.map(orderRequestDTO, Order.class);
    }
  
    public OrderResponseDTO toResponseDto(Order order) {
      OrderResponseDTO dto = modelMapper.map(order, OrderResponseDTO.class);

      List<Item> items = itemRepository.findAllById(order.getItemIds());

      dto.setItems(itemMapper.toResponseDtoList(items));
      return dto;
    }
  
    public List<OrderResponseDTO> toResponseDtoList(List<Order> orders) {
      return orders.stream()
        .map(this::toResponseDto)
        .toList();
    }
}
