package com.example.musicrequestapp.domain.music.controller.dto;

import com.example.musicrequestapp.domain.music.entity.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DailyMusicAdminResponse {
    private String music;
    private String user;

    public DailyMusicAdminResponse(Music music) {
        this.music = music.getUrl();
        this.user = music.getUser();
    }

}
