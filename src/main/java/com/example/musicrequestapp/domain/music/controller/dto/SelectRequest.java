package com.example.musicrequestapp.domain.music.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SelectRequest {
    private Long postId;
    private Boolean isSuccess;

}
