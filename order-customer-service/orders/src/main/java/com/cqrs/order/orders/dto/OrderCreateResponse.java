package com.cqrs.order.orders.dto;

import com.cqrs.order.common.dto.BaseResponse;

public class OrderCreateResponse extends BaseResponse {
    private String id;

    public OrderCreateResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
