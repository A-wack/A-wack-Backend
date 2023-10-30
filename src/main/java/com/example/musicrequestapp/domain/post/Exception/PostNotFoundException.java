package com.example.musicrequestapp.domain.post.Exception;

import com.example.musicrequestapp.global.error.ErrorCode;
import com.example.musicrequestapp.global.error.exception.CustomException;

public class PostNotFoundException extends CustomException {
    public static final CustomException EXCEPTION
            = new PostNotFoundException();

    private PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }

}
