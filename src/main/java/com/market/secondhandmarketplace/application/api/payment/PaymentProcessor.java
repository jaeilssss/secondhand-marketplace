package com.market.secondhandmarketplace.application.api.payment;

import com.market.secondhandmarketplace.application.dto.pay.PayDto;

public interface PaymentProcessor {
    public void pay(PayDto.Pay pay);
}
