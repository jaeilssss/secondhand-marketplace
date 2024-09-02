package com.market.secondhandmarketplace.presentation.controller.handler;

import com.market.secondhandmarketplace.globals.exception.BaseException;
import com.market.secondhandmarketplace.presentation.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity<Response> handleBaseException(BaseException ex) {
        return ResponseEntity.status(ex.getHttpStatusCode())
                .body(Response.fail(ex.getExceptionMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.fail(ex.getMessage()));

    }
}
