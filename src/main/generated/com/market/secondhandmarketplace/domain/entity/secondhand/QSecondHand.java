package com.market.secondhandmarketplace.domain.entity.secondhand;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSecondHand is a Querydsl query type for SecondHand
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSecondHand extends EntityPathBase<SecondHand> {

    private static final long serialVersionUID = -828455838L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSecondHand secondHand = new QSecondHand("secondHand");

    public final com.market.secondhandmarketplace.domain.entity.QAbstractEntity _super = new com.market.secondhandmarketplace.domain.entity.QAbstractEntity(this);

    public final com.market.secondhandmarketplace.domain.entity.category.QCategory category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.market.secondhandmarketplace.domain.entity.image.Images, com.market.secondhandmarketplace.domain.entity.image.QImages> imagesUrlList = this.<com.market.secondhandmarketplace.domain.entity.image.Images, com.market.secondhandmarketplace.domain.entity.image.QImages>createList("imagesUrlList", com.market.secondhandmarketplace.domain.entity.image.Images.class, com.market.secondhandmarketplace.domain.entity.image.QImages.class, PathInits.DIRECT2);

    public final ComparablePath<org.locationtech.jts.geom.Point> location = createComparable("location", org.locationtech.jts.geom.Point.class);

    public final com.market.secondhandmarketplace.domain.entity.member.QMember member;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSecondHand(String variable) {
        this(SecondHand.class, forVariable(variable), INITS);
    }

    public QSecondHand(Path<? extends SecondHand> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSecondHand(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSecondHand(PathMetadata metadata, PathInits inits) {
        this(SecondHand.class, metadata, inits);
    }

    public QSecondHand(Class<? extends SecondHand> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.market.secondhandmarketplace.domain.entity.category.QCategory(forProperty("category"), inits.get("category")) : null;
        this.member = inits.isInitialized("member") ? new com.market.secondhandmarketplace.domain.entity.member.QMember(forProperty("member")) : null;
    }

}

