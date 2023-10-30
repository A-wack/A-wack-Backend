package com.example.musicrequestapp.domain.auth.exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class AccessWithoutEmailAuthenticationException extends CustomException {
    public static final CustomException EXCEPTION =
            new AccessWithoutEmailAuthenticationException();

    private AccessWithoutEmailAuthenticationException() {
        super(ErrorCode.FORBIDDEN_EMAIL);
    }

}
