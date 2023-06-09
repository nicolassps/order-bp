package br.com.orderbp.domain.order.strategy;

import br.com.orderbp.domain.adapter.RequestShippingAdapter;
import br.com.orderbp.domain.adapter.event.RequestShippingEvent;
import br.com.orderbp.domain.order.Order;
import br.com.orderbp.domain.order.enumeration.OrderStatus;
import br.com.orderbp.domain.order.exception.InvalidChangeStateException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static br.com.orderbp.domain.order.enumeration.OrderStatus.CREATED;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
class ReservedProduct extends ProcessOrderStrategy{

    private final RequestShippingAdapter requestShippingAdapter;

    @Override
    protected void validate(final Order order) {
        if (isNull(order.getId()) || !CREATED.equals(order.getStatus())) {
            throw new InvalidChangeStateException("Invalid operation, from %s to RESERVED".formatted(order.getStatus()));
        }
    }

    @Override
    protected void proceed(final Order order) {
        if (order.distinctItems() == 0 || order.totalQuantity().compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidChangeStateException("Doesn't meet pre-requests to Reserve Products");
        }

        order.acceptReserve();
        requestShippingAdapter.request(RequestShippingEvent.fromOrder(order));
    }

    @Override
    OrderStatus compatibility() {
        return CREATED;
    }
}
