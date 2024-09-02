package com.market.secondhandmarketplace.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    String result;
    String message;
    T data;

    public static <T> Response<T> success(T data, String message) {
        return (Response<T>) Response.builder()
                .result("SUCCESS")
                .message(message)
                .data(data)
                .build();
    }

    public static <T> Response<T> success(T data) {
        return success(data, null);
    }

    public static <T> Response<T> fail(String message) {
        return (Response<T>) Response.builder()
                .result("Fail")
                .message(message)
                .build();
    }

}
