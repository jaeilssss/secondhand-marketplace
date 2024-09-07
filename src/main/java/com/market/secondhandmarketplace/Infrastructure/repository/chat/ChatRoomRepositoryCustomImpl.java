package com.market.secondhandmarketplace.Infrastructure.repository.chat;

import com.market.secondhandmarketplace.domain.entity.chat.ChatRoom;
import com.market.secondhandmarketplace.domain.entity.chat.QChatRoom;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ChatRoomRepositoryCustomImpl implements ChatRoomRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    QChatRoom qChatRoom = QChatRoom.chatRoom;
    QMember qMember1 = new QMember("member1");
    QMember qMember2 = new QMember("member2");
    public ChatRoomRepositoryCustomImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<ChatRoom> findChatRoomByMemberList(List<Member> memberList) {
        if (memberList.size() != 2) {
            throw new IllegalArgumentException("The member list must contain exactly 2 members");
        }

        return Optional.ofNullable(jpaQueryFactory.selectFrom(qChatRoom)
                .join(qChatRoom.member1, qMember1).fetchJoin()
                .join(qChatRoom.member2, qMember2).fetchJoin()
                .where(qChatRoom.member1.eq(memberList.get(0)))
                .where(qChatRoom.member2.eq(memberList.get(1)))
                .fetchOne());
    }

    @Override
    public Optional<ChatRoom> getChatRoomById(Long chatRoomId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(qChatRoom)
                        .join(qChatRoom.member1, qMember1)
                        .join(qChatRoom.member2, qMember2)
                        .where(qChatRoom.id.eq(chatRoomId))
                        .fetchOne()
        );
    }


}
