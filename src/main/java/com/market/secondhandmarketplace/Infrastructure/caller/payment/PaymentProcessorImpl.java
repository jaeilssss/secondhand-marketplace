package com.market.secondhandmarketplace.Infrastructure.caller.payment;

import com.market.secondhandmarketplace.application.api.payment.PaymentProcessor;
import com.market.secondhandmarketplace.application.dto.pay.PayDto;
import com.market.secondhandmarketplace.globals.error.PaymentErrorCode;
import com.market.secondhandmarketplace.globals.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentProcessorImpl implements PaymentProcessor {
    private final List<PaymentApiCaller> paymentApiCallerList;
    @Override
    public void pay(PayDto.Pay pay) {
        PaymentApiCaller paymentApiCaller = routingApiCaller(pay);
        paymentApiCaller.pay(pay);
    }

    private PaymentApiCaller routingApiCaller(PayDto.Pay pay) {
        return paymentApiCallerList.stream()
                .filter(paymentApiCaller -> paymentApiCaller.support(pay.getPayMethod()))
                .findFirst()
                .orElseThrow(() -> new BaseException(
                        PaymentErrorCode.INVALID_PAYMENT_METHOD.getMessage(),
                        HttpStatus.BAD_REQUEST));
    }
}
