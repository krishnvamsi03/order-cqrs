package com.cqrs.order.common.events;

import com.cqrs.order.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class OrderDeliveredEvent extends BaseEvent {
}
