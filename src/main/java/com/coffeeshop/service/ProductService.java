package com.coffeeshop.service;

import com.coffeeshop.domain.product.Product;
import com.coffeeshop.domain.product.ProductRequestDTO;
import com.coffeeshop.domain.product.ProductWebDTO;

import java.util.List;

/**
 * @author Chandan Vishwakarma
 */
public interface ProductService {
    Product create(ProductRequestDTO productRequestDTO);
    Product update(ProductRequestDTO productRequestDTO,Long id);
    Product findByID(Long Id);
    List<Product> findAllActive();
}
