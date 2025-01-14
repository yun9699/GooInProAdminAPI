package org.gooinpro.gooinproadminapi.chatroom.repository.search;

import org.gooinpro.gooinproadminapi.chatroom.domain.ChatRoomEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ChatRoomSearchImpl extends QuerydslRepositorySupport implements ChatRoomSearch {

    public ChatRoomSearchImpl() {
        super(ChatRoomEntity.class);
    }


}
