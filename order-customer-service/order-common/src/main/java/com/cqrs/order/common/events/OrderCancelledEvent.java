package com.cqrs.order.common.events;

import com.cqrs.order.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderCancelledEvent extends BaseEvent {
}
