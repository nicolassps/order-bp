package br.com.orderbp.domain.product;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Weight {
    protected BigDecimal value;

    abstract BigDecimal kilograms();

    abstract BigDecimal grams();

    public Weight of(BigDecimal value, WeightUnit unit){
        return unit.instance(value);
    }
}
