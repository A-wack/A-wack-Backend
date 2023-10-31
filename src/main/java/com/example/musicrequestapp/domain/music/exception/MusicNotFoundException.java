package com.example.musicrequestapp.domain.music.exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class MusicNotFoundException extends CustomException {
    public static final CustomException EXCEPTION =
            new MusicNotFoundException();

    private MusicNotFoundException() {
        super(ErrorCode.MUSIC_NOT_FOUND);
    }
}
