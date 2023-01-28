package com.cqrs.order.core.commands;

public interface CommandDispatcher {

    <T extends BaseCommand> void registerCommand(Class<T> type,
                                                 CommandHandlerMethod<T> handlerMethod);

    void dispatchCommand(BaseCommand command);
}
