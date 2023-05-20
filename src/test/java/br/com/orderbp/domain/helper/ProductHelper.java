package br.com.orderbp.domain.helper;

import br.com.orderbp.domain.product.Product;

import java.math.BigDecimal;

import static br.com.orderbp.domain.product.weight.WeightUnit.G;

public class ProductHelper {

    public static Product createValidProduct(){
        return Product
                .builder()
                .withIdentifier("PG-2981294")
                .withName("MOUSE")
                .withPrice(BigDecimal.valueOf(29.90))
                .withWeight(BigDecimal.valueOf(1000), G)
                .build();
    }

    public static Product createValidProductWithIdentifier(String identifier){
        return Product
                .builder()
                .withIdentifier(identifier)
                .withName("MOUSE")
                .withPrice(BigDecimal.valueOf(29.90))
                .withWeight(BigDecimal.valueOf(1000), G)
                .build();
    }
}
