package com.cqrs.order.customers.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class OrderEntity {

    @Id
    private String id;

    private String customerId;
    private Date creationDate;
    private double billAmount;
    private Status status;
    private String notes;
}
