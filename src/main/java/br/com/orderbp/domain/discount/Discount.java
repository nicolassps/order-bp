package br.com.orderbp.domain.discount;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Discount {
    @Getter
    private String label;
    private BigDecimal value;
    private DiscountType type;

    public BigDecimal apply(BigDecimal totalValue){
        return type.getAdapter().applyDiscount(value, totalValue);
    }
}
