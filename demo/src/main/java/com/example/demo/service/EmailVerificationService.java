package com.example.demo.service;

import com.example.demo.entity.EmailVerificationToken;
import com.example.demo.entity.User;
import com.example.demo.repository.EmailVerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationTokenRepository tokenRepository;

    public EmailVerificationToken createVerificationToken(User user) {
        EmailVerificationToken token = EmailVerificationToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(3600)) // 1 час
                .build();
        return tokenRepository.save(token);
    }

    public boolean verifyToken(String token) {
        return tokenRepository.findByToken(token)
                .filter(t -> t.getExpiryDate().isAfter(Instant.now()))
                .map(t -> {
                    User user = t.getUser();
                    user.setEmailVerified(true);
                    tokenRepository.delete(t);
                    return true;
                }).orElse(false);
    }
}

