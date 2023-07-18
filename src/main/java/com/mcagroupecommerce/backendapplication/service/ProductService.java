package com.mcagroupecommerce.backendapplication.service;

import com.mcagroupecommerce.backendapplication.exception.ProductException;
import com.mcagroupecommerce.backendapplication.model.Product;
import com.mcagroupecommerce.backendapplication.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest req) throws ProductException;
    public List<Product> getAllProducts();

}
