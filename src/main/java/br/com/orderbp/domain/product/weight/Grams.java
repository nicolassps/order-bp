package br.com.orderbp.domain.product.weight;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Grams extends Weight {

    Grams (BigDecimal value){
        super();
        this.value = value;
    }

    @Override
    public BigDecimal kilograms() {
        return value.divide(BigDecimal.valueOf(1000), RoundingMode.UNNECESSARY);
    }

    @Override
    public BigDecimal grams() {
        return value;
    }
}
