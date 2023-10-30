package com.example.musicrequestapp.domain.music.exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class AlreadyUsedPostException extends CustomException {
    public static final CustomException EXCEPTION = new AlreadyUsedPostException();

    private AlreadyUsedPostException() {
        super(ErrorCode.ALREADY_USED);
    }

}
