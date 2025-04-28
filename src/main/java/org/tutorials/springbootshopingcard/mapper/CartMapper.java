package org.tutorials.springbootshopingcard.mapper;

import org.mapstruct.Mapper;
import org.tutorials.springbootshopingcard.dto.CartDTO;
import org.tutorials.springbootshopingcard.entity.Cart;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDTO toDTO(Cart cart);
    Cart toEntity(CartDTO cartDTO);
}