package com.market.secondhandmarketplace.Infrastructure.repository.chat;

import com.market.secondhandmarketplace.domain.entity.chat.Chat;

public interface ChatRepositoryCustom {

    public void saveChatMessage(Chat chat);

}
