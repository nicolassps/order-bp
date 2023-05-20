package br.com.orderbp.domain.discount.adapter;

import br.com.orderbp.domain.discount.DiscountAdapter;

import java.math.BigDecimal;

public class Integral implements DiscountAdapter {
    @Override
    public BigDecimal applyDiscount(BigDecimal discountValue, BigDecimal ofValue) {
        return discountValue.min(ofValue);
    }
}
