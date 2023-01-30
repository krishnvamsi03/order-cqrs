package com.cqrs.order.customers.infrastructure.consumer;

import com.cqrs.order.common.events.*;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {

    void consumer(@Payload OrderAcceptedEvent event, Acknowledgment ack);
    void consumer(@Payload OrderCancelledEvent event, Acknowledgment ack);
    void consumer(@Payload OrderCreatedEvent event, Acknowledgment ack);
    void consumer(@Payload OrderRejectedEvent event, Acknowledgment ack);
    void consumer(@Payload OrderDeliveredEvent event, Acknowledgment ack);
}
