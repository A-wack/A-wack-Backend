package com.example.musicrequestapp.domain.music.controller.dto;

import com.example.musicrequestapp.domain.music.entity.Music;
import lombok.Builder;

@Builder
public record DailyMusicAdminResponse(String music, String user) {
    public DailyMusicAdminResponse(Music music) {
        this(music.getUrl(), music.getUser());
    }

}
