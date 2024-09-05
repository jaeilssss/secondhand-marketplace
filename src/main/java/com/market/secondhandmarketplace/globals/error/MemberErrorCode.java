package com.market.secondhandmarketplace.globals.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {
    INVALID_REQUEST("유효하지않은 요청입니다."),
    NOT_EXIST_MEMBER("유저 정보가 존재하지않습니다");
    private String message;
}
