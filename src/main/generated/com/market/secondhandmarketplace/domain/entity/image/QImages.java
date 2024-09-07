package com.market.secondhandmarketplace.domain.entity.image;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImages is a Querydsl query type for Images
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImages extends EntityPathBase<Images> {

    private static final long serialVersionUID = 758234807L;

    public static final QImages images = new QImages("images");

    public final com.market.secondhandmarketplace.domain.entity.QAbstractEntity _super = new com.market.secondhandmarketplace.domain.entity.QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath url = createString("url");

    public QImages(String variable) {
        super(Images.class, forVariable(variable));
    }

    public QImages(Path<? extends Images> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImages(PathMetadata metadata) {
        super(Images.class, metadata);
    }

}

