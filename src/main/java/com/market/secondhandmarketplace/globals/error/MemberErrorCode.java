package com.market.secondhandmarketplace.globals.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {
    INVALID_REQUEST("유효하지않은 요청입니다."),
    NOT_EXIST_MEMBER("유저 정보가 존재하지않습니다"),
    NOT_CORRECT_EMAIL_AND_PASSWORD("이메일 또는 패스워드가 잘못 되었습니다"),
    INVALID_REFRESH_TOKEN("유효하지않은 Refresh Token 입니다.");
    private String message;
}
