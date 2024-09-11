package com.market.secondhandmarketplace.presentation.request.member;

import com.market.secondhandmarketplace.application.dto.member.MemberDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@Builder
public class MemberRequest {
    @NotBlank(message = "이름은 필수로 입력해야합니다.")
    private String name;
    @NotBlank(message = "가입하는 이메일은 필수로 입력해야합니다.")
    private String email;
    @NotBlank(message = "가입하는 비밀번호는 필수로 입력해야합니다.")
    private String password;

    public MemberDto.SignUpMember toDto() {
        return MemberDto.SignUpMember.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    @Getter @Setter
    @Builder
    public static class ModifyMember {
        @NotBlank(message = "변경하는 이메일은 필수로 입력해야합니다.")
        private String email;
        @NotBlank(message = "변경하는 이름은 필수로 입력해야합니다.")
        private String name;

        public MemberDto.ModifyMember toDto() {
            return MemberDto.ModifyMember.builder()
                    .email(email)
                    .name(name)
                    .build();
        }
    }

    @Getter @Setter
    @Builder
    public static class Login {
        @NotBlank(message = "이메일은 필수로 입력해야합니다.")
        private String email;
        @NotBlank(message = "비밀번호는 필수로 입력해야합니다.")
        private String password;

        public MemberDto.Login toDto() {
            return MemberDto.Login.builder()
                    .email(email)
                    .password(password)
                    .build();
        }
    }

    @Getter @Setter
    @Builder
    public static class RefreshToken {
        @NotNull
        private Long memberId;
        @NotBlank
        private String refreshToken;

        public MemberDto.RefreshToken toDto() {
            return MemberDto.RefreshToken.builder()
                    .memberId(memberId)
                    .refreshToken(refreshToken)
                    .build();
        }
    }
}
