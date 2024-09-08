package com.market.secondhandmarketplace.application.service.pay;

import com.market.secondhandmarketplace.Infrastructure.caller.payment.PaymentApiCaller;
import com.market.secondhandmarketplace.application.api.payment.PaymentProcessor;
import com.market.secondhandmarketplace.application.dto.pay.PayDto;
import com.market.secondhandmarketplace.domain.entity.pay.Payment;
import com.market.secondhandmarketplace.domain.repository.pay.PaymentRepository;
import com.market.secondhandmarketplace.domain.repository.pay.RefundHistoryRepository;
import com.market.secondhandmarketplace.globals.error.PaymentErrorCode;
import com.market.secondhandmarketplace.globals.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PayServiceImpl implements PayService{
    private final PaymentRepository paymentRepository;
    private final RefundHistoryRepository refundHistoryRepository;
    private final PaymentProcessor paymentProcessor;
    @Override
    @Transactional
    public PayDto.PayResponse pay(PayDto.Pay pay) {
        paymentProcessor.pay(pay);
        return paymentRepository.save(pay.toEntity()).toResponse(1);
    }

    @Override
    @Transactional
    public boolean refund(PayDto.Refund refund) {

        Payment payment = paymentRepository.getPayment(refund.getPayId())
                .orElseThrow(() -> new BaseException(
                        PaymentErrorCode.INVALID_PAYMENT_ID.getMessage(),
                        HttpStatus.BAD_REQUEST));
        
        refundHistoryRepository.save(refund.toEntity(payment));
        validatePaymentAndMemberId(payment, refund.getMemberId());
        paymentRepository.delete(payment);
        return true;
    }

    public void validatePaymentAndMemberId(Payment payment , Long memberId) {

        if(!payment.getMember().getId().equals(memberId)) {
            throw new BaseException(
                    PaymentErrorCode.NOT_MATCH_MEMBER_ID.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
