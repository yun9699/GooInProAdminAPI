package org.gooinpro.gooinproadminapi.chatmessage.service;


import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.chatmessage.domain.ChatEntity;
import org.gooinpro.gooinproadminapi.chatmessage.dto.ChatMessageDTO;
import org.gooinpro.gooinproadminapi.chatmessage.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public ChatEntity saveMessage(String user, String message, String roomId) {
        log.info("Saving message: user={}, message={}, roomId={}", user, message, roomId);

        // id를 UUID로 생성하여 고유하게 설정
        String newId = UUID.randomUUID().toString();

        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setId(newId);  // UUID로 새로운 ID 설정
        chatEntity.setUser(user);
        chatEntity.setMessage(message);
        chatEntity.setRoomId(roomId);
        chatEntity.setTimestamp(new Date());

        // DB에 메시지 저장
        ChatEntity savedMessage = chatRepository.save(chatEntity);
        log.info("Saved message: {}", savedMessage);
        return savedMessage;
    }

    public List<ChatEntity> getMessage(String user) {

        return chatRepository.findByUser(user);
    }
}
