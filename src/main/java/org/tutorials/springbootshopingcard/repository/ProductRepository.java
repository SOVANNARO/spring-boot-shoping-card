package org.tutorials.springbootshopingcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tutorials.springbootshopingcard.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}