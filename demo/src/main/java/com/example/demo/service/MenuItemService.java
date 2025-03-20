package com.example.demo.service;

import com.example.demo.entity.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    // Add a new menu item
    public MenuItem addMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    // Update an existing menu item
    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(id);
        if (optionalMenuItem.isPresent()) {
            menuItem.setId(id);
            return menuItemRepository.save(menuItem);
        }
        return null;
    }

    // Get menu item by ID
    public Optional<MenuItem> getMenuItemById(Long id) {
        return menuItemRepository.findById(id);
    }

    // Get all menu items
    public Iterable<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // Delete menu item by ID
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    // Check if menu item exists
    public boolean menuItemExists(Long id) {
        return menuItemRepository.findById(id).isPresent();
    }
}

