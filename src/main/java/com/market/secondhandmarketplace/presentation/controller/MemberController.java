package com.market.secondhandmarketplace.presentation.controller;

import com.market.secondhandmarketplace.application.service.member.MemberService;
import com.market.secondhandmarketplace.presentation.Response;
import com.market.secondhandmarketplace.presentation.enums.APIResponseCode;
import com.market.secondhandmarketplace.presentation.request.member.MemberRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public Response login(@RequestBody @Valid MemberRequest.Login login) {
        return Response.success(
                memberService.login(login.toDto()),
                APIResponseCode.OK.getMessage()
        );
    }

    @PostMapping
    public Response signUp(@RequestBody @Valid MemberRequest memberRequest) {
        return Response.success(
                memberService.signUp(memberRequest.toDto()),
                APIResponseCode.OK.getMessage()
        );
    }

    @GetMapping("/{memberId}")
    public Response getMemberInfo(@PathVariable("memberId") Long memberId) {
        return Response.success(
            memberService.getMemberInfo(memberId),
            APIResponseCode.OK.getMessage()
        );
    }

    @PutMapping("/{memberId}")
    public Response modifyMemberInfo(
            @PathVariable("memberId") Long memberId,
            @RequestBody @Valid MemberRequest.ModifyMember modifyMember) {
        return Response.success(
                memberService.modifyMemberInfo(modifyMember.toDto(), memberId),
                APIResponseCode.OK.getMessage()
        );
    }

    @DeleteMapping("/{memberId}")
    public Response deleteMember(@PathVariable("memberId") Long memberId) {
        return Response.success(
                memberService.deleteMember(memberId),
                APIResponseCode.OK.getMessage()
        );
    }

    @PostMapping("/renew/refresh")
    public Response refreshToken(@RequestBody @Valid MemberRequest.RefreshToken refreshToken) {
        return Response.success(
                memberService.refresh(refreshToken.toDto()),
                APIResponseCode.OK.getMessage()
        );
    }
}
