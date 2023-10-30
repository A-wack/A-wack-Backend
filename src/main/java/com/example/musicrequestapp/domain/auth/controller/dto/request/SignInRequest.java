package com.example.musicrequestapp.domain.auth.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequest {

    @NotBlank(message = "이메일 입력 칸이 비어있습니다.")
    private String email;

    @NotBlank(message = "비밀번호 입력 칸이 비어있습니다.")
    private String password;

}
