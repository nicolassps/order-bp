package br.com.orderbp.domain.product;

import br.com.orderbp.domain.product.weight.Weight;
import br.com.orderbp.domain.product.weight.WeightUnit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ProductBuilder {
    private final Product product = new Product();

    public ProductBuilder withIdentifier(String identifier){
        product.setIdentifier(identifier);
        return this;
    }

    public ProductBuilder withName(String name){
        product.setName(name);
        return this;
    }

    public ProductBuilder withPrice(BigDecimal price){
        product.setPrice(price);
        return this;
    }

    public ProductBuilder withWeight(BigDecimal value, WeightUnit unit){
        product.setWeight(Weight.of(value, unit));
        return this;
    }

    public Product build(){
        product.valid();
        return product;
    }
}
