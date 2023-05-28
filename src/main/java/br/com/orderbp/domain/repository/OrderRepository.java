package br.com.orderbp.domain.repository;

import br.com.orderbp.domain.order.Order;

public interface OrderRepository {

    Order create(Order order);

    void update(Order order);

    Order findById(Long id);
}
