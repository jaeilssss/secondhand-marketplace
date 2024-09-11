package com.market.secondhandmarketplace.application.dto.member;

import com.market.secondhandmarketplace.domain.entity.member.Member;
import lombok.*;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberDto {

    @Getter @Setter
    @Builder
    public static class SignUpMember {
        private Long id;
        private String email;
        private String name;
        private String password;

        public Member toEntity() {
            return Member.builder()
                    .email(email)
                    .name(name)
                    .password(password)
                    .build();
        }

        public void encodePassword(PasswordEncoder passwordEncoder) {
            password = passwordEncoder.encode(password);
        }
    }

    @Getter @Setter
    @Builder
    public static class ModifyMember {
        private String email;
        private String name;
    }

    @Getter @Setter
    @Builder
    public static class Login {
        private String email;
        private String password;
    }

    @Getter @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberResponse {
        private Long id;
        private String email;
        private String name;
        private String password;
    }

    @Getter @Setter
    @Builder
    public static class LoginResponse {
        private String accessToken;
        private String refreshToken;
    }

    @Getter @Setter
    @Builder
    public static class RefreshToken {
        private Long memberId;
        private String refreshToken;
    }
}