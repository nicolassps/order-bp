package br.com.orderbp.domain.order.strategy;

import br.com.orderbp.domain.order.Order;
import br.com.orderbp.domain.order.enumeration.OrderStatus;

public abstract class ProcessOrderStrategy {

    void process(Order order){
        this.validate(order);
        this.proceed(order);
    };

    protected abstract void validate(Order order);

    protected abstract void proceed(Order order);

    abstract OrderStatus compatibility();
}
