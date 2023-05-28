package br.com.orderbp.domain.repository;

import br.com.orderbp.domain.discount.Discount;

public interface DiscountRepository {

    Discount findByCupon(String discountCupon);
}
