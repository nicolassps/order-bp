package br.com.orderbp.domain.order.strategy;

import br.com.orderbp.domain.order.Order;
import br.com.orderbp.domain.order.enumeration.OrderStatus;
import br.com.orderbp.domain.order.exception.InvalidChangeStateException;

import static br.com.orderbp.domain.order.enumeration.OrderStatus.CREATED;

public class ReservedProduct extends ProcessOrderStrategy{

    @Override
    protected void validate(Order order) {
        if (!CREATED.equals(order.getStatus())) {
            throw new InvalidChangeStateException("Invalid operation, from %s to RESERVED".formatted(order.getStatus()));
        }

    }

    @Override
    protected void proceed(Order order) {

    }

    @Override
    OrderStatus compatibility() {
        return CREATED;
    }
}
