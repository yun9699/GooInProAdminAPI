package org.gooinpro.gooinproadminapi.chatmessage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ChatMessageDTO {

    private String id;

    private String sender; // 보내는사람

    private String receiver; // 받는사람

    private String message;

    private Date timestamp;

    private String roomId;
}
