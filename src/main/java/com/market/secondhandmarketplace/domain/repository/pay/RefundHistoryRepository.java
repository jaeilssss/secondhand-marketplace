package com.market.secondhandmarketplace.domain.repository.pay;

import com.market.secondhandmarketplace.domain.entity.pay.RefundHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundHistoryRepository extends JpaRepository<RefundHistory, Long> {
}
