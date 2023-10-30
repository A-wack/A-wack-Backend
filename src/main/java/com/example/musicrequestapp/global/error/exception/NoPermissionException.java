package com.example.musicrequestapp.global.error.exception;

import com.example.musicrequestapp.global.error.ErrorCode;

public class NoPermissionException extends CustomException {
    public static final CustomException EXCEPTION
            = new NoPermissionException();

    private NoPermissionException() {
        super(ErrorCode.FORBIDDEN);
    }

}
