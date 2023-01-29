package com.cqrs.order.orders.api.commands;

import com.cqrs.order.core.commands.BaseCommand;
import com.cqrs.order.core.events.BaseEvent;
import com.cqrs.order.core.payment.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateCommand extends BaseCommand {
    private String customerId;
    private double billAmount;
    private PaymentMode paymentMode;
    private int noOfItems;
}
