package com.market.secondhandmarketplace.domain.repository.chat;

import com.market.secondhandmarketplace.Infrastructure.repository.chat.ChatRoomRepositoryCustom;
import com.market.secondhandmarketplace.domain.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepositoryCustom {
}
