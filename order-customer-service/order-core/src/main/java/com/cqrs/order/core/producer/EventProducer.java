package com.cqrs.order.core.producer;

import com.cqrs.order.core.events.BaseEvent;

public interface EventProducer {

    void produce(String topic, BaseEvent event);
}
