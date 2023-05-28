package br.com.orderbp.domain.order.strategy;

import br.com.orderbp.domain.order.OrderHelper;
import br.com.orderbp.domain.order.exception.InvalidChangeStateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.orderbp.domain.order.enumeration.OrderStatus.RESERVED;
import static br.com.orderbp.domain.order.enumeration.OrderStatus.SCHEDULED_SHIPPING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ShippingCreatedTest {

    @InjectMocks
    ShippingCreated strategy;

    @Test
    void shouldProcess(){
        var order = OrderHelper.createOrderWithReservedProductStatus();

        strategy.process(order);

        assertEquals(SCHEDULED_SHIPPING, order.getStatus());
    }

    @Test
    void shouldThrowInvalidChangeOnStatusIsNotReserved(){
        var order = OrderHelper.createOrderWithCreatedStatus();

        assertThrows(InvalidChangeStateException.class, () -> strategy.process(order));
    }

    @Test
    void shouldThrowInvalidChangeOnNotHaveProducts(){
        var order = OrderHelper.createOrderWithCreatedStatusAndEmptyItems();

        assertThrows(InvalidChangeStateException.class, () -> strategy.process(order));
    }
}