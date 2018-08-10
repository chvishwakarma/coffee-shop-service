package com.coffeeshop.domain.product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chandan Vishwakarma
 */
public class ProductAssembler {


    public static Product fromRequestDTO(ProductRequestDTO productRequestDTO){
        final Product product = new Product();
        product.setStatus(productRequestDTO.getStatus());
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setTotalQuantity(productRequestDTO.getTotalQuantity());
        product.setAvailableQuantity(productRequestDTO.getAvailableQuantity());
        return product;
    }

    public static ProductWebDTO fromProduct(Product product){
        final ProductWebDTO productWebDTO = new ProductWebDTO();
        productWebDTO.setId(product.getId());
        productWebDTO.setName(product.getName());
        productWebDTO.setDescription(product.getDescription());
        productWebDTO.setPrice(product.getPrice());
        productWebDTO.setTotalQuantity(product.getTotalQuantity());
        productWebDTO.setAvailableQuantity(product.getAvailableQuantity());
        productWebDTO.setStatus(product.getStatus());
        return productWebDTO;
    }

    public static ProductItemDTO fromProductToItem(Product product){
        final ProductItemDTO productItemDTO = new ProductItemDTO();
        productItemDTO.setId(product.getId());
        productItemDTO.setName(product.getName());
        productItemDTO.setDescription(product.getDescription());
        productItemDTO.setPrice(product.getPrice());
        return productItemDTO;
    }

    public static List<ProductItemDTO> fromProductListToItemList(List<Product> productList){
        final List<ProductItemDTO> webDTOList = new ArrayList<>();
        for (Product product : productList){
            ProductItemDTO productItemDTO = new ProductItemDTO();
            productItemDTO.setId(product.getId());
            productItemDTO.setName(product.getName());
            productItemDTO.setDescription(product.getDescription());
            productItemDTO.setPrice(product.getPrice());
            webDTOList.add(productItemDTO);
        }
        return webDTOList;
    }

    public static List<ProductWebDTO> fromProductList(List<Product> productList){
        final List<ProductWebDTO> webDTOList = new ArrayList<>();
        for (Product product : productList){
            ProductWebDTO productWebDTO = new ProductWebDTO();
            productWebDTO.setId(product.getId());
            productWebDTO.setName(product.getName());
            productWebDTO.setDescription(product.getDescription());
            productWebDTO.setPrice(product.getPrice());
            productWebDTO.setTotalQuantity(product.getTotalQuantity());
            productWebDTO.setAvailableQuantity(product.getAvailableQuantity());
            productWebDTO.setStatus(product.getStatus());
            webDTOList.add(productWebDTO);
        }
        return webDTOList;
    }
}
