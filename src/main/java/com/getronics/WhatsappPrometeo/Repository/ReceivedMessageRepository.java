package com.getronics.WhatsappPrometeo.Repository;

import com.getronics.WhatsappPrometeo.model.ReceivedMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivedMessageRepository extends MongoRepository<ReceivedMessage, String> {
}
