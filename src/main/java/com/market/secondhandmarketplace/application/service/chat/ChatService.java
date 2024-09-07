package com.market.secondhandmarketplace.application.service.chat;

import com.market.secondhandmarketplace.application.dto.chat.ChatDto;

public interface ChatService {

    public void saveChatMessage(ChatDto.ChatMessage chatMessage);
}
