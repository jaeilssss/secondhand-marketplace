package com.market.secondhandmarketplace.application.dto.member;

import com.market.secondhandmarketplace.domain.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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
    public static class MemberResponse {
        private Long id;
        private String email;
        private String name;
        private String password;
    }
}