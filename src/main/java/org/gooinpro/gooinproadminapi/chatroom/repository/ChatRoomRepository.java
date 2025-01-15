package org.gooinpro.gooinproadminapi.chatroom.repository;


import org.gooinpro.gooinproadminapi.chatroom.domain.ChatRoomEntity;
import org.gooinpro.gooinproadminapi.chatroom.repository.search.ChatRoomSearch;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long>, ChatRoomSearch {

    Optional<ChatRoomEntity> findByEmployer_Eno(Long eno); // select * from chatroom join emp where eno = ?

    Optional<ChatRoomEntity> findByPartTimer_Pno(Long pno); // select * from chatroom join part where pno = ?

}

