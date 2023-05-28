package br.com.orderbp.domain.order;

import br.com.orderbp.domain.helper.DiscountHelper;
import br.com.orderbp.domain.helper.ProductHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class OrderHelper {

    public static Order createSavedOrder(){
        var order = createUnsavedOrder();
        order.setId(10L);
        return order;
    }

    public static Order createUnsavedOrder(){
        var product1 = ProductHelper.createValidProduct();
        var product2 = ProductHelper.createValidProduct();

        var orderItem1 = new OrderItem(product1, BigDecimal.TEN, BigDecimal.ONE);
        var orderItem2 = new OrderItem(product2, BigDecimal.TEN, BigDecimal.ONE);

        return Order
                .builder()
                .withConsumerDocument("document")
                .withDiscount(DiscountHelper.createValidPercentDiscount())
                .withItem(orderItem1)
                .withItem(orderItem2)
                .build();
    }

    public static Order createOrderWithCreatedStatus(){
        return createSavedOrder();
    }

    public static Order createOrderWithCreatedStatusAndEmptyItems(){
        var order = createOrderWithCreatedStatus();
        order.setItems(new ArrayList<>());
        return order;
    }

    public static Order createOrderWithReservedProductStatus(){
        var order = createSavedOrder();
        order.acceptReserve();
        return order;
    }

    public static Order createOrderWithReservedProductStatusAndEmptyItems(){
        var order = createOrderWithCreatedStatusAndEmptyItems();
        order.acceptReserve();
        return order;
    }

    public static Order createOrderWithScheduledShippingStatus(){
        var order = createSavedOrder();
        order.createdShipping();
        return order;
    }

    public static Order createOrderWithNullStatus(){
        var order = createSavedOrder();
        order.setStatus(null);
        return order;
    }
}
