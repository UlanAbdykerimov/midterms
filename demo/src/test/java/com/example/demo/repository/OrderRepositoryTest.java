package com.example.demo.repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Найти заказы по пользователю")
    void testFindByUser() {
        User user = new User();
        user.setEmail("student@alatoo.edu.kg");
        userRepository.save(user);

        Order order1 = new Order(null, user, null, OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
        Order order2 = new Order(null, user, null, OrderStatus.READY, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20));
        orderRepository.saveAll(List.of(order1, order2));

        List<Order> orders = orderRepository.findByUser(user);
        assertThat(orders).hasSize(2);
    }

    @Test
    @DisplayName("Найти заказы по статусу")
    void testFindByStatus() {
        Order order1 = new Order(null, null, null, OrderStatus.READY, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20));
        Order order2 = new Order(null, null, null, OrderStatus.CANCELED, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10));
        orderRepository.saveAll(List.of(order1, order2));

        List<Order> readyOrders = orderRepository.findByStatus(OrderStatus.READY);
        assertThat(readyOrders).hasSize(1);
        assertThat(readyOrders.get(0).getStatus()).isEqualTo(OrderStatus.READY);
    }
}

