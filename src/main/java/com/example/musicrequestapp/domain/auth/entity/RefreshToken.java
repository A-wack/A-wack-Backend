package com.example.musicrequestapp.domain.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash
public class RefreshToken {
    @Id
    private String token;

    @Indexed
    private String user;

    @TimeToLive
    private Long expiration;

    public RefreshToken update(String token, Long expiration) {
        this.token = token;
        this.expiration = expiration;
        return this;
    }

}
