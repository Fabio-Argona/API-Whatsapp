package com.getronics.WhatsappPrometeo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class IncomingMessageDTO {
    private String messageId;
    private String senderId;
    private String content;
    private LocalDateTime timestamp; // Usando LocalDateTime para timestamp
}

