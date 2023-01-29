package com.cqrs.order.orders.infrastructure;

import com.cqrs.order.core.events.BaseEvent;
import com.cqrs.order.core.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer implements EventProducer {

    @Autowired
    private KafkaTemplate<String, Object> template;

    @Override
    public void produce(String topic, BaseEvent event) {
        template.send(topic, event);
    }
}
