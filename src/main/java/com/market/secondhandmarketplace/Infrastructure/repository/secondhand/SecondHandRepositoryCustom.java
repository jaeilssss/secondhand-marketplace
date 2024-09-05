package com.market.secondhandmarketplace.Infrastructure.repository.secondhand;

import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;

import java.util.List;
import java.util.Optional;

public interface SecondHandRepositoryCustom {

    List<SecondHand> findByLocation(int page, Double latitude, Double longitude);
    List<SecondHand> findMySecondHandList(int page, Long memberId);
}