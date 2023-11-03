package com.example.musicrequestapp.domain.post.controller.dto.response;

import com.example.musicrequestapp.domain.post.entity.Post;
import lombok.Builder;

@Builder
public record PostResponse(Long postId, String title, String content, String url, String isSuccess) {
    public PostResponse(Post post) {
        this(post.getId(), post.getTitle(), post.getContent(), post.getUrl(), post.getIsSuccess());
    }
}
