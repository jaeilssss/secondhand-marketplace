package com.market.secondhandmarketplace.domain.entity.chat;

import com.market.secondhandmarketplace.domain.entity.AbstractEntity;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String message;
}
