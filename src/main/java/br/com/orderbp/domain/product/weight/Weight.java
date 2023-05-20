package br.com.orderbp.domain.product.weight;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Weight {
    protected BigDecimal value;

    public abstract BigDecimal kilograms();

    public abstract BigDecimal grams();

    public static Weight of(BigDecimal value, WeightUnit unit){
        return unit.instance(value);
    }
}
