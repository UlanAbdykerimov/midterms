package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void send(String to, String message) {
        System.out.println("=== Email отправлен ===");
        System.out.println("Кому: " + to);
        System.out.println("Сообщение: " + message);
        System.out.println("========================");
    }
}

