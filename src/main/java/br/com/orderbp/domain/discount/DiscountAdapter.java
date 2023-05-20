package br.com.orderbp.domain.discount;

import java.math.BigDecimal;

public interface DiscountAdapter {

    BigDecimal applyDiscount(BigDecimal discountValue, BigDecimal ofValue);
}
