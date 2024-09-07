package com.market.secondhandmarketplace.presentation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum APIResponseCode {
    OK("OK");

    private final String message;
}
