package com.example.demo.mapper;

import com.example.demo.entity.OrderStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {

    // Преобразование OrderStatus в строку (для API)
    String orderStatusToString(OrderStatus orderStatus);

    // Преобразование строки в OrderStatus (для внутренней работы)
    OrderStatus stringToOrderStatus(String status);
}

