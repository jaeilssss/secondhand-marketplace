package com.market.secondhandmarketplace.presentation.controller;

import com.market.secondhandmarketplace.application.dto.pay.PayDto;
import com.market.secondhandmarketplace.application.service.member.MemberService;
import com.market.secondhandmarketplace.application.service.pay.PayService;
import com.market.secondhandmarketplace.application.service.secondhand.SecondHandService;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;
import com.market.secondhandmarketplace.presentation.Response;
import com.market.secondhandmarketplace.presentation.enums.APIResponseCode;
import com.market.secondhandmarketplace.presentation.request.pay.PayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pay")
public class PayController {
    private final MemberService memberService;
    private final SecondHandService secondHandService;
    private final PayService payService;
    @PostMapping
    public Response pay(@RequestBody PayRequest.Pay pay) {
        Member member = memberService.getMember(pay.getMemberId());
        SecondHand secondHand = secondHandService.getSecondHand(pay.getSecondHandId());

        return Response.success(
                payService.pay(pay.toDto(member, secondHand)),
                APIResponseCode.OK.getMessage()
        );
    }

    @PostMapping("/refund")
    public Response refund(@RequestBody PayRequest.Refund refund) {
        return Response.success(
                payService.refund(refund.toDto()),
                APIResponseCode.OK.getMessage()
        );
    }
}
