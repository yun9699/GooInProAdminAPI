package org.gooinpro.gooinproadminapi.chatmessage.repository;


import org.gooinpro.gooinproadminapi.chatmessage.domain.ChatEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntity, String> {

    @Query("{ 'roomId' : ?0 }")
    List<ChatEntity> findByRoomId(String roomId);

    void deleteByRoomId(String roomId);

}
