package com.market.secondhandmarketplace.Infrastructure.repository.pay;

import com.market.secondhandmarketplace.domain.entity.pay.Payment;

import java.util.Optional;

public interface PaymentRepositoryCustom {

    public Optional<Payment> getPayment(Long payId);
}
