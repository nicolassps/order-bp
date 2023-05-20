package br.com.orderbp.domain.product.weight;

import java.math.BigDecimal;

class Kilograms extends Weight{

    Kilograms (BigDecimal value){
        super();
        this.value = value;
    }

    @Override
    public BigDecimal kilograms() {
        return value;
    }

    @Override
    public BigDecimal grams() {
        return value.multiply(BigDecimal.valueOf(1000));
    }
}
