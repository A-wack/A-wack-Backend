package com.example.musicrequestapp.domain.email.exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class AlreadyAuthenticatedUserException extends CustomException {
    public static final CustomException EXCEPTION = new AlreadyAuthenticatedUserException();

    private AlreadyAuthenticatedUserException() {
        super(ErrorCode.ALREADY_AUTHENTICATED_USER);
    }

}
