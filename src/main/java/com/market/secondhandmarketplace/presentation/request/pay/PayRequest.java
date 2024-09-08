package com.market.secondhandmarketplace.presentation.request.pay;

import com.market.secondhandmarketplace.application.dto.pay.PayDto;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;
import com.market.secondhandmarketplace.globals.enums.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PayRequest {
    @Getter @Setter
    @Builder
    public static class Pay {
        private Long secondHandId;
        private Long memberId;
        private PayMethod payMethod;

        public PayDto.Pay toDto(Member member, SecondHand secondHand) {
            return PayDto.Pay.builder()
                    .payMethod(payMethod)
                    .member(member)
                    .secondHand(secondHand)
                    .build();
        }
    }

    @Getter @Setter
    @Builder
    public static class Refund {
        private Long payId;
        private String reason;
        private Long memberId;
        public PayDto.Refund toDto() {
            return PayDto.Refund.builder()
                    .payId(payId)
                    .memberId(memberId)
                    .reason(reason)
                    .build();
        }
    }
}
