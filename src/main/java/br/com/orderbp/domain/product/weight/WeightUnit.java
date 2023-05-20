package br.com.orderbp.domain.product.weight;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WeightUnit {
    KG(Kilograms::new),
    G(Grams::new);

    private final Function<BigDecimal, Weight> create;

    Weight instance(BigDecimal value){
        return create.apply(value);
    }
}
