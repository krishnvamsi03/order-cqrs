package com.cqrs.order.orders.api.controller;

import com.cqrs.order.common.dto.BaseResponse;
import com.cqrs.order.core.commands.BaseCommand;
import com.cqrs.order.core.commands.CommandDispatcher;
import com.cqrs.order.orders.api.commands.OrderAcceptCommand;
import com.cqrs.order.orders.api.commands.OrderCancelCommand;
import com.cqrs.order.orders.api.commands.OrderDeliverCommand;
import com.cqrs.order.orders.api.commands.OrderRejectCommand;
import com.cqrs.order.orders.dto.OrderCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1")
public class OrderUpdateController {

    private Logger logger =
            Logger.getLogger(OrderUpdateController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping(path = "/acceptOrder/{id}")
    public ResponseEntity<BaseResponse> acceptOrder(@PathVariable String id) {
        try {
            BaseCommand command = new OrderAcceptCommand();
            command.setId(id);
            commandDispatcher.dispatchCommand(command);
            return new ResponseEntity<>(new OrderCreateResponse("Order " +
                    "updated successfully with status accepted" +
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

    @PutMapping(path = "/rejectOrder/{id}")
    public ResponseEntity<BaseResponse> rejectOrder(@PathVariable String id,
                                                    @RequestBody OrderRejectCommand command) {
        try {
            command.setId(id);
            commandDispatcher.dispatchCommand(command);
            return new ResponseEntity<>(new OrderCreateResponse("Order " +
                    "updated successfully with status rejected" +
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

    @PutMapping(path = "/cancelOrder/{id}")
    public ResponseEntity<BaseResponse> cancelOrder(@PathVariable String id) {
        try {
            BaseCommand command = new OrderCancelCommand();
            command.setId(id);
            commandDispatcher.dispatchCommand(command);
            return new ResponseEntity<>(new OrderCreateResponse("Order " +
                    "updated successfully with status cancelled" +
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

    @PutMapping(path = "/deliverOrder/{id}")
    public ResponseEntity<BaseResponse> deliverOrder(@PathVariable String id) {
        try {
            BaseCommand command = new OrderDeliverCommand();
            command.setId(id);
            commandDispatcher.dispatchCommand(command);
            return new ResponseEntity<>(new OrderCreateResponse("Order " +
                    "updated successfully with status delivered" +
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
