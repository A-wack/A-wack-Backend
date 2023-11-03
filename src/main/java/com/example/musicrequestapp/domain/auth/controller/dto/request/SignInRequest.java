package com.example.musicrequestapp.domain.auth.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SignInRequest(@NotBlank(message = "이메일 입력 칸이 비어있습니다.") String email,
                            @NotBlank(message = "비밀번호 입력 칸이 비어있습니다.") String password) {
}
