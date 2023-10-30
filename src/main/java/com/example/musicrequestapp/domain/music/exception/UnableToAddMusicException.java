package com.example.musicrequestapp.domain.music.exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class UnableToAddMusicException extends CustomException {
    public static final CustomException EXCEPTION
            = new UnableToAddMusicException();

    private UnableToAddMusicException() {
        super(ErrorCode.DAILY_MUSIC_IS_FULL);
    }

}
