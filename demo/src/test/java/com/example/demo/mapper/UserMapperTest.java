package com.example.demo.mapper;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void shouldConvertUserToUserResponseDTO() {
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password", Role.STUDENT);

        UserResponseDTO userResponseDTO = userMapper.userToUserResponseDTO(user);

        assertThat(userResponseDTO).isNotNull();
        assertThat(userResponseDTO.getName()).isEqualTo(user.getName());
        assertThat(userResponseDTO.getEmail()).isEqualTo(user.getEmail());
        assertThat(userResponseDTO.getRole()).isEqualTo("STUDENT");
    }

    @Test
    void shouldConvertUserRequestDTOToUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO("John", "Doe", "john.doe@example.com", "password", Role.STUDENT);

        User user = userMapper.userRequestDTOToUser(userRequestDTO);

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(userRequestDTO.getName());
        assertThat(user.getEmail()).isEqualTo(userRequestDTO.getEmail());
    }
}

