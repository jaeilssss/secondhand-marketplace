package com.market.secondhandmarketplace.Infrastructure.repository.chat;

import com.market.secondhandmarketplace.domain.entity.chat.ChatRoom;
import com.market.secondhandmarketplace.domain.entity.member.Member;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepositoryCustom {
    public Optional<ChatRoom> findChatRoomByMemberList(List<Member> memberList);
    public Optional<ChatRoom> getChatRoomById(Long chatRoomId);
}