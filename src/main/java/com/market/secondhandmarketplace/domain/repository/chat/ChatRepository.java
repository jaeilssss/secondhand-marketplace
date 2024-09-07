package com.market.secondhandmarketplace.domain.repository.chat;

import com.market.secondhandmarketplace.Infrastructure.repository.chat.ChatRepositoryCustom;
import com.market.secondhandmarketplace.domain.entity.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long>, ChatRepositoryCustom {
}
