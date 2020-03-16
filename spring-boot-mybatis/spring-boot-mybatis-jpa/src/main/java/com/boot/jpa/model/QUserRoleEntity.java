package com.boot.jpa.model;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.boot.jpa.entity.UserRoleEntity;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import javax.annotation.Generated;


/**
 * QUserRoleEntity is a Querydsl query type for UserRoleEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserRoleEntity extends EntityPathBase<UserRoleEntity> {

    private static final long serialVersionUID = -292041747L;

    public static final QUserRoleEntity userRoleEntity = new QUserRoleEntity("userRoleEntity");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath roleId = createString("roleId");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final StringPath userId = createString("userId");

    public QUserRoleEntity(String variable) {
        super(UserRoleEntity.class, forVariable(variable));
    }

    public QUserRoleEntity(Path<? extends UserRoleEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRoleEntity(PathMetadata metadata) {
        super(UserRoleEntity.class, metadata);
    }

}

