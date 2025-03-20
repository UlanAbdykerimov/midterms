package com.example.demo.controller;

import com.example.demo.entity.MenuItem;
import com.example.demo.service.MenuItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class MenuItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MenuItemService menuItemService;

    @InjectMocks
    private MenuItemController menuItemController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(menuItemController).build();
    }

    @Test
    void testCreateMenuItem() throws Exception {
        MenuItem menuItem = new MenuItem(1L, "Burger", "Tasty burger", 5.99, false);
        Mockito.when(menuItemService.addMenuItem(any(MenuItem.class))).thenReturn(menuItem);

        mockMvc.perform(post("/api/menu-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Burger\", \"description\": \"Tasty burger\", \"price\": 5.99, \"weeklySpecial\": false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Burger"));
    }

    @Test
    void testGetMenuItemById() throws Exception {
        MenuItem menuItem = new MenuItem(1L, "Pizza", "Cheese pizza", 8.99, true);
        Mockito.when(menuItemService.getMenuItemById(1L)).thenReturn(Optional.of(menuItem));

        mockMvc.perform(get("/api/menu-items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pizza"));
    }

    @Test
    void testGetAllMenuItems() throws Exception {
        MenuItem menuItem1 = new MenuItem(1L, "Pizza", "Cheese pizza", 8.99, true);
        MenuItem menuItem2 = new MenuItem(2L, "Pasta", "Creamy pasta", 7.49, false);
        Mockito.when(menuItemService.getAllMenuItems()).thenReturn(Arrays.asList(menuItem1, menuItem2));

        mockMvc.perform(get("/api/menu-items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pizza"))
                .andExpect(jsonPath("$[1].name").value("Pasta"));
    }

    @Test
    void testUpdateMenuItem() throws Exception {
        MenuItem updatedMenuItem = new MenuItem(1L, "Updated Burger", "New description", 6.99, false);
        Mockito.when(menuItemService.updateMenuItem(eq(1L), any(MenuItem.class))).thenReturn(updatedMenuItem);

        mockMvc.perform(put("/api/menu-items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Burger\", \"description\": \"New description\", \"price\": 6.99, \"weeklySpecial\": false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Burger"));
    }

    @Test
    void testDeleteMenuItem() throws Exception {
        mockMvc.perform(delete("/api/menu-items/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(menuItemService).deleteMenuItem(1L);
    }
}

