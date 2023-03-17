package com.revature.repos;

import com.revature.models.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String name);
}
