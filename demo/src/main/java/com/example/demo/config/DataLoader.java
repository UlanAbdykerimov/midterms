package com.example.demo.config;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev") // Только для профиля dev
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User student = User.builder()
                    .name("Ulan")
                    .surname("Abdykerimov")
                    .email("ulan.abdykerimov@alatoo.edu.kg")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.STUDENT)
                    .build();

            User admin = User.builder()
                    .name("Admin")
                    .surname("Adminov")
                    .email("admin1@alatoo.edu.kg")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build();

            User cashier = User.builder()
                    .name("Cashier")
                    .surname("Kassirov")
                    .email("cashier1@alatoo.edu.kg")
                    .password(passwordEncoder.encode("cashier123"))
                    .role(Role.CASHIER)
                    .build();

            userRepository.saveAll(java.util.List.of(student, admin, cashier));
        }
    }
}
