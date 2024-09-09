package com.market.secondhandmarketplace.domain.entity.pay;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRefundHistory is a Querydsl query type for RefundHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefundHistory extends EntityPathBase<RefundHistory> {

    private static final long serialVersionUID = 1499158122L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRefundHistory refundHistory = new QRefundHistory("refundHistory");

    public final com.market.secondhandmarketplace.domain.entity.QAbstractEntity _super = new com.market.secondhandmarketplace.domain.entity.QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.market.secondhandmarketplace.domain.entity.member.QMember member;

    public final EnumPath<com.market.secondhandmarketplace.globals.enums.PayMethod> payMethod = createEnum("payMethod", com.market.secondhandmarketplace.globals.enums.PayMethod.class);

    public final StringPath reason = createString("reason");

    public final com.market.secondhandmarketplace.domain.entity.secondhand.QSecondHand secondHand;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QRefundHistory(String variable) {
        this(RefundHistory.class, forVariable(variable), INITS);
    }

    public QRefundHistory(Path<? extends RefundHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRefundHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRefundHistory(PathMetadata metadata, PathInits inits) {
        this(RefundHistory.class, metadata, inits);
    }

    public QRefundHistory(Class<? extends RefundHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.market.secondhandmarketplace.domain.entity.member.QMember(forProperty("member")) : null;
        this.secondHand = inits.isInitialized("secondHand") ? new com.market.secondhandmarketplace.domain.entity.secondhand.QSecondHand(forProperty("secondHand"), inits.get("secondHand")) : null;
    }

}

