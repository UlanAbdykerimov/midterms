package com.example.demo.mapper;

import com.example.demo.dto.MenuItemRequestDTO;
import com.example.demo.dto.MenuItemResponseDTO;
import com.example.demo.entity.MenuItem;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuItemMapperTest {

    private final MenuItemMapper menuItemMapper = Mappers.getMapper(MenuItemMapper.class);

    @Test
    void shouldConvertMenuItemToMenuItemResponseDTO() {
        MenuItem menuItem = new MenuItem(1L, "Pizza", "Delicious pizza", 5.99, true);

        MenuItemResponseDTO menuItemResponseDTO = menuItemMapper.menuItemToMenuItemResponseDTO(menuItem);

        assertThat(menuItemResponseDTO).isNotNull();
        assertThat(menuItemResponseDTO.getName()).isEqualTo(menuItem.getName());
        assertThat(menuItemResponseDTO.getDescription()).isEqualTo(menuItem.getDescription());
        assertThat(menuItemResponseDTO.getPrice()).isEqualTo(menuItem.getPrice());
    }

    @Test
    void shouldConvertMenuItemRequestDTOToMenuItem() {
        MenuItemRequestDTO menuItemRequestDTO = new MenuItemRequestDTO("Pizza", "Delicious pizza", 5.99, true);

        MenuItem menuItem = menuItemMapper.menuItemRequestDTOToMenuItem(menuItemRequestDTO);

        assertThat(menuItem).isNotNull();
        assertThat(menuItem.getName()).isEqualTo(menuItemRequestDTO.getName());
        assertThat(menuItem.getPrice()).isEqualTo(menuItemRequestDTO.getPrice());
    }
}

