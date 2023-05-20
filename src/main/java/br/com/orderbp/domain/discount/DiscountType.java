package br.com.orderbp.domain.discount;

import br.com.orderbp.domain.discount.adapter.Integral;
import br.com.orderbp.domain.discount.adapter.Percent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
enum DiscountType {
    PERCENT(new Percent()),
    INTEGRAL(new Integral());

    @Getter
    private final DiscountAdapter adapter;
}
