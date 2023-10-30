package com.example.musicrequestapp.domain.user.controller;

import com.example.musicrequestapp.domain.post.controller.dto.response.PostResponse;
import com.example.musicrequestapp.domain.post.service.MyPostList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class UserController {
    private final MyPostList myPostList;

    @GetMapping("/song-list")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> setMyPostList() {
        return myPostList.execute();
    }

}
