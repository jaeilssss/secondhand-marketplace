package com.market.secondhandmarketplace.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.secondhandmarketplace.application.dto.member.MemberDto;
import com.market.secondhandmarketplace.application.service.member.MemberService;
import com.market.secondhandmarketplace.globals.config.JpaConfig;
import com.market.secondhandmarketplace.globals.error.MemberErrorCode;
import com.market.secondhandmarketplace.globals.exception.BaseException;
import com.market.secondhandmarketplace.presentation.Response;
import com.market.secondhandmarketplace.presentation.enums.APIResponseCode;
import com.market.secondhandmarketplace.presentation.request.member.MemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@Import(JpaConfig.class)  // 필요한 설정 클래스만 임포트
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void login() {

    }

    @Test
    @DisplayName("회원가입 성공 테스트")
    void signUp() throws Exception {
        MemberRequest memberRequest =  MemberRequest.builder()
                .email("test@test.com")
                .name("test")
                .password("1234")
                .build();

        Response<Boolean> response = new Response<>(
            "SUCCESS",
            APIResponseCode.OK.getMessage(),
            true
        );

        given(memberService.signUp(any(MemberDto.SignUpMember.class))).willReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(memberRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(asJsonString(response)));
    }

    @Test
    @DisplayName("회원가입 실패 테스트 (이메일 중복)")
    void signUpFail() throws Exception {
        MemberRequest memberRequest =  MemberRequest.builder()
                .email("test@test.com")
                .name("test")
                .password("1234")
                .build();

        when(memberService.signUp(any())).thenThrow(
                new BaseException(
                        MemberErrorCode.EXIST_EMAIL.getMessage(),
                        HttpStatus.BAD_REQUEST
                ));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(memberRequest)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("회원정보 조회 테스트")
    void getMemberInfo() throws Exception {

        MemberDto.MemberResponse memberResponse = MemberDto.MemberResponse.builder()
                .id(1L)
                .email("test@test.com")
                .password("test")
                .name("test")
                .build();
        when(memberService.getMemberInfo(any())).thenReturn(memberResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/member/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원정보 수정 테스트")
    void modifyMemberInfo() throws Exception {
        MemberRequest.ModifyMember memberRequest =  MemberRequest.ModifyMember.builder()
                .email("test@test.com")
                .name("test")
                .build();

        MemberDto.MemberResponse memberResponse = MemberDto.MemberResponse.builder()
                .id(1L)
                .email("test@test.com")
                .password("test")
                .name("test")
                .build();

        when(memberService.modifyMemberInfo(any(),any())).thenReturn(memberResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/member/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(memberRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원 탈퇴 테스트")
    void deleteMember() throws Exception {

        when(memberService.deleteMember(any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/member/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void refreshToken() throws Exception {
        MemberRequest.RefreshToken refreshToken = MemberRequest.RefreshToken.builder()
                .memberId(1L)
                .refreshToken("test")
                .build();

        MemberDto.LoginResponse response = MemberDto.LoginResponse.builder()
                .refreshToken("test")
                .accessToken("test access")
                .build();

        when(memberService.refresh(any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/renew/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(refreshToken)))
                .andExpect(status().isOk())
                .andDo(print());
        

    }

    private String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}