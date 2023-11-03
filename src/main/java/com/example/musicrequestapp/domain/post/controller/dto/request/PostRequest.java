package com.example.musicrequestapp.domain.post.controller.dto.request;

import jakarta.validation.constraints.Pattern;

public record PostRequest(String content,
                          @Pattern(regexp = ".*(youtube|youtu.be).*") String url) {
}
