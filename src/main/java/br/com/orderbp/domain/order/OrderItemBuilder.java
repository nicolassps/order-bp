package br.com.orderbp.domain.order;

import br.com.orderbp.domain.product.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderItemBuilder {
    private final OrderItem item = new OrderItem();

    public OrderItemBuilder withProduct(Product product){
        item.setProduct(product);
        return this;
    }

    public OrderItemBuilder withQuantity(BigDecimal quantity){
        item.setQuantity(quantity);
        return this;
    }

    public OrderItemBuilder withValue(BigDecimal value){
        item.setValue(value);
        return this;
    }

    public OrderItem build(){
        item.afterSetProperties();
        return item;
    }
}
