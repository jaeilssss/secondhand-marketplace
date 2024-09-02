package com.market.secondhandmarketplace.domain.repository;

import com.market.secondhandmarketplace.Infrastructure.repository.member.MemberRepositoryCustom;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
