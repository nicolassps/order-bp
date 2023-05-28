package br.com.orderbp.domain.adapter;

import br.com.orderbp.domain.adapter.event.CreatedOrderEvent;

public interface CreatedOrderAdapter {

    void publish(CreatedOrderEvent event);
}
