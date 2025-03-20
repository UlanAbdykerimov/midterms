package com.example.demo.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemRequestDTO {
    private String name;
    private String description;
    private Double price;
    private boolean weeklySpecial;
}

