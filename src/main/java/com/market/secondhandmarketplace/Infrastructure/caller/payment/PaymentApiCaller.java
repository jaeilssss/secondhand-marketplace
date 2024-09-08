package com.market.secondhandmarketplace.Infrastructure.caller.payment;

import com.market.secondhandmarketplace.application.dto.pay.PayDto;
import com.market.secondhandmarketplace.globals.enums.PayMethod;

public interface PaymentApiCaller {
    boolean support(PayMethod payMethod);

    void pay(PayDto.Pay pay);
}
