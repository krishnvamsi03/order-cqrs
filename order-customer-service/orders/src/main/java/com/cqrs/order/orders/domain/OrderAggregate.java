package com.cqrs.order.orders.domain;

import com.cqrs.order.common.events.*;
import com.cqrs.order.core.commands.BaseCommand;
import com.cqrs.order.core.domain.AggregatorRoot;
import com.cqrs.order.orders.api.commands.*;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class OrderAggregate extends AggregatorRoot {

    private boolean isActive;
    private String rejectedReason;

    public OrderAggregate(OrderCreateCommand baseCommand) {
        raiseEvent(OrderCreatedEvent.builder()
                .id(baseCommand.getId())
                .createdDate(new Date())
                .customerId(baseCommand.getCustomerId())
                .billAmount(baseCommand.getBillAmount())
                .paymentMode(baseCommand.getPaymentMode())
                .build()
        );
    }

    public void apply(OrderCreatedEvent event) {
        this.id = event.getId();
        this.isActive = true;
    }

    public void acceptOrder(OrderAcceptCommand command) {
        if (!isActive) {
            throw new IllegalArgumentException("Aggregate is not active");
        }
        raiseEvent(OrderAcceptedEvent.builder()
                .id(command.getId())
                .build());
    }

    public void apply(OrderAcceptedEvent event) {
        this.id = event.getId();
        this.isActive = true;
    }

    public void rejectOrder(OrderRejectCommand command) {
        if (!isActive) {
            throw new IllegalArgumentException("Aggregate is not active");
        }

        raiseEvent(OrderRejectedEvent.builder()
                .reason(command.getReason())
                .build());
    }

    public void apply(OrderRejectedEvent event) {
        this.id = event.getId();
        this.isActive = false;
    }

    public void cancelOrder(OrderCancelCommand command) {
        if (!isActive) {
            throw new IllegalArgumentException("Aggregate is not active");
        }
        raiseEvent(OrderCancelledEvent.builder()
                .id(command.getId())
                .build());
    }

    public void apply(OrderCancelledEvent event) {
        this.id = event.getId();
        this.isActive = false;
    }

    public void deliveredOrder(OrderDeliverCommand command) {
        if (!isActive) {
            throw new IllegalArgumentException("Aggregate is not active");
        }
        raiseEvent(OrderCancelledEvent.builder()
                .id(command.getId()).build());
    }

    public void apply(OrderDeliveredEvent event) {
        this.id = event.getId();
        this.isActive = false;
    }

}
