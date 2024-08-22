package com.getronics.WhatsappPrometeo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Document(collection = "received_messages")
public class SendMessage {

    @Id
    private String id;
    private String messageId;
    private String senderId;
    private String content;
    private LocalDateTime timestamp;

//    private boolean isSent;

}

