package com.mcagroupecommerce.backendapplication.controller;

import com.mcagroupecommerce.backendapplication.exception.ProductException;
import com.mcagroupecommerce.backendapplication.model.Product;
import com.mcagroupecommerce.backendapplication.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis")
public class UserProductController {

    private ProductService productService;

    public UserProductController(ProductService productService) {
        this.productService=productService;
    }


    @GetMapping("/all/products")
    public ResponseEntity<List<Product>> findAllProduct(){

        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

}
