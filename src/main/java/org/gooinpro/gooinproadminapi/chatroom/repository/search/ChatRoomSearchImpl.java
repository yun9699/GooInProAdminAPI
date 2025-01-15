package org.gooinpro.gooinproadminapi.chatroom.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.gooinpro.gooinproadminapi.chatroom.domain.ChatRoomEntity;
import org.gooinpro.gooinproadminapi.chatroom.domain.QChatRoomEntity;
import org.gooinpro.gooinproadminapi.chatroom.dto.ChatRoomGetDTO;
import org.gooinpro.gooinproadminapi.chatroom.dto.ChatRoomAddPartDTO;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ChatRoomSearchImpl extends QuerydslRepositorySupport implements ChatRoomSearch {

    public ChatRoomSearchImpl() {
        super(ChatRoomEntity.class);
    }


    @Override
    public ChatRoomGetDTO GetRoomNumber(Long eno) {

        QChatRoomEntity chatroom = QChatRoomEntity.chatRoomEntity;
        JPQLQuery<ChatRoomEntity> query = from(chatroom);

        query.where(chatroom.employer.eno.eq(eno));

        JPQLQuery<ChatRoomGetDTO> dtojpqlQuery = query.select(
                Projections.bean(ChatRoomGetDTO.class,
                        chatroom.rno)
        );

        ChatRoomGetDTO chatRoomGet = dtojpqlQuery.fetchOne();


        return chatRoomGet;
    }

    @Override
    public ChatRoomGetDTO GetRoomPartNumber(Long pno) {

        QChatRoomEntity chatroom = QChatRoomEntity.chatRoomEntity;
        JPQLQuery<ChatRoomEntity> query = from(chatroom);

        query.where(chatroom.partTimer.pno.eq(pno));

        JPQLQuery<ChatRoomGetDTO> dtojpqlQuery = query.select(
                Projections.bean(ChatRoomGetDTO.class,
                        chatroom.rno)
        );

        ChatRoomGetDTO chatRoomGet = dtojpqlQuery.fetchOne();

        return chatRoomGet;
    }
}
