package com.example.musicrequestapp.domain.music.entity;

import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(timeToLive = 20 * 60 * 60)
public class Music {

    @Id
    private String url;

    private String user;

}
