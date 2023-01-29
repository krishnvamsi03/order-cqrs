package com.cqrs.order.orders.api.handler;

import com.cqrs.order.orders.api.commands.*;

public interface CommandHandler {

    void handle(OrderCreateCommand command);
    void handle(OrderAcceptCommand command);
    void handle(OrderRejectCommand command);
    void handle(OrderCancelCommand command);
    void handle(OrderDeliverCommand command);
}
