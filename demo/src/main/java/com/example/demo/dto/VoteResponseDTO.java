package com.example.demo.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteResponseDTO {
    private Long id;
    private Long userId;
    private Long menuItemId;
    private LocalDateTime voteTime;
}

