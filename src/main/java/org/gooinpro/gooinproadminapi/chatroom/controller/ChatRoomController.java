package org.gooinpro.gooinproadminapi.chatroom.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.chatroom.dto.ChatRoomAddDTO;
import org.gooinpro.gooinproadminapi.chatroom.dto.ChatRoomAddPartDTO;
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

    @GetMapping("/get/emp/{eno}") // 고용인 채팅 하기
    public ResponseEntity<ChatRoomGetDTO> getChatRoom(@PathVariable Long eno) {
        log.info("Getting chat room with eno");

        return ResponseEntity.ok(chatRoomService.findChatRoom(eno));
    }


    @PutMapping("/add/emp") // 고용인 채팅방 만들기 <채팅방 없을때 채팅하기 하면 자동으로 생성됨>
    public ResponseEntity<String> addChatRoom(@RequestBody ChatRoomAddDTO chatRoomDTO) {
        log.info("chat room add");

        return ResponseEntity.ok().body(chatRoomService.addChatRoom(chatRoomDTO));
    }

    @GetMapping("/get/part/{pno}")  // 파트타이머 채팅 하기
    public ResponseEntity<ChatRoomGetDTO> getChatPartRoom(@PathVariable Long pno) {
        log.info("Getting chat part room with no pno");

        return ResponseEntity.ok(chatRoomService.findChatPartRoom(pno));
    }

    @PutMapping("/add/part") // 파트타이머 채팅방 만들기 <채팅방 없을때 채팅하기 하면 자동으로 생성됨>
    public ResponseEntity<String> addChatPartRoom(@RequestBody ChatRoomAddPartDTO chatRoomAddPartDTO) {
        log.info("chat Part room add");

        return ResponseEntity.ok(chatRoomService.addChatPartRoom(chatRoomAddPartDTO));

    }

    @DeleteMapping("/delete/{eno}") // 고용인 채팅방 삭제
    public ResponseEntity<String> deleteChatRoom(@PathVariable Long eno) {
        log.info("Deleting chat room with eno");

        return ResponseEntity.ok().body(chatRoomService.deleteChatRoom(eno));
    }

    @DeleteMapping("/delete/part/{pno}") // 파트타이머 채팅방 삭제
    public ResponseEntity<String> deleteChatPartRoom(@PathVariable Long pno) {
        log.info("Deleting chat room with pno");

        return ResponseEntity.ok().body(chatRoomService.deleteChatPartRoom(pno));
    }
}
