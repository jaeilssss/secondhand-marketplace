package com.market.secondhandmarketplace.Infrastructure.repository.member;

import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    QMember qMember = QMember.member;

    public MemberRepositoryCustomImpl(EntityManager entityManager) {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Member> getMemberList(List<Long> memberIdList) {
        return jpaQueryFactory.selectFrom(qMember)
                .where(qMember.id.in(memberIdList))
                .fetch();
    }
}
