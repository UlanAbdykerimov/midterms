package com.example.demo.dto;

import lombok.*;

import static com.example.demo.entity.OrderStatus.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusDTO {
    private Long orderId;
    private String status;
}
