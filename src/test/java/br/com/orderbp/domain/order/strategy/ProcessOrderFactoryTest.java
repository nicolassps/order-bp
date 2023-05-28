package br.com.orderbp.domain.order.strategy;

import br.com.orderbp.domain.adapter.RequestShippingAdapter;
import br.com.orderbp.domain.order.OrderHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static br.com.orderbp.domain.order.enumeration.OrderStatus.RESERVED;
import static br.com.orderbp.domain.order.enumeration.OrderStatus.SCHEDULED_SHIPPING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessOrderFactoryTest {

    @Mock
    RequestShippingAdapter adapter;

    ProcessOrderFactory factory;

    @BeforeEach
    void setup(){
        this.factory = new ProcessOrderFactory(Set.of(new ReservedProduct(adapter), new ShippingCreated()));
    }

    @Test
    void shouldProcessOrderWithCreatedStatus() {
        var order = OrderHelper.createOrderWithCreatedStatus();

        factory.process(order);
        assertEquals(RESERVED, order.getStatus());
    }

    @Test
    void shouldProcessOrderWithReservedStatus() {
        var order = OrderHelper.createOrderWithReservedProductStatus();

        factory.process(order);
        assertEquals(SCHEDULED_SHIPPING, order.getStatus());
    }

    @Test
    void shouldNotProcessOrderWithAnyOtherStatus() {
        var order = OrderHelper.createOrderWithScheduledShippingStatus();

        assertThrows(IllegalStateException.class, () -> factory.process(order));
    }

    @Test
    void shouldNotProcessOrderWithNullStatus() {
        var order = OrderHelper.createOrderWithScheduledShippingStatus();

        assertThrows(IllegalStateException.class, () -> factory.process(order));
    }
}