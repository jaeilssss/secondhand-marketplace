package com.market.secondhandmarketplace.Infrastructure.repository.secondhand;

import com.market.secondhandmarketplace.application.service.secondhand.QSecondHandDistanceDto;
import com.market.secondhandmarketplace.application.service.secondhand.SecondHandDistanceDto;
import com.market.secondhandmarketplace.domain.entity.category.QCategory;
import com.market.secondhandmarketplace.domain.entity.member.QMember;
import com.market.secondhandmarketplace.domain.entity.secondhand.QSecondHand;
import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;
import com.market.secondhandmarketplace.globals.factory.GeometryPoint;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Repository
public class SecondHandRepositoryCustomImpl implements SecondHandRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    QSecondHand qSecondHand = QSecondHand.secondHand;
    QMember qMember = QMember.member;
    QCategory qCategory = QCategory.category;
    int PAGE_SIZE = 10;
    public SecondHandRepositoryCustomImpl(EntityManager entityManager) {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<SecondHand> findByLocation(int page, Double latitude, Double longitude) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        Point location = GeometryPoint.createPoint(latitude, longitude);
        return jpaQueryFactory.selectFrom(qSecondHand)
                .join(qSecondHand.category, qCategory).fetchJoin()
                .join(qSecondHand.member, qMember).fetchJoin()
                .where(Expressions.numberTemplate(Double.class,
                                "ST_Distance_Sphere({0}, {1})",
                                qSecondHand.location, location)
                        .loe(1000))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
    }

    @Override
    public List<SecondHand> findMySecondHandList(int page, Long memberId) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        return jpaQueryFactory.selectFrom(qSecondHand)
                .join(qSecondHand.category, qCategory).fetchJoin()
                .join(qSecondHand.member, qMember).fetchJoin()
                .where(qSecondHand.member.id.eq(memberId))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
    }
}

