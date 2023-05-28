package br.com.orderbp.domain.product;

import br.com.orderbp.domain.product.exception.InvalidPricingException;
import br.com.orderbp.domain.product.exception.InvalidWeightException;
import br.com.orderbp.domain.product.weight.Weight;
import br.com.orderbp.domain.product.weight.WeightUnit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@Getter
public class Product {
    private String identifier;
    private String name;
    private BigDecimal price;
    private Weight weight;

    public Boolean valid(){
        if (weight.grams().compareTo(BigDecimal.TEN) <= 0)
            throw new InvalidWeightException();

        if (price.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidPricingException();

        return Boolean.TRUE;
    }

    public static ProductBuilder builder(){
        return new ProductBuilder();
    }
}
