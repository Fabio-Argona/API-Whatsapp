package com.getronics.WhatsappPrometeo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "received_messages")
public class ReceivedMessage {
    @Id
    private String id;
    private String messageId;
    private String senderId;
    private String content;
    private Long timestamp;
}

