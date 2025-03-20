package com.example.demo.service;

import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.dto.OrderResponseDTO;
import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public OrderResponseDTO getOrder(Long orderId) {
        // Получаем заказ из базы данных
        Order order = OrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        // Маппим сущность в DTO
        return orderMapper.orderToOrderResponseDTO(order);
    }

    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        // Маппим DTO в сущность
        Order order = orderMapper.orderRequestDTOToOrder(orderRequestDTO);

        // Сохраняем заказ
        return OrderRepository.save(order);
    }
}

