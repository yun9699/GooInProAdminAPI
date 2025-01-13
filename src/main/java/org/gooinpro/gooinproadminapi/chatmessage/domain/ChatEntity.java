package org.gooinpro.gooinproadminapi.chatmessage.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "messages")
public class ChatEntity {

    @Id
    private String id;

    private String user;

    private String message;

    private Date timestamp;

    @Override
    public String toString() { // test확인용
        return "ChatEntity{id='" + id + "', user='" + user + "', message='" + message + "', timestamp=" + timestamp + "}";
    }

}
