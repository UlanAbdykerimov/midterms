package com.example.demo.mapper;

import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.dto.OrderResponseDTO;
import com.example.demo.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")  // componentModel = "spring" для использования в Spring
public interface OrderMapper {

    // Создание экземпляра маппера
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    // Маппинг из сущности Order в OrderResponseDTO
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "items", target = "menuItemIds")
    @Mapping(source = "status", target = "status")
    OrderResponseDTO orderToOrderResponseDTO(Order order);

    // Маппинг из OrderRequestDTO в сущность Order
    @Mapping(source = "menuItemIds", target = "items")
    Order orderRequestDTOToOrder(OrderRequestDTO orderRequestDTO);
}

