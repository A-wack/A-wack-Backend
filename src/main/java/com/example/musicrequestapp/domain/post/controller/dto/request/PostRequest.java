package com.example.musicrequestapp.domain.post.controller.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequest {

    private String content;

    @Pattern(regexp = ".*(youtube|youtu.be).*")
    private String url;

}
