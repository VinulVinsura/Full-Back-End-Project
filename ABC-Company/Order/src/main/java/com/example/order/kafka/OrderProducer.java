package com.example.order.kafka;




import com.example.order.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final NewTopic orderTopic;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC="order_topic";

    public void sendMessage(MessageDto messageDto){
        log.info(messageDto.toString());

//        Message<MessageDto> message = MessageBuilder
//                .withPayload(messageDto)
//                .setHeader(KafkaHeaders.TOPIC, orderTopic.name())
//                .build();

//        log.info(message.toString());

        kafkaTemplate.send(TOPIC,messageDto);
    }




}
