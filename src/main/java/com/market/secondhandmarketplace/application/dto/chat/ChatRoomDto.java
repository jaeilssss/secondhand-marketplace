package com.market.secondhandmarketplace.application.dto.chat;

import com.market.secondhandmarketplace.domain.entity.chat.ChatRoom;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ChatRoomDto {

    @Getter @Setter
    @Builder
    public static class CreateChatRoom {
        private List<Member> memberList;

        public ChatRoom toEntity() {
            return ChatRoom.builder()
                    .member1(memberList.get(0))
                    .member2(memberList.get(1))
                    .build();
        }
    }

    @Getter @Setter
    @Builder
    public static class ChatRoomResponse {
        private Long id;

    }
}
