package org.tutorials.springbootshopingcard.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tutorials.springbootshopingcard.dto.CartDTO;
import org.tutorials.springbootshopingcard.dto.ProductDTO;
import org.tutorials.springbootshopingcard.entity.Cart;
import org.tutorials.springbootshopingcard.entity.CartItem;
import org.tutorials.springbootshopingcard.entity.Product;
import org.tutorials.springbootshopingcard.mapper.CartMapper;
import org.tutorials.springbootshopingcard.repository.CartRepository;
import org.tutorials.springbootshopingcard.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.tutorials.springbootshopingcard.service.CartService;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Override
    @Transactional
    public CartDTO createCart() {
        Cart cart = new Cart();
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDTO(savedCart);
    }

    @Override
    @Transactional
    public CartDTO getCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        return cartMapper.toDTO(cart);
    }

    @Override
    @Transactional
    public CartDTO addProductToCart(Long cartId, ProductDTO productDTO, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with id: " + cartId));

        Product product;
        if (productDTO.getId() != null) {
            product = productRepository.findById(productDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productDTO.getId()));
        } else {
            product = new Product();
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product = productRepository.save(product);
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cart.getItems().add(cartItem);

        Cart updatedCart = cartRepository.save(cart);
        return cartMapper.toDTO(updatedCart);
    }

    @Override
    @Transactional
    public CartDTO updateCartItem(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product not found in cart"));

        cartItem.setQuantity(quantity);

        Cart updatedCart = cartRepository.save(cart);
        return cartMapper.toDTO(updatedCart);
    }

    @Override
    @Transactional
    public CartDTO removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));

        Cart updatedCart = cartRepository.save(cart);
        return cartMapper.toDTO(updatedCart);
    }

    @Override
    @Transactional
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        cart.getItems().clear();
        cartRepository.save(cart);
    }
}