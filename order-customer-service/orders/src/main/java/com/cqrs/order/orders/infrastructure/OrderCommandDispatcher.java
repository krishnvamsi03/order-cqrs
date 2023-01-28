package com.cqrs.order.orders.infrastructure;

import com.cqrs.order.core.commands.BaseCommand;
import com.cqrs.order.core.commands.CommandDispatcher;
import com.cqrs.order.core.commands.CommandHandlerMethod;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class OrderCommandDispatcher implements CommandDispatcher {

    private Map<Class<? extends BaseCommand>, CommandHandlerMethod> routes =
            new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerCommand(Class<T> type,
                                                        CommandHandlerMethod<T> handlerMethod) {
        routes.putIfAbsent(type, handlerMethod);
    }

    @Override
    public void dispatchCommand(BaseCommand command) {
        Class<? extends BaseCommand> type = command.getClass();
        if (!routes.containsKey(type)) {
            throw new IllegalArgumentException(MessageFormat.format("Command " +
                    "of type {} is not registered with dispatcher", type));
        }
        routes.get(type).handle(command);
    }
}
