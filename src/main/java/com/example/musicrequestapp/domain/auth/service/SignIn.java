package com.example.musicrequestapp.domain.auth.service;

import com.example.musicrequestapp.domain.auth.controller.dto.request.SignInRequest;
import com.example.musicrequestapp.domain.auth.controller.dto.response.TokenResponse;
import com.example.musicrequestapp.domain.auth.exception.AccessWithoutEmailAuthenticationException;
import com.example.musicrequestapp.domain.auth.exception.PasswordNotMatchException;
import com.example.musicrequestapp.domain.user.entity.Role;
import com.example.musicrequestapp.domain.user.entity.User;
import com.example.musicrequestapp.domain.user.service.facade.UserFacade;
import com.example.musicrequestapp.global.error.exception.NoPermissionException;
import com.example.musicrequestapp.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignIn {
    private final UserFacade userFacade;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponse execute(SignInRequest request) {

        User user = userFacade.getUserByEmail(request.email());

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw PasswordNotMatchException.EXCEPTION;
        }

        if (user.getRole() == null) {
            throw NoPermissionException.EXCEPTION;
        }

        if (user.getRole() == Role.ROLE_GUEST) {
            throw AccessWithoutEmailAuthenticationException.EXCEPTION;
        }

        String accessToken = jwtProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtProvider.generateRefreshToken(user.getEmail());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

}
