package hr.ivlahek.showcase.event;

import hr.ivlahek.showcase.Constants;
import hr.ivlahek.showcase.UatAbstractTest;
import hr.ivlahek.showcase.event.dto.Event3;
import hr.ivlahek.showcase.event.dto.Event4;
import org.apache.avro.generic.GenericData;
import org.apache.avro.specific.SpecificData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class MessageReceiverTest extends UatAbstractTest {

    @Autowired
    private MessageReceiver messageReceiver;

    @Test
    public void should_receive_event_3() {
        event3Producer.send(new ProducerRecord<>(Constants.EVENT_3_TOPIC, null, Event3.newBuilder()
                .setTimestamp(System.currentTimeMillis())
                .setId(1)
                .build()));

        Iterable<ConsumerRecord<String, GenericData>> records = KafkaTestUtils.getRecords(event3Consumer).records(Constants.EVENT_3_TOPIC);
        List<Event3> events =
                Lists.newArrayList(records).stream().collect(Collectors.mapping(consumerRecord -> map(consumerRecord), Collectors.toList()));

        assertThat(events.get(0).getId()).isEqualTo(1);
    }

    private Event3 map(ConsumerRecord record) {
        return (Event3) SpecificData.get().deepCopy(Event3.SCHEMA$, record.value());
    }

    @Test
    public void should_receive_event_4() {
        event4Producer.send(new ProducerRecord<>(Constants.EVENT_4_TOPIC, null, Event4.newBuilder()
                .setTimestamp(System.currentTimeMillis())
                .setId("id")
                .build()));

        ConsumerRecord<String, Event4> consumerRecord = KafkaTestUtils.getSingleRecord(event4Consumer, Constants.EVENT_4_TOPIC);
        assertThat(consumerRecord).isNotNull();
    }

}