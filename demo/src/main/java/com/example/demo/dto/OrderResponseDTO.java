package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long id;
    private Long userId;
    private List<Long> menuItemIds;
    private String status;
    private LocalDateTime orderTime;
    private LocalDateTime pickupTime;
}

