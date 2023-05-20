package br.com.orderbp.domain.order;

import br.com.orderbp.domain.discount.Discount;

import static br.com.orderbp.domain.order.enumeration.OrderStatus.CREATED;

class OrderBuilder {
    private final Order order = new Order();

    OrderBuilder(){
        this.order.setStatus(CREATED);
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
