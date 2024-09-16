package com.market.secondhandmarketplace.application.service.member;

import com.market.secondhandmarketplace.application.dto.member.MemberDto;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.repository.member.DeleteMemberRepository;
import com.market.secondhandmarketplace.domain.repository.member.MemberRepository;
import com.market.secondhandmarketplace.globals.exception.BaseException;
import com.market.secondhandmarketplace.globals.error.MemberErrorCode;
import com.market.secondhandmarketplace.globals.jwt.JwtProviders;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
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
    private final JwtProviders jwtProviders;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    @Transactional
    public boolean signUp(MemberDto.SignUpMember signUpMember) {
        signUpMember.encodePassword(passwordEncoder);
        validateIsExistEmail(signUpMember.getEmail());
        memberRepository.save(signUpMember.toEntity());
        return true;
    }

    @Override
    @Cacheable(cacheNames = "member", key = "#memberId")
    public MemberDto.MemberResponse getMemberInfo(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BaseException(
                        MemberErrorCode.NOT_EXIST_MEMBER.getMessage(),
                        HttpStatus.NOT_FOUND))
                .toResponse();
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "member", allEntries = true, beforeInvocation = true, cacheManager = "cacheManager")
    public MemberDto.MemberResponse modifyMemberInfo(
            MemberDto.ModifyMember modifyMember,
            Long memberId) {
        Member member = getMember(memberId);
        member.modifyMemberInfo(modifyMember);
        return member.toResponse();
    }

    @Override
    @CacheEvict(cacheNames = "member", allEntries = true, beforeInvocation = true, cacheManager = "cacheManager")
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

    @Override
    public MemberDto.LoginResponse login(MemberDto.Login login) {
        Member member = getMemberByEmail(login.getEmail());
        validatePassword(login.getPassword(), member.getPassword());
        return jwtProviders.createToken(member.getEmail(), member.getId());
    }

    @Override
    public MemberDto.LoginResponse refresh(MemberDto.RefreshToken refreshToken) {
        if(checkRefreshToken(refreshToken.getRefreshToken())) {
            Member member = getMember(refreshToken.getMemberId());
            return jwtProviders.createToken(member.getEmail(), member.getId());
        }
        throw new BaseException(
                MemberErrorCode.INVALID_REFRESH_TOKEN.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    public boolean checkRefreshToken(String refresh) {
        return jwtProviders.validateToken(refresh);
    }
    public Member getMemberByEmail(String email) {
         return memberRepository.findMemberByEmail(email)
                 .orElseThrow(() -> new BaseException(
                         MemberErrorCode.NOT_CORRECT_EMAIL_AND_PASSWORD.getMessage(),
                         HttpStatus.BAD_REQUEST));
    }

    public void validateIsExistEmail(String email) {
        memberRepository.findMemberByEmail(email)
                .ifPresent(member -> {
                    throw new BaseException(
                            MemberErrorCode.EXIST_EMAIL.getMessage(),
                            HttpStatus.BAD_REQUEST);
                });
    }

    public void validatePassword(String encodedPassword, String password) {
        if(!passwordEncoder.matches(password, encodedPassword))
            throw new BaseException(
                    MemberErrorCode.NOT_CORRECT_EMAIL_AND_PASSWORD.getMessage(),
                    HttpStatus.BAD_REQUEST);
    }
}
