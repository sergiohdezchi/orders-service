package com.helier.orders.orders_api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.helier.orders.orders_api.dto.request.OrderRequestDTO;
import com.helier.orders.orders_api.dto.request.ProductRequestDTO;
import com.helier.orders.orders_api.dto.response.OrderResponseDTO;
import com.helier.orders.orders_api.exception.InsufficientStockException;
import com.helier.orders.orders_api.exception.ResourceNotFoundException;
import com.helier.orders.orders_api.mapper.OrderMapper;
import com.helier.orders.orders_api.model.Item;
import com.helier.orders.orders_api.model.Order;
import com.helier.orders.orders_api.model.Product;
import com.helier.orders.orders_api.model.User;
import com.helier.orders.orders_api.repository.ItemRepository;
import com.helier.orders.orders_api.repository.OrderRepository;
import com.helier.orders.orders_api.repository.UserRepository;
import com.helier.orders.orders_api.repository.ProductRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRespository productRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {

        User user = getAuthenticatedUser();

        Order order = createOrderForUser(user);

        List<Item> savedItems = processItems(orderRequestDTO.getItems(), order);

        double totalPrice = calculateTotalPrice(savedItems);
        order.setTotalPrice(totalPrice);

        order.setItemIds(savedItems.stream()
            .map(Item::getId)
            .collect(Collectors.toList()));

        order = orderRepository.save(order);

        return orderMapper.toResponseDto(order);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDTO> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        
        return orders.map(orderMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDTO> getAllOrdersByUserId(String userId, Pageable pageable) {
        Page<Order> orders = orderRepository.findByUserId(userId, pageable);
        
        return orders.map(orderMapper::toResponseDto);
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        return userRepository.findOneByEmail(authentication.getName())
            .orElseThrow(ResourceNotFoundException::new);
    }

    private Order createOrderForUser(User user) {
        Order order = new Order();
        order.setUserId(user.getId());
        
        return orderRepository.save(order);
    }

    private List<Item> processItems(List<ProductRequestDTO> productRequestDTOs, Order order) {
        List<Item> savedItems = new ArrayList<>();
        for (ProductRequestDTO productRequestDTO : productRequestDTOs) {
            Product product = getProductById(productRequestDTO.getId());

            validateStock(product, productRequestDTO.getQuantity());

            Item item = createItem(product, productRequestDTO.getQuantity(), order.getId());
            itemRepository.save(item);
            savedItems.add(item);

            updateProductStock(product, productRequestDTO.getQuantity());
        }
        
        return savedItems;
    }

    private Product getProductById(String productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId));
    }

    private void validateStock(Product product, int requestedQuantity) {
        if (product.getStock() < requestedQuantity) {
            throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
        }
    }

    private Item createItem(Product product, int quantity, String orderId) {
        Item item = new Item();
        item.setProductId(product.getId());
        item.setOrderId(orderId);
        item.setUnitPrice(product.getPrice());
        item.setQuantity(quantity);
        
        return item;
    }

    private void updateProductStock(Product product, int quantity) {
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }


    private double calculateTotalPrice(List<Item> items) {
        return items.stream()
            .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
            .sum();
    }
}
