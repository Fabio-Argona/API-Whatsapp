package com.getronics.WhatsappPrometeo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getronics.WhatsappPrometeo.Repository.ReceivedMessageRepository;
import com.getronics.WhatsappPrometeo.dto.IncomingMessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WhatsappServiceTest {

    @Mock
    private RestClient restClient;

    @Mock
    private ReceivedMessageRepository receivedMessageRepository;

    @InjectMocks
    private WhatsappService whatsappService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

//    @Test
//    void testSendMessage() throws Exception {
//        MessageBodyDTO messageBodyDTO = new MessageBodyDTO("1234567890", "Hello, World!");
//        String jsonResponse = "{\"messaging_product\":\"Message Sent\"}";
//
//        // Simula a chamada do RestClient
//        RestClient.RequestBodyUriSpec requestBodyUriSpec = createRequestBodyUriSpec(jsonResponse);
//        when(restClient.post()).thenReturn(requestBodyUriSpec);
//
//        // Chama o método a ser testado
//        ResponseWhatsapp response = whatsappService.sendMessage(messageBodyDTO);
//
//        // Verifica a resposta
//        assertEquals("Message Sent", response.messaging_product());
//        verify(receivedMessageRepository, never()).save(any(ReceivedMessage.class));
//    }
//
//    private RestClient.RequestBodyUriSpec createRequestBodyUriSpec(String jsonResponse) {
//        RestClient.RequestBodyUriSpec requestBodyUriSpec = mock(RestClient.RequestBodyUriSpec.class);
//        RestClient.ResponseSpec responseSpec = mock(RestClient.ResponseSpec.class);
//
//        when(restClient.post()).thenReturn(requestBodyUriSpec);
//        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodyUriSpec);
//        when(requestBodyUriSpec.contentType(MediaType.APPLICATION_JSON)).thenReturn(requestBodyUriSpec);
//        when(requestBodyUriSpec.body(any())).thenReturn(requestBodyUriSpec);
//        when(requestBodyUriSpec.retrieve()).thenReturn(responseSpec);
//        when(responseSpec.body(String.class)).thenReturn(jsonResponse);
//
//        return requestBodyUriSpec;
//    }

//    @Test
//    void testSendMessageRestClientException() {
//        MessageBodyDTO messageBodyDTO = new MessageBodyDTO("1234567890", "Hello, World!");
//
//        // Mockando o RestClient para lançar uma exceção
//        when(restClient.post()).thenThrow(new RestClientException("Error to send message"));
//
//        // Verifica se a exceção é lançada
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            whatsappService.sendMessage(messageBodyDTO);
//        });
//        assertEquals("Error to send message", exception.getMessage());
//    }

    @Test
    void testProcessIncomingMessage() {
        IncomingMessageDTO incomingMessageDTO = new IncomingMessageDTO("1234567890", "Hello!");

        // Chama o método para processar a mensagem recebida
        whatsappService.processIncomingMessage(incomingMessageDTO);

        // Verifica se a mensagem foi salva no repositório
        verify(receivedMessageRepository, times(1)).save(any(ReceivedMessage.class));
    }
}
