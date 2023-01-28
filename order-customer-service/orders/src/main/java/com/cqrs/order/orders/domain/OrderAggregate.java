package com.cqrs.order.orders.domain;

import com.cqrs.order.core.commands.BaseCommand;
import com.cqrs.order.core.domain.AggregatorRoot;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderAggregate extends AggregatorRoot {

    private boolean isActive;

    public OrderAggregate(BaseCommand baseCommand) {
    }

    public void apply() {

    }
}
