package com.market.secondhandmarketplace.application.service.member;

import com.market.secondhandmarketplace.application.dto.member.MemberDto;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.repository.member.DeleteMemberRepository;
import com.market.secondhandmarketplace.domain.repository.member.MemberRepository;
import com.market.secondhandmarketplace.globals.exception.BaseException;
import com.market.secondhandmarketplace.globals.error.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final DeleteMemberRepository deleteMemberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public boolean signUp(MemberDto.SignUpMember signUpMember) {
        signUpMember.encodePassword(passwordEncoder);
        memberRepository.save(signUpMember.toEntity());
        return true;
    }

    @Override
    public MemberDto.MemberResponse getMemberInfo(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(
                        MemberErrorCode.NOT_EXIST_MEMBER.getMessage(),
                        HttpStatus.NOT_FOUND))
                .toResponse();
    }

    @Override
    @Transactional
    public MemberDto.MemberResponse modifyMemberInfo(
            MemberDto.ModifyMember modifyMember,
            Long memberId) {
        Member member = getMember(memberId);
        member.modifyMemberInfo(modifyMember);
        return null;
    }

    @Override
    public boolean deleteMember(Long memberId) {
        Member member = getMember(memberId);
        deleteMemberRepository.save(member.toDeleteMember());
        memberRepository.delete(member);
        return true;
    }

    @Override
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(
                        MemberErrorCode.NOT_EXIST_MEMBER.getMessage(),
                        HttpStatus.NOT_FOUND
                ));
    }

    @Override
    public List<Member> getMemberList(List<Long> memberIdList) {
        return memberRepository.getMemberList(memberIdList);
    }


}
