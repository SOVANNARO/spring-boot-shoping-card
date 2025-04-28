package org.tutorials.springbootshopingcard.service;

import org.tutorials.springbootshopingcard.dto.CartDTO;
import org.tutorials.springbootshopingcard.dto.ProductDTO;

public interface CartService {
    CartDTO createCart();
    CartDTO getCart(Long cartId);
    CartDTO addProductToCart(Long cartId, ProductDTO productDTO, int quantity);
    CartDTO updateCartItem(Long cartId, Long productId, int quantity);
    CartDTO removeProductFromCart(Long cartId, Long productId);
    void clearCart(Long cartId);
}