package com.market.secondhandmarketplace.domain.entity.chat;

import com.market.secondhandmarketplace.application.dto.chat.ChatRoomDto;
import com.market.secondhandmarketplace.domain.entity.AbstractEntity;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member1_id")
    private Member member1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member2_id")
    private Member member2;

    public ChatRoomDto.ChatRoomResponse toResponse() {
        return ChatRoomDto.ChatRoomResponse.builder()
                .id(id)
                .build();
    }


}
