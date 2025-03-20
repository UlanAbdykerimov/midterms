package com.example.demo.mapper;

import com.example.demo.dto.MenuItemRequestDTO;
import com.example.demo.dto.MenuItemResponseDTO;
import com.example.demo.entity.MenuItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    MenuItemResponseDTO menuItemToMenuItemResponseDTO(MenuItem menuItem);

    MenuItem menuItemRequestDTOToMenuItem(MenuItemRequestDTO menuItemRequestDTO);
}
