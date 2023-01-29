package com.cqrs.order.orders;

import com.cqrs.order.core.commands.CommandDispatcher;
import com.cqrs.order.orders.api.commands.*;
import com.cqrs.order.orders.api.handler.CommandHandler;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderApplication {

    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private CommandDispatcher dispatcher;


    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @PostConstruct
    public void registerHandler() {
        dispatcher.registerCommand(OrderCreateCommand.class, commandHandler::handle);
        dispatcher.registerCommand(OrderAcceptCommand.class, commandHandler::handle);
        dispatcher.registerCommand(OrderRejectCommand.class, commandHandler::handle);
        dispatcher.registerCommand(OrderCancelCommand.class, commandHandler::handle);
        dispatcher.registerCommand(OrderDeliverCommand.class, commandHandler::handle);
    }


}
