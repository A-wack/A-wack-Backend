package com.example.musicrequestapp.domain.post.service;

import com.example.musicrequestapp.domain.post.controller.dto.request.PostRequest;
import com.example.musicrequestapp.domain.post.entity.Post;
import com.example.musicrequestapp.domain.post.repository.PostRepository;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UploadPost {
    private final PostRepository postRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(PostRequest request) {
        User user = userFacade.getUser();

        Post post = Post.builder()
                .title(user.getName())
                .content(request.content())
                .url(request.url())
                .user(user)
                .upload(LocalDateTime.now())
                .build();

        postRepository.save(post);
    }

}
