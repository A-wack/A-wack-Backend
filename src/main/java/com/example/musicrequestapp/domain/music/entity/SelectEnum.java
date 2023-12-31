package com.example.musicrequestapp.domain.music.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SelectEnum {
    SELECTED("채택되었습니다."),
    NOT_SELECTED("거부되었습니다."),
    CHECKING("확인 중");

    private final String num;
}
