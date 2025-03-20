package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {
    private Long id;
    private List<Long> menuItemIds;
    private String status;
    private LocalDateTime orderTime;
    private LocalDateTime pickupTime;
}

