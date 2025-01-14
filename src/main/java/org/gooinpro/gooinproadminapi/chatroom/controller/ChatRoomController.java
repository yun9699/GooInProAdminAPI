package org.gooinpro.gooinproadminapi.chatroom.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.chatroom.dto.AddChatRoomDTO;
import org.gooinpro.gooinproadminapi.chatroom.service.ChatRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/chatroom")
@Log4j2
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PutMapping("/add")
    public ResponseEntity<String> addChatRoom(@RequestBody AddChatRoomDTO chatRoomDTO) {
        log.info("chat room add");

        return ResponseEntity.ok().body(chatRoomService.addChatRoom(chatRoomDTO));
    }
}
