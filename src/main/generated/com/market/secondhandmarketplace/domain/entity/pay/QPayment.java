package com.market.secondhandmarketplace.domain.entity.pay;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = -2128925452L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPayment payment = new QPayment("payment");

    public final com.market.secondhandmarketplace.domain.entity.QAbstractEntity _super = new com.market.secondhandmarketplace.domain.entity.QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.market.secondhandmarketplace.domain.entity.member.QMember member;

    public final EnumPath<com.market.secondhandmarketplace.globals.enums.PayMethod> payMethod = createEnum("payMethod", com.market.secondhandmarketplace.globals.enums.PayMethod.class);

    public final EnumPath<com.market.secondhandmarketplace.globals.enums.PayStatus> payStatus = createEnum("payStatus", com.market.secondhandmarketplace.globals.enums.PayStatus.class);

    public final com.market.secondhandmarketplace.domain.entity.secondhand.QSecondHand secondHand;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPayment(String variable) {
        this(Payment.class, forVariable(variable), INITS);
    }

    public QPayment(Path<? extends Payment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPayment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPayment(PathMetadata metadata, PathInits inits) {
        this(Payment.class, metadata, inits);
    }

    public QPayment(Class<? extends Payment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.market.secondhandmarketplace.domain.entity.member.QMember(forProperty("member")) : null;
        this.secondHand = inits.isInitialized("secondHand") ? new com.market.secondhandmarketplace.domain.entity.secondhand.QSecondHand(forProperty("secondHand"), inits.get("secondHand")) : null;
    }

}

