package com.example.demo.mapper;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Преобразование сущности User в UserResponseDTO
    UserResponseDTO userToUserResponseDTO(User user);

    // Преобразование UserRequestDTO в сущность User
    User userRequestDTOToUser(UserRequestDTO userRequestDTO);
}

