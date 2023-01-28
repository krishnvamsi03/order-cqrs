package com.cqrs.order.core.handler;

import com.cqrs.order.core.domain.AggregatorRoot;

public interface EventSourceHandler<T> {

    void save(AggregatorRoot root);

    T getById(String id);
}
