package com.example.musicrequestapp.domain.auth.exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class PasswordNotMatchException extends CustomException {
    public static final CustomException EXCEPTION =
            new PasswordNotMatchException();

    private PasswordNotMatchException() {
        super(ErrorCode.PASSWORD_NOT_MATCHES);
    }

}
