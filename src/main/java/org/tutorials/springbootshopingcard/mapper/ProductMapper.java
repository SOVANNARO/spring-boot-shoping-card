package org.tutorials.springbootshopingcard.mapper;

import org.mapstruct.Mapper;
import org.tutorials.springbootshopingcard.dto.ProductDTO;
import org.tutorials.springbootshopingcard.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);
}