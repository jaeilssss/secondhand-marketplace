package com.market.secondhandmarketplace.application.service.chat;

import com.market.secondhandmarketplace.application.dto.chat.ChatRoomDto;
import com.market.secondhandmarketplace.domain.entity.chat.ChatRoom;
import com.market.secondhandmarketplace.domain.entity.member.Member;

import java.util.List;

public interface ChatRoomService {
    public ChatRoomDto.ChatRoomResponse createChatRoom(
            ChatRoomDto.CreateChatRoom createChatRoom);

    public ChatRoomDto.ChatRoomResponse findChatRoom(List<Member> memberList);
    public ChatRoom getChatRoom(Long chatRoomId);
}
