package com.cqrs.order.core.commands;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {

    void handle(T BaseCommand);
}
