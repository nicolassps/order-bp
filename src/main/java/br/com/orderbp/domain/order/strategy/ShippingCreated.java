package br.com.orderbp.domain.order.strategy;

import br.com.orderbp.domain.order.Order;
import br.com.orderbp.domain.order.enumeration.OrderStatus;
import br.com.orderbp.domain.order.exception.InvalidChangeStateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static br.com.orderbp.domain.order.enumeration.OrderStatus.RESERVED;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class ShippingCreated extends ProcessOrderStrategy{
    @Override
    protected void validate(final Order order) {
        if (isNull(order.getId()) || !RESERVED.equals(order.getStatus())) {
            throw new InvalidChangeStateException("Invalid operation, from %s to SCHEDULED_SHIPPING".formatted(order.getStatus()));
        }
    }

    @Override
    protected void proceed(final Order order) {
        if (order.distinctItems() == 0 || order.totalQuantity().compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidChangeStateException("Doesn't meet pre-requests to Reserve Products");
        }

        order.createdShipping();
    }

    @Override
    OrderStatus compatibility() {
        return RESERVED;
    }
}
