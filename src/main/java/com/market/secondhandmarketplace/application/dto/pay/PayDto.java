package com.market.secondhandmarketplace.application.dto.pay;

import com.market.secondhandmarketplace.domain.entity.member.Member;

import com.market.secondhandmarketplace.domain.entity.pay.Payment;
import com.market.secondhandmarketplace.domain.entity.pay.RefundHistory;
import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;
import com.market.secondhandmarketplace.globals.enums.PayMethod;
import com.market.secondhandmarketplace.globals.enums.PayStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class PayDto {
    @Getter @Setter
    @Builder
    public static class Pay {
        private Member member;
        private SecondHand secondHand;
        private PayMethod payMethod;

        public Payment toEntity() {
            return Payment.builder()
                    .member(member)
                    .secondHand(secondHand)
                    .payMethod(payMethod)
                    .build();
        }
    }

    @Getter @Setter
    @Builder
    public static class Refund {
        private Long payId;
        private String reason;
        private Long memberId;

        public RefundHistory toEntity(Payment payment) {
            return RefundHistory.builder()
                    .reason(reason)
                    .secondHand(payment.getSecondHand())
                    .member(payment.getMember())
                    .payMethod(payment.getPayMethod())
                    .build();
        }
    }

    @Getter @Setter
    @Builder
    public static class PayResponse {
        private Long id;
        private String message;
        private LocalDateTime payDateTime;
        private String secondHandTitle;
        private int price;
        private PayMethod payMethod;
        private PayStatus payStatus;
    }
}
