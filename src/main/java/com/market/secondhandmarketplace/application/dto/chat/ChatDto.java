package com.market.secondhandmarketplace.application.dto.chat;

import com.market.secondhandmarketplace.domain.entity.chat.Chat;
import com.market.secondhandmarketplace.domain.entity.chat.ChatRoom;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ChatDto {

    @Getter @Setter
    @Builder
    public static class ChatMessage {
        private String message;
        private Member member;
        private ChatRoom chatRoom;

        public Chat toEntity() {
            return Chat.builder()
                    .message(message)
                    .member(member)
                    .chatRoom(chatRoom)
                    .build();
        }
    }
}
