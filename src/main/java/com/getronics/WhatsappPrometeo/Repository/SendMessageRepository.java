package com.getronics.WhatsappPrometeo.Repository;

import com.getronics.WhatsappPrometeo.model.SendMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendMessageRepository extends MongoRepository<SendMessage, String> {
}
