package hr.ivlahek.showcase.event;

import hr.ivlahek.showcase.Constants;
import hr.ivlahek.showcase.event.dto.Event3;
import hr.ivlahek.showcase.event.dto.Event4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @KafkaListener(topics = Constants.EVENT_4_TOPIC, groupId = "showcase-consumer")
    public void receiveCreateMobileApplicationCommand(Event4 event) {
        logger.info("Event4 received {}", event);
    }

    @KafkaListener(topics = Constants.EVENT_3_TOPIC, groupId = "showcase-consumer")
    public void receiveCreateUserCommand(Event3 event) {
        logger.info("Event3 received {}", event);
    }

}
