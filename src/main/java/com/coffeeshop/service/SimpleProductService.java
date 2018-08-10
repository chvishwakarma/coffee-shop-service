package com.coffeeshop.service;

import com.coffeeshop.constant.StatusType;
import com.coffeeshop.domain.product.Product;
import com.coffeeshop.domain.product.ProductAssembler;
import com.coffeeshop.domain.product.ProductRequestDTO;
import com.coffeeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chandan Vishwakarma
 */
@Service
@Primary
public class SimpleProductService implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product create(ProductRequestDTO productRequestDTO) {
        Product product = ProductAssembler.fromRequestDTO(productRequestDTO);
        Product created = productRepository.save(product);
        return created;
    }

    @Override
    public Product update(ProductRequestDTO productRequestDTO,Long id) {
        Product product = ProductAssembler.fromRequestDTO(productRequestDTO);
        product.setId(id);
        Product updated = productRepository.save(product);
        return updated;
    }

    @Override
    public Product findByID(Long Id) {
        return productRepository.findOne(Id);
    }

    @Override
    public List<Product> findAllActive() {
        return productRepository.findAllByStatus(StatusType.ACTIVE.getValue());
    }
}
