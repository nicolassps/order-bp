package br.com.orderbp.domain.discount.adapter;

import br.com.orderbp.domain.discount.DiscountAdapter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Percent implements DiscountAdapter {
    @Override
    public BigDecimal applyDiscount(BigDecimal discountValue, BigDecimal ofValue) {
        discountValue = discountValue.setScale(4, RoundingMode.HALF_UP);
        var totalDiscount = discountValue.divide(new BigDecimal("100.00"), RoundingMode.HALF_UP)
                .multiply(ofValue);

        return ofValue.subtract(totalDiscount);
    }
}
