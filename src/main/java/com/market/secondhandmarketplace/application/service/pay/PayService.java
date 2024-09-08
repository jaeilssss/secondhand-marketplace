package com.market.secondhandmarketplace.application.service.pay;

import com.market.secondhandmarketplace.application.dto.pay.PayDto;

public interface PayService {
    public PayDto.PayResponse pay(PayDto.Pay pay);
    public boolean refund(PayDto.Refund refund);
}
