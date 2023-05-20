package br.com.orderbp.domain.product.weight;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WeightTest {

    @Test
    void shouldCreateKGWeightAndReturnValues(){
        var weight = Weight.of(BigDecimal.TEN, WeightUnit.KG);

        assertEquals(Kilograms.class, weight.getClass());
        assertEquals(BigDecimal.valueOf(10000), weight.grams());
        assertEquals(BigDecimal.valueOf(10), weight.kilograms());
    }

    @Test
    void shouldCreateGWeightAndReturnValues(){
        var weight = Weight.of(BigDecimal.valueOf(2000), WeightUnit.G);

        assertEquals(Grams.class, weight.getClass());
        assertEquals(BigDecimal.valueOf(2000), weight.grams());
        assertEquals(BigDecimal.valueOf(2), weight.kilograms());
    }

}