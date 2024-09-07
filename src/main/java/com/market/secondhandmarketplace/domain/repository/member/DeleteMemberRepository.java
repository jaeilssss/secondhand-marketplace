package com.market.secondhandmarketplace.domain.repository.member;

import com.market.secondhandmarketplace.Infrastructure.repository.member.DeleteMemberRepositoryCustom;
import com.market.secondhandmarketplace.domain.entity.member.DeleteMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteMemberRepository extends JpaRepository<DeleteMember, Long>, DeleteMemberRepositoryCustom {
}
