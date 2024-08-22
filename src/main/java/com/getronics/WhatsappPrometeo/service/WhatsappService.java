package com.getronics.WhatsappPrometeo.service;

import com.getronics.WhatsappPrometeo.Repository.ReceivedMessageRepository;
import com.getronics.WhatsappPrometeo.Repository.SendMessageRepository;
import com.getronics.WhatsappPrometeo.dto.IncomingMessageDTO;
import com.getronics.WhatsappPrometeo.dto.MessageBodyDTO;
import com.getronics.WhatsappPrometeo.model.MessageRequest;
import com.getronics.WhatsappPrometeo.model.ReceivedMessage;
import com.getronics.WhatsappPrometeo.model.SendMessage;
import com.getronics.WhatsappPrometeo.model.TextContent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class WhatsappService {

    private static final Logger logger = LoggerFactory.getLogger(WhatsappService.class);

    @Value("${whatsapp.api.url}")
    private String apiUrl;

    @Value("${whatsapp.api.token}")
    private String tokenWhatsapp;

    private final RestTemplate restTemplate;
    private final ReceivedMessageRepository receivedMessageRepository;
    private final SendMessageRepository sendMessageRepository;

    public String sendMessage(MessageBodyDTO messageBodyDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(tokenWhatsapp);

        MessageRequest requestBody = new MessageRequest(
                "whatsapp",
                messageBodyDTO.number(),
                "text",
                new TextContent(messageBodyDTO.message())
        );

        HttpEntity<MessageRequest> entity = new HttpEntity<>(requestBody, headers);

        try {
            String response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class).getBody();

            // Gere um ID único para a mensagem
            String uniqueId = generateUniqueId();

            // Salvar mensagem enviada
            SendMessage sentMessage = SendMessage.builder()
                    .messageId(uniqueId)
                    .senderId(messageBodyDTO.number())
                    .content(messageBodyDTO.message())
                    .timestamp(LocalDateTime.now())
                    .build();

            sendMessageRepository.save(sentMessage);
            logger.info("Mensagem enviada salva com sucesso: {}", sentMessage);

            return response;
        } catch (Exception e) {
            logger.error("Erro ao enviar mensagem: {}", e.getMessage());
            return "Erro ao enviar mensagem: " + e.getMessage();
        }
    }

    private String generateUniqueId() {
        // Implemente a lógica para gerar um ID único, por exemplo, usando UUID
        return UUID.randomUUID().toString();
    }

    public void processIncomingMessage(IncomingMessageDTO incomingMessage) {
        Long timestamp = convertLocalDateTimeToLong(incomingMessage.getTimestamp());

        ReceivedMessage message = ReceivedMessage.builder()
                .messageId(incomingMessage.getMessageId())
                .senderId(incomingMessage.getSenderId())
                .content(incomingMessage.getContent())
                .timestamp(timestamp)
                .build();

        receivedMessageRepository.save(message);
        System.out.println("Mensagem recebida de "
                + incomingMessage.getSenderId() + ": "
                + incomingMessage.getContent());
    }

    private Long convertLocalDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
