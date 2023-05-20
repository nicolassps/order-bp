package br.com.orderbp.domain.discount;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscountFactory {

    public static Discount percent(String description, BigDecimal value){
        return new Discount(description, value, DiscountType.PERCENT);
    }

    public static Discount integral(String description, BigDecimal value) {
        return new Discount(description, value, DiscountType.INTEGRAL);
    }
}
