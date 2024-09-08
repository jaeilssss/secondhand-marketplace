package com.market.secondhandmarketplace.Infrastructure.caller.payment.validator;

import com.market.secondhandmarketplace.application.dto.pay.PayDto;

public interface PaymentValidator {

    public void validate(PayDto.Pay pay);
}
