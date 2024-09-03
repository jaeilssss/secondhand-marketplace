package com.market.secondhandmarketplace.globals.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryErrorCode {
    NOT_EXIST_CATEGORY("카테고리가 존재하지 않습니다.");

    private String message;
}
