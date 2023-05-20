package br.com.orderbp.domain.order;

import br.com.orderbp.domain.helper.DiscountHelper;
import br.com.orderbp.domain.helper.ProductHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static br.com.orderbp.domain.order.enumeration.OrderStatus.CREATED;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderTest {

    @Test
    void shouldBuilderWithDifferentItems() {
        var product1 = ProductHelper.createValidProductWithIdentifier("ID1");
        var product2 = ProductHelper.createValidProductWithIdentifier("ID2");

        var orderItem1 = new OrderItem(product1, BigDecimal.TEN, BigDecimal.ONE);
        var orderItem2 = new OrderItem(product2, BigDecimal.TEN, BigDecimal.ONE);

        var order = Order
                .builder()
                .withConsumerDocument("document")
                .withDiscount(null)
                .withItem(orderItem1)
                .withItem(orderItem2)
                .build();

        assert nonNull(order);
        assertEquals(BigDecimal.valueOf(20), order.totalValue());
        assertEquals(2, order.distinctItems());
        assertEquals("document", order.getConsumerDocument());
        assertEquals(CREATED, order.getStatus());
    }

    @Test
    void shouldBuilderWithSameItemAndWithoutDiscount() {
        var product1 = ProductHelper.createValidProduct();
        var product2 = ProductHelper.createValidProduct();

        var orderItem1 = new OrderItem(product1, BigDecimal.TEN, BigDecimal.ONE);
        var orderItem2 = new OrderItem(product2, BigDecimal.TEN, BigDecimal.ONE);

        var order = Order
                .builder()
                .withConsumerDocument("document")
                .withDiscount(null)
                .withItem(orderItem1)
                .withItem(orderItem2)
                .build();

        assert nonNull(order);
        assertEquals(BigDecimal.valueOf(20), order.totalValue());
        assertEquals(1, order.distinctItems());
        assertEquals(BigDecimal.valueOf(2), order.totalQuantity());
        assertEquals("document", order.getConsumerDocument());
        assertEquals(CREATED, order.getStatus());
    }

    @Test
    void shouldBuilderWithSameItemWithPercentDiscount() {
        var product1 = ProductHelper.createValidProduct();
        var product2 = ProductHelper.createValidProduct();

        var orderItem1 = new OrderItem(product1, BigDecimal.TEN, BigDecimal.ONE);
        var orderItem2 = new OrderItem(product2, BigDecimal.TEN, BigDecimal.ONE);

        var order = Order
                .builder()
                .withConsumerDocument("document")
                .withDiscount(DiscountHelper.createValidPercentDiscount())
                .withItem(orderItem1)
                .withItem(orderItem2)
                .build();

        assert nonNull(order);
        assertEquals(0, BigDecimal.valueOf(18.00).compareTo(order.totalValue()));
        assertEquals(1, order.distinctItems());
        assertEquals(BigDecimal.valueOf(2), order.totalQuantity());
        assertEquals("document", order.getConsumerDocument());
        assertEquals(CREATED, order.getStatus());
    }

    @Test
    void shouldBuilderWithSameItemWithIntegralDiscount() {
        var product1 = ProductHelper.createValidProduct();
        var product2 = ProductHelper.createValidProduct();

        var orderItem1 = new OrderItem(product1, BigDecimal.TEN, BigDecimal.ONE);
        var orderItem2 = new OrderItem(product2, BigDecimal.TEN, BigDecimal.ONE);

        var order = Order
                .builder()
                .withConsumerDocument("document")
                .withDiscount(DiscountHelper.createValidIntegralDiscount())
                .withItem(orderItem1)
                .withItem(orderItem2)
                .build();

        assert nonNull(order);
        assertEquals(0, BigDecimal.TEN.compareTo(order.totalValue()));
        assertEquals(1, order.distinctItems());
        assertEquals(BigDecimal.valueOf(2), order.totalQuantity());
        assertEquals("document", order.getConsumerDocument());
        assertEquals(CREATED, order.getStatus());
    }
}