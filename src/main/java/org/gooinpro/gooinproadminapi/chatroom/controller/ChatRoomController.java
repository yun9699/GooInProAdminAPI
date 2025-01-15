package org.gooinpro.gooinproadminapi.chatroom.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.chatroom.dto.ChatRoomAddDTO;
import org.gooinpro.gooinproadminapi.chatroom.dto.ChatRoomGetDTO;
import org.gooinpro.gooinproadminapi.chatroom.service.ChatRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/chatroom")
@Log4j2
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/get/{eno}")
    public ResponseEntity<ChatRoomGetDTO> getChatRoom(@PathVariable Long eno) {
        log.info("Getting chat room with eno");

        return ResponseEntity.ok(chatRoomService.findChatRoom(eno));
    }


    @PutMapping("/add")
    public ResponseEntity<String> addChatRoom(@RequestBody ChatRoomAddDTO chatRoomDTO) {
        log.info("chat room add");

        return ResponseEntity.ok().body(chatRoomService.addChatRoom(chatRoomDTO));
    }
}
