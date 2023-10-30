package com.example.musicrequestapp.domain.email.controller.dao;

import com.example.musicrequestapp.global.redis.RedisKey;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class AuthCodeDao {

    private final StringRedisTemplate stringRedisTemplate;
    private static final String EMAIL_AUTH = RedisKey.EMAIL_AUTH.getKey();

    public void saveAuthCode(String email, String authCode) {
        stringRedisTemplate.opsForValue()
                .set(EMAIL_AUTH + email,
                        authCode,
                        Duration.ofHours(3L));
    }

    public String getAuthCode(String email) {
        return stringRedisTemplate.opsForValue().get(EMAIL_AUTH + email);
    }

    public void deleteAuthCode(String email) {
        stringRedisTemplate.delete(EMAIL_AUTH + email);
    }

    public boolean hasKey(String email) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(EMAIL_AUTH + email));
    }

}