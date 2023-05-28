package br.com.orderbp.domain.order;

import br.com.orderbp.domain.order.exception.InvalidItemException;
import br.com.orderbp.domain.product.Product;
import br.com.orderbp.domain.product.exception.InvalidPricingException;
import br.com.orderbp.domain.product.weight.Weight;
import br.com.orderbp.domain.product.weight.WeightUnit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static br.com.orderbp.domain.product.weight.WeightUnit.KG;
import static java.util.Objects.isNull;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE )
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
public class OrderItem {
    private Product product;
    private BigDecimal value;
    private BigDecimal quantity;

    public BigDecimal totalPrice(){
        return value.multiply(quantity);
    }

    public BigDecimal totalWeight(WeightUnit unit){
        if(unit.equals(KG))
            return product.getWeight().kilograms().multiply(quantity);

        return product.getWeight().grams().multiply(quantity);
    }

    public void add(BigDecimal quantity){
        this.quantity = this.quantity.add(quantity);
    }

    void afterSetProperties(){
        if (isNull(value) || isNull(quantity) || isNull(product)) {
            throw new InvalidItemException();
        }

        if (value.compareTo(BigDecimal.ZERO) < 0 || quantity.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidItemException();
        }
    }

    public static OrderItemBuilder builder(){
        return new OrderItemBuilder();
    }
}
