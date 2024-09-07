package com.market.secondhandmarketplace.application.service.chat;


import com.market.secondhandmarketplace.domain.repository.chat.ChatRepository;
import com.market.secondhandmarketplace.application.dto.chat.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final ChatRepository chatRepository;

    @Override
    public void saveChatMessage(ChatDto.ChatMessage chatMessage) {
        chatRepository.save(chatMessage.toEntity());
    }
}
