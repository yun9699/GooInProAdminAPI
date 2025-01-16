package org.gooinpro.gooinproadminapi.chatroom.repository.search;

import org.gooinpro.gooinproadminapi.chatroom.dto.ChatRoomGetDTO;
import org.gooinpro.gooinproadminapi.chatroom.dto.ChatRoomAddPartDTO;

public interface ChatRoomSearch {

    ChatRoomGetDTO GetRoomNumber(Long eno);

    ChatRoomGetDTO GetRoomPartNumber(Long pno);
}
