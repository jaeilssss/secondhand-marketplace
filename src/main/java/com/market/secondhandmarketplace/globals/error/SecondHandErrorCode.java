package com.market.secondhandmarketplace.globals.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SecondHandErrorCode {
    NOT_FOUND_SECONDHAND("중고거래 게시물이 존재하지 않습니다.");
    private String message;
}
