package com.example.musicrequestapp.domain.email.exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class MailSendException extends CustomException {
    public static final CustomException EXCEPTION = new MailSendException();

    private MailSendException() {
        super(ErrorCode.MAIL_CONFIRM_FAILED);
    }

}
