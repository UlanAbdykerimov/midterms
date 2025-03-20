package com.example.demo.mapper;

import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.dto.OrderResponseDTO;
import com.example.demo.entity.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderMapperTest {

    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Test
    void shouldConvertOrderToOrderResponseDTO() {
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password", Role.STUDENT);
        MenuItem menuItem = new MenuItem(1L, "Pizza", "Delicious pizza", 5.99, false);
        Order order = new Order(1L, user, Arrays.asList(menuItem), OrderStatus.PENDING, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20));

        OrderResponseDTO orderResponseDTO = orderMapper.orderToOrderResponseDTO(order);

        assertThat(orderResponseDTO).isNotNull();
        assertThat(orderResponseDTO.getUserId()).isEqualTo(user.getId());
        assertThat(orderResponseDTO.getMenuItemIds()).contains(menuItem.getId());
        assertThat(orderResponseDTO.getStatus()).isEqualTo("PENDING");
    }

    @Test
    void shouldConvertOrderRequestDTOToOrder() {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(1L, Arrays.asList(1L), "PENDING", LocalDateTime.now(), LocalDateTime.now().plusMinutes(20));

        Order order = orderMapper.orderRequestDTOToOrder(orderRequestDTO);

        assertThat(order).isNotNull();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(order.getItems()).hasSize(1);
    }
}

