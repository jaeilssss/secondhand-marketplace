package com.market.secondhandmarketplace.presentation.request.chat;

import com.market.secondhandmarketplace.application.dto.chat.ChatDto;
import com.market.secondhandmarketplace.domain.entity.chat.Chat;
import com.market.secondhandmarketplace.domain.entity.chat.ChatRoom;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ChatRequest {

    @Getter @Setter
    @Builder
    public static class ChatMessage {
        private String message;
        private Long chatRoomId;
        private Long senderId;

        public ChatDto.ChatMessage toDto(Member member, ChatRoom chatRoom) {
            return ChatDto.ChatMessage.builder()
                    .message(message)
                    .chatRoom(chatRoom)
                    .member(member)
                    .build();
        }
    }
}
