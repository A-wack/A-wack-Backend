package com.example.musicrequestapp.domain.user.controller.dto;

import com.example.musicrequestapp.domain.music.controller.dto.DailyMusicResponse;
import com.example.musicrequestapp.domain.post.controller.dto.response.PostResponse;
import com.example.musicrequestapp.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainResponse {

    private User user;
    private List<DailyMusicResponse> dailyMusic;
    private List<PostResponse> postResponseList;

}
