package br.com.orderbp.domain.repository;

import br.com.orderbp.domain.product.Product;

public interface ProductRepository {

    Product getByIdentifier(String identifier);
}
