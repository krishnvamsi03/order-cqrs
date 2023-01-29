package com.cqrs.order.orders.infrastructure;

import com.cqrs.order.core.domain.AggregatorRoot;
import com.cqrs.order.core.events.BaseEvent;
import com.cqrs.order.core.handler.EventSourceHandler;
import com.cqrs.order.core.infrastructure.EventStore;
import com.cqrs.order.orders.domain.OrderAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class OrderEventSourceHandler implements EventSourceHandler {

    @Autowired
    private EventStore eventStore;

    @Override
    public void save(AggregatorRoot root) {
        eventStore.saveEvent(root.getId(), root.getChanges(),
                root.getVersion());
        root.markChangesAsCommitted();
    }

    @Override
    public OrderAggregate getById(String id) {
        OrderAggregate root = new OrderAggregate();
        List<BaseEvent> events = eventStore.getEvents(id);
        if (events != null || events.size() == 0) {
            root.replayEvents(events);
            int latestVersion =
                    events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder()).get();
            root.setVersion(latestVersion);
        }
        return root;
    }
}
