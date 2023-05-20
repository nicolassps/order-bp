package br.com.orderbp.domain.product;

import br.com.orderbp.domain.product.exception.InvalidPricingException;
import br.com.orderbp.domain.product.exception.InvalidWeightException;
import br.com.orderbp.domain.product.weight.Weight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static br.com.orderbp.domain.product.weight.WeightUnit.G;
import static br.com.orderbp.domain.product.weight.WeightUnit.KG;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ProductTest {

    @Test
    void shouldCreateAndValidateProduct(){
        var weight = Weight.of(BigDecimal.valueOf(1000), G);
        var product = new Product("PG-2981294", "MOUSE", BigDecimal.valueOf(29.90), weight);

        assertTrue(product.valid());
    }

    @Test
    void shouldCreateAndInvalidOnLightWeightInGrams(){
        var weight = Weight.of(BigDecimal.valueOf(5), G);
        var product = new Product("PG-2981294", "MOUSE", BigDecimal.valueOf(29.90), weight);

        assertThrows(InvalidWeightException.class, product::valid);
    }

    @Test
    void shouldCreateAndInvalidOnLightWeightInKilograms(){
        var weight = Weight.of(BigDecimal.valueOf(0.005), KG);
        var product = new Product("PG-2981294", "MOUSE", BigDecimal.valueOf(29.90), weight);

        assertThrows(InvalidWeightException.class, product::valid);
    }

    @Test
    void shouldCreateAndInvalidOnNegativePrice(){
        var weight = Weight.of(BigDecimal.valueOf(1), KG);
        var product = new Product("PG-2981294", "MOUSE", BigDecimal.valueOf(-29.90), weight);

        assertThrows(InvalidPricingException.class, product::valid);
    }

}