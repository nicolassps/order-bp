package br.com.orderbp.domain.adapter.event;

import br.com.orderbp.domain.order.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreatedOrderEvent {
    private Long orderId;

    public static CreatedOrderEvent fromOrder(Order order){
        return new CreatedOrderEvent(order.getId());
    }
}
