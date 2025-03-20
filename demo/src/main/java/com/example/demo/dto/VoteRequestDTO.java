package com.example.demo.dto;

import  lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteRequestDTO {
    private Long menuItemId;
}
