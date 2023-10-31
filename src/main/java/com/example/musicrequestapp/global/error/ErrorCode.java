package com.example.musicrequestapp.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //400
    INVALID_DATA(400, "잘못된 데이터입니다."),
    MAIL_CONFIRM_FAILED(400, "메일 전송 실패"),
    DAILY_MUSIC_IS_FULL(400, "오늘은 음악을 더 이상 저장할 수 없습니다."),
    ALREADY_AUTHENTICATED_USER(400, "이미 이메일 인증을 한 사용자입니다."),
    ALREADY_USED(400, "이미 사용된 음악"),

    //401
    PASSWORD_NOT_MATCHES(401, "비밀번호가 일치하지 않습니다."),
    USER_NOT_MATCHES(401, "유저 정보가 일치하지 않습니다."),

    //403
    FORBIDDEN(403, "권한이 없습니다."),
    FORBIDDEN_EMAIL(403, "이메일 인증을 하지 않은 사용자입니다."),

    //404
    USER_NOT_FOUND(404, "유저를 찾지 못하였습니다."),
    POST_NOT_FOUND(404, "신청 내역을 찾지 못하였습니다."),
    MUSIC_NOT_FOUND(404, "입력하신 URL에 해당하는 기상송을 찾지 못하였습니다."),

    //409
    ALREADY_USER_EXIST(409, "유저가 이미 존재합니다."),
    ALREADY_EMAIL_EXIST(409, "이 이메일을 사용 중인 유저가 존재합니다."),
    ALREADY_NAME_EXIST(409, "이 이름을 사용 중인 유저가 존재합니다."),

    //429
    TOO_MANY_REQUEST(429, "너무 많은 요청"),

    //503
    DAILY_MUSIC_IS_NOT_FULL(503, "저장된 음악이 부족합니다.");

    private final int httpStatus;
    private final String message;
}