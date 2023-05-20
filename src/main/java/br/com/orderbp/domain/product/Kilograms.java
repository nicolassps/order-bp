package br.com.orderbp.domain.product;

import java.math.BigDecimal;

class Kilograms extends Weight{

    Kilograms (BigDecimal value){
        super();
        this.value = value;
    }

    @Override
    BigDecimal kilograms() {
        return value;
    }

    @Override
    BigDecimal grams() {
        return value.multiply(BigDecimal.valueOf(1000));
    }
}
