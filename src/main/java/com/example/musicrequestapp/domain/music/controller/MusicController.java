package com.example.musicrequestapp.domain.music.controller;

import com.example.musicrequestapp.domain.music.controller.dto.DailyMusicResponse;
import com.example.musicrequestapp.domain.music.controller.dto.SelectRequest;
import com.example.musicrequestapp.domain.music.service.DailyMusic;
import com.example.musicrequestapp.domain.music.service.SelectMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/daily-music")
@RequiredArgsConstructor
public class MusicController {
    private final DailyMusic dailyMusic;
    private final SelectMusic selectMusic;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DailyMusicResponse> showDailyMusic() {
        return dailyMusic.execute();
    }

    //어드민 권한
    @PostMapping("/admin/select")
    @ResponseStatus(HttpStatus.CREATED)
    public void setSelectMusic(@RequestBody SelectRequest request) {
        selectMusic.execute(request);
    }

}
