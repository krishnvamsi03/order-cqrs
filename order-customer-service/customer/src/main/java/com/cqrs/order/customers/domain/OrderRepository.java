package com.cqrs.order.customers.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {

    Optional<OrderEntity> findByCustomerId(String id);
}
