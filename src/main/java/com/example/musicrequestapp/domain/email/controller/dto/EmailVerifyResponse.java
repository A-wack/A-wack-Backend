package com.example.musicrequestapp.domain.email.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailVerifyResponse {

    private String message;
    private Boolean isSuccess;

}
