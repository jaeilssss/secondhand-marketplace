package com.market.secondhandmarketplace.domain.entity.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeleteMember is a Querydsl query type for DeleteMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeleteMember extends EntityPathBase<DeleteMember> {

    private static final long serialVersionUID = 565151085L;

    public static final QDeleteMember deleteMember = new QDeleteMember("deleteMember");

    public final com.market.secondhandmarketplace.domain.entity.QAbstractEntity _super = new com.market.secondhandmarketplace.domain.entity.QAbstractEntity(this);

    public final NumberPath<Long> beforeId = createNumber("beforeId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QDeleteMember(String variable) {
        super(DeleteMember.class, forVariable(variable));
    }

    public QDeleteMember(Path<? extends DeleteMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeleteMember(PathMetadata metadata) {
        super(DeleteMember.class, metadata);
    }

}

