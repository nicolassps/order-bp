package br.com.orderbp.domain.product;

import br.com.orderbp.domain.product.Weight;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WeightUnit {
    KG(Kilograms::new),
    G(Grams::new);

    private Function<BigDecimal, Weight> create;

    Weight instance(BigDecimal value){
        return create.apply(value);
    }
}
