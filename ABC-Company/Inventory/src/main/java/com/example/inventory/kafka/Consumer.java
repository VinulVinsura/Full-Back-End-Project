package com.example.inventory.kafka;




import com.example.inventory.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Consumer {

    @KafkaListener(
            topics = "order_topic",
            groupId = "inventory",
            properties = {
                    "spring.json.type.mapping=com.example.order.dto.MessageDto:com.example.inventory.dto.MessageDto"
            }
    )
    public void consumer(MessageDto messageDto){
        log.info(messageDto.toString());
    }


}
