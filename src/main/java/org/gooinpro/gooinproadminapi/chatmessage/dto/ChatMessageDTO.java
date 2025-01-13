package org.gooinpro.gooinproadminapi.chatmessage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ChatMessageDTO {

    private String id;

    private String user;

    private String message;

    private Date timestamp;
}
