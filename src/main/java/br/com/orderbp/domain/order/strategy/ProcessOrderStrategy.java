package br.com.orderbp.domain.order.strategy;

import br.com.orderbp.domain.order.Order;
import br.com.orderbp.domain.order.enumeration.OrderStatus;

abstract class ProcessOrderStrategy {

    void process(final Order order){
        this.validate(order);
        this.proceed(order);
    };

    protected abstract void validate(final Order order);

    protected abstract void proceed(final Order order);

    abstract OrderStatus compatibility();
}
