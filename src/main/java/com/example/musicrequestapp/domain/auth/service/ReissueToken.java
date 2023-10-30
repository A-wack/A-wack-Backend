package com.example.musicrequestapp.domain.auth.service;

import com.example.musicrequestapp.domain.auth.controller.dto.request.ReissueRequest;
import com.example.musicrequestapp.domain.auth.controller.dto.response.TokenResponse;
import com.example.musicrequestapp.domain.auth.repository.RefreshTokenRepository;
import com.example.musicrequestapp.global.error.exception.NoPermissionException;
import com.example.musicrequestapp.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReissueToken {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    @Value("${auth.jwt.refreshExp}")
    private Long refreshExp;

    public TokenResponse execute(ReissueRequest request) {
        String token = request.getRefreshToken();

        return refreshTokenRepository.findById(token)
                .map(refreshToken -> {
                    String newRefreshToken = jwtProvider.generateRefreshToken(refreshToken.getUser());

                    return refreshToken.update(newRefreshToken, refreshExp);
                })
                .map(refreshTokenRepository::save)
                .map(refreshToken -> {
                    String newAccessToken = jwtProvider.generateAccessToken(refreshToken.getUser());

                    return TokenResponse.builder()
                            .accessToken(newAccessToken)
                            .refreshToken(refreshToken.getToken())
                            .build();
                })
                .orElseThrow(() -> NoPermissionException.EXCEPTION);
    }

}
