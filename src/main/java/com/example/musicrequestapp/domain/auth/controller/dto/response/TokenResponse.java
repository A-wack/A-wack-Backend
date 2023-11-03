package com.example.musicrequestapp.domain.auth.controller.dto.response;

import lombok.Builder;

@Builder
public record TokenResponse(String accessToken, String refreshToken) {

}