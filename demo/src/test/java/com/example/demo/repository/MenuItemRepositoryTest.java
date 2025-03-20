package com.example.demo.repository;


import com.example.demo.entity.MenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MenuItemRepositoryTest {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    @DisplayName("Получить все блюда недели")
    void testFindByIsWeeklySpecialTrue() {
        // Добавляем блюда
        MenuItem item1 = new MenuItem(null, "Плов", "Вкусный плов", 120.0, true);
        MenuItem item2 = new MenuItem(null, "Шашлык", "Шашлык из баранины", 250.0, false);
        MenuItem item3 = new MenuItem(null, "Лагман", "Традиционный лагман", 150.0, true);
        menuItemRepository.saveAll(List.of(item1, item2, item3));

        // Проверяем выборку блюд недели
        List<MenuItem> weeklySpecials = menuItemRepository.findByIsWeeklySpecialTrue();
        assertThat(weeklySpecials).hasSize(2);
        assertThat(weeklySpecials).extracting(MenuItem::getName).containsExactlyInAnyOrder("Плов", "Лагман");
    }
}

