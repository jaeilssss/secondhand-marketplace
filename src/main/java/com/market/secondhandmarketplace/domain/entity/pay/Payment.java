package com.market.secondhandmarketplace.domain.entity.pay;

import com.market.secondhandmarketplace.application.dto.pay.PayDto;
import com.market.secondhandmarketplace.domain.entity.AbstractEntity;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;
import com.market.secondhandmarketplace.globals.enums.PayMethod;
import com.market.secondhandmarketplace.globals.enums.PayStatus;
import com.market.secondhandmarketplace.globals.error.PaymentErrorCode;
import com.market.secondhandmarketplace.globals.exception.BaseException;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_hand_id")
    private SecondHand secondHand;

    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;

    public PayDto.PayResponse toResponse(int type) {
        return PayDto.PayResponse.builder()
                .id(id)
                .message(createMessage(1))
                .payDateTime(getCreatedAt())
                .price(secondHand.getPrice())
                .secondHandTitle(secondHand.getName())
                .payMethod(payMethod)
                .payStatus(payStatus)
                .build();
    }




    public String createMessage(int type) {
        if(type == 1) {
            return secondHand.getPrice() + "원 이 결제되었습니다.";
        } else{
            throw new BaseException(PaymentErrorCode.ERROR.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
