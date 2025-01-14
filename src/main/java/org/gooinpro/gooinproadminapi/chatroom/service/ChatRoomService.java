package org.gooinpro.gooinproadminapi.chatroom.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.chatroom.domain.ChatRoomEntity;
import org.gooinpro.gooinproadminapi.chatroom.dto.AddChatRoomDTO;
import org.gooinpro.gooinproadminapi.chatroom.repository.ChatRoomRepository;
import org.gooinpro.gooinproadminapi.employer.domain.EmployerEntity;
import org.gooinpro.gooinproadminapi.employer.repository.EmployerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ChatRoomService {


    private final ChatRoomRepository chatRoomRepository;
    private final EmployerRepository employerRepository;

    public String addChatRoom(AddChatRoomDTO addChatRoomDTO) {

        Optional<ChatRoomEntity> existingChatRoom = chatRoomRepository.findByEmployer_Eno(addChatRoomDTO.getEno());

        if (existingChatRoom.isPresent()) {
            return "Chat Room Already Exists";
        }

        Optional<EmployerEntity> eno = employerRepository.findByEno(addChatRoomDTO.getEno());

        ChatRoomEntity chatRoomEntity = ChatRoomEntity.builder()
                .employer(eno.get())
                .build();

        chatRoomRepository.save(chatRoomEntity);

        return "success add chat room";

    }


}
