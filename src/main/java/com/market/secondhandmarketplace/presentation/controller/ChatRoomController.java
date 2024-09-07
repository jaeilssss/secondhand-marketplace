package com.market.secondhandmarketplace.presentation.controller;

import com.market.secondhandmarketplace.application.dto.chat.ChatRoomDto;
import com.market.secondhandmarketplace.application.service.chat.ChatRoomService;
import com.market.secondhandmarketplace.application.service.member.MemberService;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.presentation.Response;
import com.market.secondhandmarketplace.presentation.enums.APIResponseCode;
import com.market.secondhandmarketplace.presentation.request.chat.ChatRoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final MemberService memberService;

    @PostMapping
    public Response findChatRoom(@RequestBody ChatRoomRequest.CreateChatRoom createChatRoom) {
        List<Member> memberList = memberService.getMemberList(createChatRoom.getMemberIdList());
        ChatRoomDto.ChatRoomResponse chatRoomResponse = chatRoomService.findChatRoom(memberList);

        if (chatRoomResponse.getId() == -1L) {
            chatRoomResponse = chatRoomService.createChatRoom(
                        ChatRoomDto.CreateChatRoom.builder()
                            .memberList(memberList)
                            .build());
        }

        return Response.success(
                chatRoomResponse,
                APIResponseCode.OK.getMessage()
        );
    }

}
