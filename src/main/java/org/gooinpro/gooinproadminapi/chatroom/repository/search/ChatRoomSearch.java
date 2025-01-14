package org.gooinpro.gooinproadminapi.chatroom.repository.search;

import org.gooinpro.gooinproadminapi.chatroom.dto.ChatRoomGetDTO;
import org.springframework.data.jpa.repository.Query;

public interface ChatRoomSearch {

    ChatRoomGetDTO GetRoomNumber(Long eno);
}
