package com.example.demo.mapper;

import com.example.demo.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    // Преобразование роли в строку
    String roleToString(Role role);

    // Преобразование строки в роль
    Role stringToRole(String role);
}

