package com.example.musicrequestapp.domain.email.controller.dto;

import jakarta.validation.constraints.Pattern;

public record EmailRequest(@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(dsm\\.hs\\.kr)$",
        message = "이메일 형식이 올바르지 않습니다.") String email) {
}
