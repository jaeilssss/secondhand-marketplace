package com.market.secondhandmarketplace.globals.error;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatRoomErrorCode {
    INVALID_CHAT_ROOM_ID("유효하지 않은 채팅방 ID 입니다.");

    private final String message;

}
