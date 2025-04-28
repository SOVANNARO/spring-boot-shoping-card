package org.tutorials.springbootshopingcard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tutorials.springbootshopingcard.dto.CartDTO;
import org.tutorials.springbootshopingcard.dto.ProductDTO;
import org.tutorials.springbootshopingcard.service.CartService;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDTO> createCart() {
        CartDTO createdCart = cartService.createCart();
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long cartId) {
        CartDTO cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartDTO> addProductToCart(
            @PathVariable Long cartId,
            @RequestBody ProductDTO productDTO,
            @RequestParam int quantity) {
        CartDTO updatedCart = cartService.addProductToCart(cartId, productDTO, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<CartDTO> updateCartItem(@PathVariable Long cartId,
                                                  @PathVariable Long productId,
                                                  @RequestParam int quantity) {
        CartDTO updatedCart = cartService.updateCartItem(cartId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<CartDTO> removeProductFromCart(@PathVariable Long cartId,
                                                         @PathVariable Long productId) {
        CartDTO updatedCart = cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }
}