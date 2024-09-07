package com.market.secondhandmarketplace.domain.entity.member;

import com.market.secondhandmarketplace.application.dto.member.MemberDto;
import com.market.secondhandmarketplace.domain.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends AbstractEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String email;
    private String password;

    public MemberDto.MemberResponse toResponse() {
        return MemberDto.MemberResponse.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }

    public void modifyMemberInfo(MemberDto.ModifyMember modifyMember) {
        name = modifyMember.getName();
        email = modifyMember.getEmail();
    }

    public DeleteMember toDeleteMember() {
        return DeleteMember.builder()
                .beforeId(id)
                .email(email)
                .name(name)
                .build();
    }
}
