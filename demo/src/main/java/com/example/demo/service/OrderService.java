package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // Create a new order
    public Order createOrder(Order order) {
        order.setStatus(OrderStatus.PENDING); // Set initial status as PENDING
        return orderRepository.save(order);
    }

    // Get order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Get all orders
    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Update order status
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null;
    }

    // Cancel order by ID
    public void cancelOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent() && order.get().getStatus() == OrderStatus.PENDING) {
            orderRepository.deleteById(orderId);
        }
    }
}
