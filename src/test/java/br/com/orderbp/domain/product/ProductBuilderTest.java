package br.com.orderbp.domain.product;

import br.com.orderbp.domain.product.exception.InvalidPricingException;
import br.com.orderbp.domain.product.exception.InvalidWeightException;
import br.com.orderbp.domain.product.weight.Weight;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.com.orderbp.domain.product.weight.WeightUnit.G;
import static br.com.orderbp.domain.product.weight.WeightUnit.KG;
import static org.junit.jupiter.api.Assertions.*;

class ProductBuilderTest {

    @Test
    void shouldCreateAndValidateProduct(){
        var product = Product
                .builder()
                .withIdentifier("PG-2981294")
                .withName("MOUSE")
                .withPrice(BigDecimal.valueOf(29.90))
                .withWeight(BigDecimal.valueOf(1000), G)
                .build();

        assertTrue(product.valid());
    }

    @Test
    void shouldCreateAndInvalidOnLightWeightInGrams(){
        var builder = Product
                .builder()
                .withIdentifier("PG-2981294")
                .withName("MOUSE")
                .withPrice(BigDecimal.valueOf(29.90))
                .withWeight(BigDecimal.valueOf(5), G);

        assertThrows(InvalidWeightException.class, builder::build);
    }

    @Test
    void shouldCreateAndInvalidOnLightWeightInKilograms(){
        var builder = Product
                .builder()
                .withIdentifier("PG-2981294")
                .withName("MOUSE")
                .withPrice(BigDecimal.valueOf(29.90))
                .withWeight(BigDecimal.valueOf(0.005), KG);

        assertThrows(InvalidWeightException.class, builder::build);
    }

    @Test
    void shouldCreateAndInvalidOnNegativePrice(){
        var builder = Product
                .builder()
                .withIdentifier("PG-2981294")
                .withName("MOUSE")
                .withPrice(BigDecimal.valueOf(-29.90))
                .withWeight(BigDecimal.valueOf(1), KG);

        assertThrows(InvalidPricingException.class, builder::build);
    }

}