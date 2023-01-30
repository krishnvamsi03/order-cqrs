package com.cqrs.order.customers.infrastructure.handler;

import com.cqrs.order.common.events.*;

public interface EventHandler {

    void on(OrderAcceptedEvent event);
    void on(OrderCancelledEvent event);
    void on(OrderCreatedEvent event);
    void on(OrderDeliveredEvent event);
    void on(OrderRejectedEvent event);
}
