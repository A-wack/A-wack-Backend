package com.example.musicrequestapp.domain.post.collection;

import com.example.musicrequestapp.domain.post.controller.dto.response.PostResponse;
import com.example.musicrequestapp.domain.post.entity.Post;

import java.util.List;
import java.util.Objects;

public class PostCollection {
    private final List<Post> posts;

    public PostCollection(List<Post> posts) {
        this.posts = posts;
    }

    public List<PostResponse> toPostResponses() {
        return posts.stream()
                .filter(Objects::nonNull)
                .map(PostResponse::new)
                .toList();
    }

}