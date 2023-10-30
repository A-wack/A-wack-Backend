package com.example.musicrequestapp.global.security.jwt;

import com.example.musicrequestapp.domain.auth.entity.RefreshToken;
import com.example.musicrequestapp.domain.auth.repository.RefreshTokenRepository;
import com.example.musicrequestapp.global.redis.RedisKey;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer";

    @Value("${auth.jwt.accessExp}")
    private Long accessExp;

    @Value("${auth.jwt.refreshExp}")
    private Long refreshExp;

    @Value("${auth.jwt.secretKey}")
    private String secretKey;

    public String generateAccessToken(String user) {
        return generateToken(user, "accessToken", accessExp);
    }

    public String generateRefreshToken(String user) {
        String token = generateToken(user, "refreshToken", refreshExp);

        refreshTokenRepository.save(RefreshToken.builder()
                .token(token)
                .user(RedisKey.REFRESH.getKey() + user)
                .expiration(refreshExp)
                .build());

        return token;
    }

    private String generateToken(String user, String type, Long expiration) {
        Date date = new Date();
        Claims claims = Jwts.claims().setSubject(user);
        claims.put("type", type);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + expiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private UserDetails createAuthenticatedUserFromClaims(Claims claims) {
        String subject = getSubject(claims);

        return new User(subject, "", Collections.emptyList());
    }

    public String getSubject(Claims claims) {
        return claims.getSubject();
    }

    public Claims parseClaims(String token){
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
    }

    public Authentication getAuthentication(String token) {
        try {
            Claims claim = parseClaims(token);
            UserDetails details = createAuthenticatedUserFromClaims(claim);
            return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
        } catch (JwtException e) {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("잘못된 JWT 서명");
        } catch (MalformedJwtException e) {
            log.error("잘못된 JWT 토큰");
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰");
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰");
        } catch (IllegalArgumentException e) {
            log.error("JWT 클레임이 비어 있습니다");
        }
        return false;
    }

    public String extractToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER);
        if(StringUtils.hasText(token) && token.startsWith(PREFIX))
            return token.substring(PREFIX.length());
        return null;
    }

}
