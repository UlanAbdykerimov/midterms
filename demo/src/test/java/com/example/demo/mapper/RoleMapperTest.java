package com.example.demo.mapper;

import com.example.demo.entity.Role;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleMapperTest {

    private final RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    @Test
    void shouldConvertRoleToString() {
        Role role = Role.ADMIN;

        String roleString = roleMapper.roleToString(role);

        assertThat(roleString).isEqualTo("ADMIN");
    }

    @Test
    void shouldConvertStringToRole() {
        String roleString = "ADMIN";

        Role role = roleMapper.stringToRole(roleString);

        assertThat(role).isEqualTo(Role.ADMIN);
    }
}

