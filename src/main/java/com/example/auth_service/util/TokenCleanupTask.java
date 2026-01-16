package com.example.auth_service.util;

import com.example.auth_service.repository.RefreshTokenRepository;
import jakarta.persistence.Column;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@EnableScheduling
public class TokenCleanupTask {
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenCleanupTask(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanUpExpiredToken(){
        refreshTokenRepository.deleteByDate(Instant.now());
        System.out.println("Cleaned up expired refresh tokens from database");
    }
}
