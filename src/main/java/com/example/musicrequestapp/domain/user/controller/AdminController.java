package com.example.musicrequestapp.domain.user.controller;

import com.example.musicrequestapp.domain.post.controller.dto.response.PostResponse;
import com.example.musicrequestapp.domain.post.service.AllPostList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AllPostList allPostList;

    @GetMapping("/song-list")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> setAllPostList() {
        return allPostList.execute();
    }

}
