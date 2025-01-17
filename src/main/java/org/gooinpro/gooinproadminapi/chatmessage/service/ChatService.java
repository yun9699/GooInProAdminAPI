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

    public ChatEntity saveMessage(ChatMessageDTO chatMessageDTO) {
        ChatEntity chatEntity = ChatEntity.builder()
                .id(UUID.randomUUID().toString())
                .sender(chatMessageDTO.getSender())
                .receiver(chatMessageDTO.getReceiver())
                .message(chatMessageDTO.getMessage())
                .roomId(chatMessageDTO.getRoomId())
                .timestamp(new Date())
                .build();

        ChatEntity savedMessage = chatRepository.save(chatEntity);
        log.info("----------------------save");
        log.info("Saved message: {}", savedMessage);
        return savedMessage;
    }

    public List<ChatEntity> getMessage(String roomId) {

        return chatRepository.findByRoomId(roomId);
    }

    public void deleteMessage(String roomId) {
        log.info("Deleting message: {}", roomId);
        chatRepository.deleteByRoomId(roomId);
    }


}
