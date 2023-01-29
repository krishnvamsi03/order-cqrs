package com.cqrs.order.orders.repository;

import com.cqrs.order.core.domain.EventModel;
import com.cqrs.order.core.events.BaseEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoreRepository extends MongoRepository<EventModel, String> {

    List<EventModel> findByAggregateId(String id);
}
