package com.example.musicrequestapp.domain.auth.exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class AlreadyEmailExistException extends CustomException {
    public static final CustomException EXCEPTION
            = new AlreadyEmailExistException();

    private AlreadyEmailExistException() {
        super(ErrorCode.ALREADY_EMAIL_EXIST);
    }

}
