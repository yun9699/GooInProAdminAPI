package org.gooinpro.gooinproadminapi.chatmessage.service;


import org.gooinpro.gooinproadminapi.chatmessage.domain.ChatEntity;
import org.gooinpro.gooinproadminapi.chatmessage.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public ChatEntity saveMessage(String user, String message) {

        Date timestamp = new Date();

        ChatEntity chatEntity = ChatEntity.builder()
                .user(user)
                .message(message)
                .timestamp(timestamp)
                .build();

        return chatRepository.save(chatEntity);
    }

    public List<ChatEntity> getMessage(String user) {

        return chatRepository.findByUser(user);
    }
}
