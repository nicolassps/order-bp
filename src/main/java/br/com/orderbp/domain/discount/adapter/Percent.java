package br.com.orderbp.domain.discount.adapter;

import br.com.orderbp.domain.discount.DiscountAdapter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Percent implements DiscountAdapter {
    @Override
    public BigDecimal applyDiscount(BigDecimal discountValue, BigDecimal ofValue) {
        return discountValue.divide(new BigDecimal(100), RoundingMode.UNNECESSARY).multiply(ofValue);
    }
}
