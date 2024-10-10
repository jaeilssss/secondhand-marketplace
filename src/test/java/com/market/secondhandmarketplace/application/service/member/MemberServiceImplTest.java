package com.market.secondhandmarketplace.application.service.member;

import com.market.secondhandmarketplace.application.dto.member.MemberDto;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.repository.member.DeleteMemberRepository;
import com.market.secondhandmarketplace.domain.repository.member.MemberRepository;
import com.market.secondhandmarketplace.globals.jwt.JwtProviders;
import io.jsonwebtoken.JwtParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private DeleteMemberRepository deleteMemberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtProviders jwtProviders;
    @InjectMocks
    private MemberServiceImpl memberService;
    @Test
    @DisplayName("회원가입 성공 테스트")
    void signUp() {
        //given
        MemberDto.SignUpMember signUpMember = MemberDto.SignUpMember.builder()
                .email("test@test.com")
                .password("test")
                .name("test")
                .build();
        boolean result = memberService.signUp(signUpMember);

        verify(memberRepository).save(any(Member.class));
        assertTrue(result);

    }

    @Test
    void getMemberInfo() {

        Member member = Member.builder()
                .id(1L)
                .email("test@test.com")
                .name("test")
                .password("test")
                .build();
        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
        MemberDto.MemberResponse response = memberService.getMemberInfo(1L);
        verify(memberRepository, times(1)).findById(1L);  // 메소드가 정확히 호출되었는지 확인
        assertEquals("test@test.com", response.getEmail());
        assertEquals("test", response.getName());
        assertEquals(1L, response.getId());
    }

    @Test
    @DisplayName("회원정보 수정 테스트")
    void modifyMemberInfo() {
        MemberDto.ModifyMember modifyMember = MemberDto.ModifyMember.builder()
                .email("test@modify.com")
                .name("modify")
                .build();

        Member member = Member.builder()
                .id(1L)
                .email("test@test.com")
                .name("test")
                .password("test")
                .build();

        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
        MemberDto.MemberResponse response = memberService.modifyMemberInfo(modifyMember, 1L);
        assertEquals("test@modify.com", response.getEmail());
    }

    @Test
    void getMemberList() {
        List<Long> memberIdList = Arrays.asList(1L,2L);

        Member member1 = Member.builder()
                .id(1L)
                .email("test@test.com")
                .name("test")
                .password("test")
                .build();

        Member member2 = Member.builder()
                .id(2L)
                .email("test2@test.com")
                .name("test2")
                .password("test2")
                .build();


        List<Member> list = Arrays.asList(member1, member2);

        when(memberRepository.getMemberList(memberIdList)).thenReturn(list);

        List<Member> response = memberRepository.getMemberList(memberIdList);

        assertEquals(response, list);
    }
    
}