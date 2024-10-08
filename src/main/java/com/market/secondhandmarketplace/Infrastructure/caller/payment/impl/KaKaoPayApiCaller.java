package com.market.secondhandmarketplace.Infrastructure.caller.payment.impl;

import com.market.secondhandmarketplace.Infrastructure.caller.payment.PaymentApiCaller;
import com.market.secondhandmarketplace.application.dto.pay.PayDto;
import com.market.secondhandmarketplace.globals.enums.PayMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KaKaoPayApiCaller implements PaymentApiCaller {
    @Override
    public boolean support(PayMethod payMethod) {
        return PayMethod.KAKAO_PAY == payMethod;
    }

    @Override
    public void pay(PayDto.Pay pay) {
        log.info("KaKao pay 결제");
        log.info(pay.getSecondHand().getName() + "를" +pay.getSecondHand().getPrice() + "원 결제하셨습니다.");
    }
}
