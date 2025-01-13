package org.gooinpro.gooinproadminapi.chatmessage.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.chatmessage.domain.ChatEntity;
import org.gooinpro.gooinproadminapi.chatmessage.dto.ChatMessageDTO;
import org.gooinpro.gooinproadminapi.chatmessage.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ChatController {

    private final SimpMessageSendingOperations template;

    private final ChatService chatService;

    // 채팅 리스트 반환
    @GetMapping("/chat/{user}")
    public ResponseEntity<List<ChatEntity>> getChatMessages(@PathVariable String user){
        //임시로 리스트 형식으로 구현, 실제론 DB 접근 필요

        log.info("getChatMessages called");

        List<ChatEntity> chatEntities = chatService.getMessage(user);

        return ResponseEntity.ok().body(chatEntities);

    }

    //메시지 송신 및 수신, /pub가 생략된 모습. 클라이언트 단에선 /pub/message로 요청
    @MessageMapping("/message")
    public ResponseEntity<Void> receiveMessage(@RequestBody ChatMessageDTO chat) {
        // 메시지를 해당 채팅방 구독자들에게 전송
        template.convertAndSend("/sub/chatroom/1", chat);
        log.info("Sent message: {}", chat);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send")
    public ResponseEntity<ChatEntity> sendMessage(@RequestBody ChatMessageDTO chatMessageDTO) {

        log.info("sendMessage called");
        log.info("----------------------------------------");
        // 메시지를 서비스로 전달하여 저장
        ChatEntity savedMessage = chatService.saveMessage(chatMessageDTO.getUser(), chatMessageDTO.getMessage());
        // 저장된 메시지 응답
        return ResponseEntity.ok(savedMessage);
    }
}
