package br.com.orderbp.domain.helper;

import br.com.orderbp.domain.discount.Discount;
import br.com.orderbp.domain.discount.DiscountFactory;

import java.math.BigDecimal;

public class DiscountHelper {

    public static Discount createValidPercentDiscount(){
        return DiscountFactory.percent("PERCENT", BigDecimal.TEN);
    }

    public static Discount createValidIntegralDiscount(){
        return DiscountFactory.integral("INTEGRAL", BigDecimal.TEN);
    }
}
