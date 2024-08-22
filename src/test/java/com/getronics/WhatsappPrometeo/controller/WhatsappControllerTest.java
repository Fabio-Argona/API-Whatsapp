package com.getronics.WhatsappPrometeo.controller;

import com.getronics.WhatsappPrometeo.Repository.ReceivedMessageRepository;

import com.getronics.WhatsappPrometeo.dto.MessageBodyDTO;

import com.getronics.WhatsappPrometeo.model.ResponseWhatsappContact;

import com.getronics.WhatsappPrometeo.service.WhatsappService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;


import static org.mockito.Mockito.*;

class WhatsappControllerTest {

    @Mock
    private WhatsappService whatsappService;

    @Mock
    private ReceivedMessageRepository receivedMessageRepository;

    @InjectMocks
    private WhatsappController whatsappController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSend() {
        // Crie uma instância de MessageBodyDTO com os parâmetros necessários
        MessageBodyDTO messageBodyDTO = new MessageBodyDTO("1234567890", "Hello, World!");

        // Crie listas vazias ou inicialize-as conforme necessário
        List<ResponseWhatsappContact> contacts = List.of(); // Lista vazia
        String actualResponse = String.valueOf(whatsappController.send(messageBodyDTO));

        verify(whatsappService, times(1)).sendMessage(messageBodyDTO);
    }

    private void assertEquals(String s, String s1) {
    }





}
