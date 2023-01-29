package com.cqrs.order.common.events;

import com.cqrs.order.core.events.BaseEvent;
import com.cqrs.order.core.payment.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderCreatedEvent extends BaseEvent {
    private String customerId;
    private Date createdDate;
    private double billAmount;

    private PaymentMode paymentMode;

}
