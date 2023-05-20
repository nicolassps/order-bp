package br.com.orderbp.domain.order;

import br.com.orderbp.domain.discount.Discount;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

class OrderBuilder {
    private Order order;

    OrderBuilder(){
        this.order = new Order();
    }

    public OrderBuilder withDiscount(Discount discount){
        this.order.setDiscount(discount);
        return this;
    }

    public OrderBuilder withConsumerDocument(String document){
        this.order.setConsumerDocument(document);
        return this;
    }

    public OrderBuilder withItem(OrderItem item){
        this.order.acceptItem(item);
        return this;
    }

    public Order build(){
        return this.order;
    }
}
