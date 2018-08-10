package com.coffeeshop.repository;

import com.coffeeshop.domain.product.Product;

import java.util.List;

/**
 * Product repository for database related queries
 * @author Chandan Vishwakarma
 */
public interface ProductRepository extends BaseRepository<Product,Long>{
    List<Product> findAllByStatus(int status);
}
