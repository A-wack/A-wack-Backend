package com.example.musicrequestapp.domain.auth.controller.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @Size(min = 2, max = 10,
          message = "이름 형식이 올바르지 않습니다.")
    private String name;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(dsm\\.hs\\.kr)$",
            message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(min = 6, max = 100,
          message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;

}
