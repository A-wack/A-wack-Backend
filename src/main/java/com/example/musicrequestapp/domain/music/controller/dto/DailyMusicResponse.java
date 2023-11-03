package com.example.musicrequestapp.domain.music.controller.dto;

import com.example.musicrequestapp.domain.music.entity.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
public record DailyMusicResponse(String music) {
    public DailyMusicResponse(Music music) {
        this(music.getUrl());
    }

}
