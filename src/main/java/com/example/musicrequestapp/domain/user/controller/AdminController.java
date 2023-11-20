package com.example.musicrequestapp.domain.user.controller;

import com.example.musicrequestapp.domain.music.controller.dto.DailyMusicAdminResponse;
import com.example.musicrequestapp.domain.music.controller.dto.SelectRequest;
import com.example.musicrequestapp.domain.music.service.DailyMusicAdmin;
import com.example.musicrequestapp.domain.music.service.DeleteMusic;
import com.example.musicrequestapp.domain.music.service.SelectMusic;
import com.example.musicrequestapp.domain.post.controller.dto.response.PostResponse;
import com.example.musicrequestapp.domain.post.service.AllPostList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AllPostList allPostList;
    private final SelectMusic selectMusic;
    private final DeleteMusic deleteMusic;
    private final DailyMusicAdmin dailyMusicAdmin;

    @GetMapping("/daily-music/list")
    @ResponseStatus(HttpStatus.OK)
    public List<DailyMusicAdminResponse> setDailyMusicAdmin() {
        return dailyMusicAdmin.execute();
    }

    @GetMapping("/request-list")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> setAllPostList(
            @PageableDefault(size = 15, sort = "upload", direction = Sort.Direction.DESC) Pageable pageable) {
        return allPostList.execute(pageable);
    }

    @PostMapping("/select")
    @ResponseStatus(HttpStatus.CREATED)
    public void setSelectMusic(@RequestBody SelectRequest request) {
        selectMusic.execute(request);
    }

    @DeleteMapping("/{url}")
    @ResponseStatus(HttpStatus.OK)
    public void setDeleteMusic(@PathVariable String url) {
        deleteMusic.execute(url);
    }

}
