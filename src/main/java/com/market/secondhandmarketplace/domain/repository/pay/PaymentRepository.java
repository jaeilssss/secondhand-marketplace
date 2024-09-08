package com.market.secondhandmarketplace.domain.repository.pay;

import com.market.secondhandmarketplace.Infrastructure.repository.pay.PaymentRepositoryCustom;
import com.market.secondhandmarketplace.domain.entity.pay.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentRepositoryCustom {
}
