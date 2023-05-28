package br.com.orderbp.domain.order.strategy;

import br.com.orderbp.domain.adapter.RequestShippingAdapter;
import br.com.orderbp.domain.adapter.event.RequestShippingEvent;
import br.com.orderbp.domain.order.OrderHelper;
import br.com.orderbp.domain.order.exception.InvalidChangeStateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.orderbp.domain.order.enumeration.OrderStatus.RESERVED;
import static br.com.orderbp.domain.product.weight.WeightUnit.KG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReservedProductTest {

    @InjectMocks
    ReservedProduct strategy;

    @Mock
    RequestShippingAdapter adapter;

    @Test
    void shouldProcess(){
        var order = OrderHelper.createOrderWithCreatedStatus();

        strategy.process(order);

        var eventCaptor = ArgumentCaptor.forClass(RequestShippingEvent.class);
        verify(adapter).request(eventCaptor.capture());
        assertEquals(order.totalValue(), eventCaptor.getValue().getTotalValue());
        assertEquals(order.totalWeigth(KG), eventCaptor.getValue().getTotalWeightInKg());
        assertEquals(order.totalQuantity(), eventCaptor.getValue().getTotalSize());
        assertEquals(order.getId(), eventCaptor.getValue().getOrderId());

        assertEquals(RESERVED, order.getStatus());
    }

    @Test
    void shouldThrowInvalidChangeOnStatusIsNotCreated(){
        var order = OrderHelper.createOrderWithReservedProductStatus();

        assertThrows(InvalidChangeStateException.class, () -> strategy.process(order));
    }

    @Test
    void shouldThrowInvalidChangeOnNotHaveProducts(){
        var order = OrderHelper.createOrderWithCreatedStatusAndEmptyItems();

        assertThrows(InvalidChangeStateException.class, () -> strategy.process(order));
    }
}