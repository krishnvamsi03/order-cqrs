package com.cqrs.order.customers.infrastructure.handler;

import com.cqrs.order.common.events.*;
import com.cqrs.order.customers.domain.OrderEntity;
import com.cqrs.order.customers.domain.OrderRepository;
import com.cqrs.order.customers.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderEventHandler implements EventHandler {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void on(OrderAcceptedEvent event) {
        Optional<OrderEntity> order = orderRepository.findById(event.getId());
        if (order.isEmpty()) {
            return;
        }
        order.get().setStatus(Status.ACCEPTED);
        orderRepository.save(order.get());
    }

    @Override
    public void on(OrderCancelledEvent event) {
        Optional<OrderEntity> order = orderRepository.findById(event.getId());
        if (order.isEmpty()) {
            return;
        }
        order.get().setStatus(Status.CANCELLED);
        orderRepository.save(order.get());
    }

    @Override
    public void on(OrderCreatedEvent event) {
        orderRepository.save(OrderEntity.builder()
                .id(event.getId())
                .billAmount(event.getBillAmount())
                .creationDate(new Date())
                .customerId(event.getCustomerId())
                .status(Status.CREATED)
                .build());
    }

    @Override
    public void on(OrderDeliveredEvent event) {
        Optional<OrderEntity> order = orderRepository.findById(event.getId());
        if (order.isEmpty()) {
            return;
        }
        order.get().setStatus(Status.DELIVERED);
        orderRepository.save(order.get());
    }

    @Override
    public void on(OrderRejectedEvent event) {
        Optional<OrderEntity> order = orderRepository.findById(event.getId());
        if (order.isEmpty()) {
            return;
        }
        order.get().setStatus(Status.REJECTED);
        order.get().setNotes(event.getReason());
        orderRepository.save(order.get());
    }
}
