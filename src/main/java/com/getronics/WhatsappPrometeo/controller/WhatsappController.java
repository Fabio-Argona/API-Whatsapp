package com.getronics.WhatsappPrometeo.controller;

import com.getronics.WhatsappPrometeo.Repository.ReceivedMessageRepository;
import com.getronics.WhatsappPrometeo.dto.IncomingMessageDTO;
import com.getronics.WhatsappPrometeo.dto.MessageBodyDTO;
import com.getronics.WhatsappPrometeo.model.ReceivedMessage;
import com.getronics.WhatsappPrometeo.service.WhatsappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsappController {

    private final WhatsappService whatsappService;
    private final ReceivedMessageRepository receivedMessageRepository;

    @Autowired
    public WhatsappController(WhatsappService whatsappService, ReceivedMessageRepository receivedMessageRepository) {
        this.whatsappService = whatsappService;
        this.receivedMessageRepository = receivedMessageRepository;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody MessageBodyDTO payload) {
        String response = whatsappService.sendMessage(payload);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/receive")
    public ResponseEntity<String> receive(@RequestBody IncomingMessageDTO incomingMessage) {
        try {
            whatsappService.processIncomingMessage(incomingMessage);
            return new ResponseEntity<>("Message processed successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to process message", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<ReceivedMessage>> getAllMessages() {
        List<ReceivedMessage> messages = receivedMessageRepository.findAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
