package org.gooinpro.gooinproadminapi.chatmessage.repository;


import org.gooinpro.gooinproadminapi.chatmessage.domain.ChatEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntity, Long> {

    List<ChatEntity> findByUser(String user);

}
