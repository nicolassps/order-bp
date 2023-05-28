package br.com.orderbp.domain.adapter.event;

import br.com.orderbp.domain.order.Order;
import br.com.orderbp.domain.product.weight.WeightUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

import static br.com.orderbp.domain.product.weight.WeightUnit.KG;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@Getter
public class RequestShippingEvent {
    private Long orderId;
    private BigDecimal totalValue;
    private BigDecimal totalWeightInKg;
    private BigDecimal totalSize;

    public static RequestShippingEvent fromOrder(Order order){
        return new RequestShippingEvent(order.getId(), order.totalValue(), order.totalWeigth(KG), order.totalQuantity());
    }

}
