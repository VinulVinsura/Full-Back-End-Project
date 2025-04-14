package com.example.order.kafka;




import com.example.order.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Producer {

    private final NewTopic orderTopic;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC="order_topic";

    public void sendMessage(MessageDto messageDto){
        log.info(messageDto.toString());

        kafkaTemplate.send(TOPIC,messageDto);
    }




}
