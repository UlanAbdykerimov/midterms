package com.example.demo.controller;

import com.example.demo.dto.RefreshTokenRequestDTO;
import com.example.demo.dto.RefreshTokenResponseDTO;
import com.example.demo.entity.RefreshToken;
import com.example.demo.service.JwtService;
import com.example.demo.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDTO request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtService.generateToken(user);
                    return ResponseEntity.ok(new RefreshTokenResponseDTO(token, requestRefreshToken));
                })
                .orElseThrow(() -> new RuntimeException("Refresh token not found!"));
    }
}
