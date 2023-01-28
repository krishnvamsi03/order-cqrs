package com.cqrs.order.core.infrastructure;

import com.cqrs.order.core.domain.AggregatorRoot;
import com.cqrs.order.core.events.BaseEvent;

import java.util.List;

public interface EventStore {

    void saveEvent(String aggregateId, List<BaseEvent> events, int expectedVersion);

    List<BaseEvent> getEvents(String aggregateId);

    List<String> getAggregateIds();
}
