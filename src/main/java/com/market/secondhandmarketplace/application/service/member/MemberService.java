package com.market.secondhandmarketplace.application.service.member;

import com.market.secondhandmarketplace.application.dto.member.MemberDto;

public interface MemberService {

    public boolean signUp(MemberDto.SignUpMember signUpMember);
    public MemberDto.MemberResponse getMemberInfo(Long memberId);
    public MemberDto.MemberResponse modifyMemberInfo(
            MemberDto.ModifyMember modifyMember,
            Long memberId);
    public boolean deleteMember(Long memberId);
}
