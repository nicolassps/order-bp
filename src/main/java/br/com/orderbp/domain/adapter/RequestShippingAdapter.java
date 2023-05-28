package br.com.orderbp.domain.adapter;

import br.com.orderbp.domain.adapter.event.RequestShippingEvent;

public interface RequestShippingAdapter {

    void request(RequestShippingEvent event);
}
