package org.tutorials.springbootshopingcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tutorials.springbootshopingcard.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}