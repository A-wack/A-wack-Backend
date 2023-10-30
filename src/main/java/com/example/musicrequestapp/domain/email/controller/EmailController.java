package com.example.musicrequestapp.domain.email.controller;

import com.example.musicrequestapp.domain.email.EmailService;
import com.example.musicrequestapp.domain.email.controller.dto.EmailRequest;
import com.example.musicrequestapp.domain.email.controller.dto.EmailVerifyResponse;
import com.example.musicrequestapp.global.error.exception.TooManyRequestException;
import io.github.bucket4j.Bucket;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final Bucket bucket;

    @PostMapping("/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void sendMailForVerify(@Valid @RequestBody EmailRequest request) throws MessagingException, UnsupportedEncodingException {
        if (bucket.tryConsume(1)) {
            emailService.sendAuthCode(request);
        } else {
            throw TooManyRequestException.EXCEPTION;
        }
    }

    @PostMapping("/verify")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EmailVerifyResponse verifyEmail(@RequestParam String authCode,
                                           @Valid @RequestBody EmailRequest request) {
        return emailService.verifyEmail(authCode, request);
    }

}
