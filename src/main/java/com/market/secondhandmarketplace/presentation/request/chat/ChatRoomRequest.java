package com.market.secondhandmarketplace.presentation.request.chat;

import lombok.*;

import java.util.List;

public class ChatRoomRequest {

    @Getter @Setter
    @NoArgsConstructor  // 매개변수 없는 생성자 제공
    @AllArgsConstructor // 모든 필드를 받는 생성자 제공
    @Builder
    public static class CreateChatRoom {
        private List<Long> memberIdList;
    }
}
