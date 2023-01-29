package com.cqrs.order.orders.api.controller;

import com.cqrs.order.common.dto.BaseResponse;
import com.cqrs.order.core.commands.CommandDispatcher;
import com.cqrs.order.orders.api.commands.OrderCreateCommand;
import com.cqrs.order.orders.dto.OrderCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/createOrder")
public class OrderCreateController {

    private Logger logger =
            Logger.getLogger(OrderCreateController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> createOrder(@RequestBody OrderCreateCommand command) {
        String id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandDispatcher.dispatchCommand(command);
            return new ResponseEntity<>(new OrderCreateResponse("Order " +
                    "created successfully", id), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad" +
                    " request for id - {0}", id));
            return new ResponseEntity<>(new BaseResponse("Invalid arguments " +
                    "passed"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.log(Level.SEVERE, MessageFormat.format("Something went " +
                    "wrong for request {}", e));
            return new ResponseEntity<>(new BaseResponse("Something went " +
                    "wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
