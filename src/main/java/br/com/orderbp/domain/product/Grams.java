package br.com.orderbp.domain.product;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Grams extends Weight {

    Grams (BigDecimal value){
        super();
        this.value = value;
    }

    @Override
    BigDecimal kilograms() {
        return value.divide(BigDecimal.valueOf(1000), RoundingMode.UNNECESSARY);
    }

    @Override
    BigDecimal grams() {
        return value;
    }
}
