package com.mcagroupecommerce.backendapplication.repository;

import com.mcagroupecommerce.backendapplication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
