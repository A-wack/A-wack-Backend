package com.example.musicrequestapp.domain.user.exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class UserNotFoundException extends CustomException {
    public static final CustomException EXCEPTION
            = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

}
