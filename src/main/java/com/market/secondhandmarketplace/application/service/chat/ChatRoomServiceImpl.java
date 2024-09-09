package com.market.secondhandmarketplace.application.service.chat;

import com.market.secondhandmarketplace.domain.repository.chat.ChatRoomRepository;
import com.market.secondhandmarketplace.application.dto.chat.ChatRoomDto;
import com.market.secondhandmarketplace.domain.entity.chat.ChatRoom;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.globals.error.ChatRoomErrorCode;
import com.market.secondhandmarketplace.globals.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoomDto.ChatRoomResponse findChatRoom(List<Member> memberList) {
        return chatRoomRepository.findChatRoomByMemberList(memberList)
                .orElse(ChatRoom.builder().id(-1L).build()).toResponse();
    }

    @Override
    public ChatRoom getChatRoom(Long chatRoomId) {
        return chatRoomRepository.getChatRoomById(chatRoomId)
                .orElseThrow(() -> new BaseException(
                        ChatRoomErrorCode.INVALID_CHAT_ROOM_ID.getMessage(),
                        HttpStatus.BAD_REQUEST
                ));
    }

    @Override
    @Transactional
    public ChatRoomDto.ChatRoomResponse createChatRoom(
            ChatRoomDto.CreateChatRoom createChatRoom) {
        return chatRoomRepository.save(createChatRoom.toEntity()).toResponse();
    }
}
