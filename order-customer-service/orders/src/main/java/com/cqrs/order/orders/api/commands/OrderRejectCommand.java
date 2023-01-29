package com.cqrs.order.orders.api.commands;

import com.cqrs.order.core.commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRejectCommand extends BaseCommand {
    private String reason;
}
