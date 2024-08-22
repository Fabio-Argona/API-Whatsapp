package com.getronics.WhatsappPrometeo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private String messaging_product;
    private String to;
    private String type;
    private TextContent text;
}


