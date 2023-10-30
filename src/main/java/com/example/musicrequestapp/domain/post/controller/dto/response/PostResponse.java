package com.example.musicrequestapp.domain.post.controller.dto.response;

import com.example.musicrequestapp.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Long postId;
    private String title;
    private String content;
    private String url;
    private String isSuccess;

    public PostResponse(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.url = post.getUrl();
        this.isSuccess = post.getIsSuccess();
    }
}
