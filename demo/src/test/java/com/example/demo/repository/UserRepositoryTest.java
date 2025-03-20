package com.example.demo.repository;


import com.example.demo.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Найти пользователя по email")
    void testFindByEmail() {
        // Создаем пользователя
        User user = new User();
        user.setEmail("test@alatoo.edu.kg");
        user.setPassword("password123");
        userRepository.save(user);

        // Проверяем поиск по email
        Optional<User> foundUser = userRepository.findByEmail("test@alatoo.edu.kg");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("test@alatoo.edu.kg");
    }
}

