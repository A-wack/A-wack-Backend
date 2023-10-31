package com.example.musicrequestapp.domain.auth.controller;

import com.example.musicrequestapp.domain.auth.controller.dto.request.ReissueRequest;
import com.example.musicrequestapp.domain.auth.controller.dto.request.SignInRequest;
import com.example.musicrequestapp.domain.auth.controller.dto.request.SignUpRequest;
import com.example.musicrequestapp.domain.auth.controller.dto.response.SignInResponse;
import com.example.musicrequestapp.domain.auth.controller.dto.response.TokenResponse;
import com.example.musicrequestapp.domain.auth.service.ReissueToken;
import com.example.musicrequestapp.domain.auth.service.SignIn;
import com.example.musicrequestapp.domain.auth.service.SignUp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final SignIn signIn;
    private final SignUp signUp;
    private final ReissueToken reissueToken;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void setSignUp(@Valid @RequestBody SignUpRequest request) {
        signUp.execute(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponse setSignIn(@Valid @RequestBody SignInRequest request) {
        return signIn.execute(request);
    }

    @PatchMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse tokenRefresh(@RequestBody ReissueRequest request) {
        return reissueToken.execute(request);
    }

}
