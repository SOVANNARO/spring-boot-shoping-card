package org.tutorials.springbootshopingcard.service;

import org.tutorials.springbootshopingcard.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO getProduct(Long productId);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(Long productId, ProductDTO productDTO);
    void deleteProduct(Long productId);
}