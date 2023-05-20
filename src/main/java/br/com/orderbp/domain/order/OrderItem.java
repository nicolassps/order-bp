package br.com.orderbp.domain.order;

import br.com.orderbp.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderItem {
    private Product product;
    private BigDecimal value;
    private BigDecimal quantity;

    public BigDecimal totalPrice(){
        return value.multiply(quantity);
    }

    public void add(BigDecimal quantity){
        this.quantity = this.quantity.add(quantity);
    }
}
