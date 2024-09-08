package com.market.secondhandmarketplace.Infrastructure.repository.pay;

import com.market.secondhandmarketplace.domain.entity.member.QMember;
import com.market.secondhandmarketplace.domain.entity.pay.Payment;
import com.market.secondhandmarketplace.domain.entity.pay.QPayment;
import com.market.secondhandmarketplace.domain.entity.secondhand.QSecondHand;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PaymentRepositoryCustomImpl implements PaymentRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    QPayment qPayment = QPayment.payment;
    public PaymentRepositoryCustomImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<Payment> getPayment(Long payId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(qPayment)
                        .join(qPayment.member, QMember.member).fetchJoin()
                        .join(qPayment.secondHand, QSecondHand.secondHand).fetchJoin()
                        .where(qPayment.id.eq(payId))
                        .fetchOne()
        );
    }
}
