package br.com.orderbp.domain.repository;

import br.com.orderbp.domain.order.Order;

public interface OrderRepository {

    void create(Order order);

}
