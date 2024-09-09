package com.market.secondhandmarketplace.presentation.controller;

import com.market.secondhandmarketplace.application.service.chat.ChatRoomService;
import com.market.secondhandmarketplace.application.service.chat.ChatService;
import com.market.secondhandmarketplace.application.service.member.MemberService;
import com.market.secondhandmarketplace.presentation.request.chat.ChatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatController {
    private final SimpMessageSendingOperations messageSendingOperations;
    private final ChatService chatService;
    private final MemberService memberService;
    private final ChatRoomService chatRoomService;
    @MessageMapping("/pub")
    public void message(ChatRequest.ChatMessage message) {

        chatService.saveChatMessage(message.toDto(
                memberService.getMember(message.getSenderId()),
                chatRoomService.getChatRoom(message.getChatRoomId())
        ));

        messageSendingOperations.convertAndSend("/sub/"+message.getChatRoomId(), message);
    }
}