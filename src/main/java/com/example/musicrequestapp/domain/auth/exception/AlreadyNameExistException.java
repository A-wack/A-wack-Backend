package com.example.musicrequestapp.domain.auth.exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class AlreadyNameExistException extends CustomException {
    public static final CustomException EXCEPTION
            = new AlreadyNameExistException();
    
    private AlreadyNameExistException() {
        super(ErrorCode.ALREADY_NAME_EXIST);
    }
    
}