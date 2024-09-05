package com.market.secondhandmarketplace.application.service.secondhand;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.market.secondhandmarketplace.application.service.secondhand.QSecondHandDistanceDto is a Querydsl Projection type for SecondHandDistanceDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSecondHandDistanceDto extends ConstructorExpression<SecondHandDistanceDto> {

    private static final long serialVersionUID = 1734241566L;

    public QSecondHandDistanceDto(com.querydsl.core.types.Expression<? extends com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand> secondHand, com.querydsl.core.types.Expression<Double> distance) {
        super(SecondHandDistanceDto.class, new Class<?>[]{com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand.class, double.class}, secondHand, distance);
    }

}

