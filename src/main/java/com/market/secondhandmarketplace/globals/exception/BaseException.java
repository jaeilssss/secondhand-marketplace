package com.market.secondhandmarketplace.globals.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private String exceptionMessage;
    private HttpStatusCode httpStatusCode;
}
