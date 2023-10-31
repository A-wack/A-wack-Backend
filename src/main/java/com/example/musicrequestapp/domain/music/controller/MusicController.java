package com.example.musicrequestapp.domain.music.controller;

import com.example.musicrequestapp.domain.music.controller.dto.DailyMusicResponse;
import com.example.musicrequestapp.domain.music.service.DailyMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/daily-music")
@RequiredArgsConstructor
public class MusicController {
    private final DailyMusic dailyMusic;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DailyMusicResponse> showDailyMusic() {
        return dailyMusic.execute();
    }

}
