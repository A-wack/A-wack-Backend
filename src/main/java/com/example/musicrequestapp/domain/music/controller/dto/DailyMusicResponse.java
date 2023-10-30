package com.example.musicrequestapp.domain.music.controller.dto;

import com.example.musicrequestapp.domain.music.entity.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DailyMusicResponse {
    private String music;

    public DailyMusicResponse(Music music) {
        this.music = music.getUrl();
    }

}
