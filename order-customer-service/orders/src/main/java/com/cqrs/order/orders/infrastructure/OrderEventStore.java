package com.cqrs.order.orders.infrastructure;

import com.cqrs.order.core.domain.EventModel;
import com.cqrs.order.core.events.BaseEvent;
import com.cqrs.order.core.infrastructure.EventStore;
import com.cqrs.order.core.producer.EventProducer;
import com.cqrs.order.orders.domain.OrderAggregate;
import com.cqrs.order.orders.repository.EventStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderEventStore implements EventStore {

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public void saveEvent(String aggregateId, List<BaseEvent> events,
                          int expectedVersion) {

        List<EventModel> prevEvents =
                eventStoreRepository.findByAggregateId(aggregateId);
        if (expectedVersion != -1 && (prevEvents.get(prevEvents.size() - 1).getVersion() != expectedVersion)) {
            throw new RuntimeException("Concurrency exception");
        }

        int version = expectedVersion;
        for (BaseEvent event : events) {
            version++;
            event.setVersion(version);
            EventModel model = EventModel.builder()
                    .timeStamp(new Date())
                    .aggregateType(OrderAggregate.class.getSimpleName())
                    .eventType(event.getClass().getTypeName())
                    .aggregateId(aggregateId)
                    .version(version)
                    .eventData(event)
                    .build();
            var persistedStore = eventStoreRepository.save(model);
            if (!persistedStore.getId().isEmpty()) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }

    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        List<EventModel> prevEvents =
                eventStoreRepository.findByAggregateId(aggregateId);
        if (prevEvents != null || prevEvents.size() != 0) {
            throw new RuntimeException("No events found for aggregate id");
        }
        return prevEvents.stream().map(x -> x.getEventData()).collect(Collectors.toList());
    }

    @Override
    public List<String> getAggregateIds() {
        List<EventModel> prevEvents = eventStoreRepository.findAll();
        if (prevEvents != null || prevEvents.size() != 0) {
            throw new RuntimeException("No events");
        }
        return prevEvents.stream().map(x -> x.getAggregateId()).collect(Collectors.toList());
    }
}
