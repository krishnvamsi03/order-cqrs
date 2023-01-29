package com.cqrs.order.common.events;

import com.cqrs.order.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderRejectedEvent extends BaseEvent {
    private String reason;
}
