package com.example.demo.dto;

import com.example.demo.entity.Role;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
}

