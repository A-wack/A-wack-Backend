package com.example.musicrequestapp.domain.post.Exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class UserNotMatchException extends CustomException {
    public static final CustomException EXCEPTION
            = new UserNotMatchException();

    private UserNotMatchException() {
        super(ErrorCode.USER_NOT_MATCHES);
    }

}
