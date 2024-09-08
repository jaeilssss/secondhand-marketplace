package com.market.secondhandmarketplace.globals.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentErrorCode {
    ERROR("결제에 문제가 생겼습니다. 고객센터 문의 하시길 바랍니다."),
    INVALID_PAYMENT_METHOD("잘못된 결제 방식을 선택했습니다"),
    INVALID_PAYMENT_ID("잘못된 결제 ID 입니다. (결제 이력이 존재하지않습니다)"),
    NOT_MATCH_MEMBER_ID("결제자 ID와 환불 ID가 일치하지않습니다");
    private String message;
}
