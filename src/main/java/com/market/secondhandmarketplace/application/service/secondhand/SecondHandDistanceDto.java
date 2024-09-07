package com.market.secondhandmarketplace.application.service.secondhand;

import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@Builder
public class SecondHandDistanceDto {

    private SecondHand secondHand;
    private Double distance;

    @QueryProjection
    public SecondHandDistanceDto(SecondHand secondHand, Double distance) {
        this.secondHand = secondHand;
        this.distance = distance;
    }
}