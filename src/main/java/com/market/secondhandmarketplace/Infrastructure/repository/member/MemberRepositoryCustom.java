package com.market.secondhandmarketplace.Infrastructure.repository.member;

import com.market.secondhandmarketplace.domain.entity.member.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    public List<Member> getMemberList(List<Long> memberIdList);
}
