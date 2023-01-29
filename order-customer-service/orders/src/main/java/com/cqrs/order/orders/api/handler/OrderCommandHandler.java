package com.cqrs.order.orders.api.handler;

import com.cqrs.order.core.domain.AggregatorRoot;
import com.cqrs.order.core.handler.EventSourceHandler;
import com.cqrs.order.orders.api.commands.*;
import com.cqrs.order.orders.domain.OrderAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCommandHandler implements CommandHandler {

    @Autowired
    private EventSourceHandler<OrderAggregate> eventSourceHandler;

    @Override
    public void handle(OrderCreateCommand command) {
        OrderAggregate root = new OrderAggregate(command);
        eventSourceHandler.save(root);
    }

    @Override
    public void handle(OrderAcceptCommand command) {
        OrderAggregate root = eventSourceHandler.getById(command.getId());
        root.acceptOrder(command);
        eventSourceHandler.save(root);

    }

    @Override
    public void handle(OrderRejectCommand command) {
        OrderAggregate root = eventSourceHandler.getById(command.getId());
        root.rejectOrder(command);
        eventSourceHandler.save(root);
    }

    @Override
    public void handle(OrderCancelCommand command) {
        OrderAggregate root = eventSourceHandler.getById(command.getId());
        root.cancelOrder(command);
        eventSourceHandler.save(root);
    }

    @Override
    public void handle(OrderDeliverCommand command) {
        OrderAggregate root = eventSourceHandler.getById(command.getId());
        root.deliveredOrder(command);
        eventSourceHandler.save(root);
    }
}
