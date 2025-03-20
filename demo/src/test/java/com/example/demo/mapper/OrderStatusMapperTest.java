package com.example.demo.mapper;

import com.example.demo.entity.OrderStatus;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderStatusMapperTest {

    private final OrderStatusMapper orderStatusMapper = Mappers.getMapper(OrderStatusMapper.class);

    @Test
    void shouldConvertOrderStatusToString() {
        OrderStatus orderStatus = OrderStatus.PENDING;

        String status = orderStatusMapper.orderStatusToString(orderStatus);

        assertThat(status).isEqualTo("PENDING");
    }

    @Test
    void shouldConvertStringToOrderStatus() {
        String status = "PENDING";

        OrderStatus orderStatus = orderStatusMapper.stringToOrderStatus(status);

        assertThat(orderStatus).isEqualTo(OrderStatus.PENDING);
    }
}

