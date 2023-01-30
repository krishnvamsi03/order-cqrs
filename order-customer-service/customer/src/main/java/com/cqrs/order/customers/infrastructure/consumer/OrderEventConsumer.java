package com.cqrs.order.customers.infrastructure.consumer;

import com.cqrs.order.common.events.*;
import com.cqrs.order.customers.infrastructure.handler.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer implements EventConsumer{

    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "OrderAcceptedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(OrderAcceptedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "OrderCancelledEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(OrderCancelledEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "OrderCreatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(OrderCreatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "OrderRejectedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(OrderRejectedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "OrderDeliveredEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(OrderDeliveredEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}
