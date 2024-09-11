package com.market.secondhandmarketplace.domain.repository.member;

import com.market.secondhandmarketplace.Infrastructure.repository.member.MemberRepositoryCustom;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    public Optional<Member> findMemberByEmail(String email);
}
