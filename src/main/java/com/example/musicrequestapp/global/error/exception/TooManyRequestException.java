package com.example.musicrequestapp.global.error.exception;

import com.example.musicrequestapp.global.error.ErrorCode;

public class TooManyRequestException extends CustomException {
    public static final CustomException EXCEPTION = new TooManyRequestException();

    private TooManyRequestException() {
        super(ErrorCode.TOO_MANY_REQUEST);
    }

}
