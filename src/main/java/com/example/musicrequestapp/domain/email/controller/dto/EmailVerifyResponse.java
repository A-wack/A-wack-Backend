package com.example.musicrequestapp.domain.email.controller.dto;

import lombok.Builder;

@Builder
public record EmailVerifyResponse(String message, Boolean isSuccess) {
}
