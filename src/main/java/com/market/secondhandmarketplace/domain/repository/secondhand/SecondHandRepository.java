package com.market.secondhandmarketplace.domain.repository.secondhand;

import com.market.secondhandmarketplace.Infrastructure.repository.secondhand.SecondHandRepositoryCustom;
import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondHandRepository extends JpaRepository<SecondHand, Long>, SecondHandRepositoryCustom {
}
