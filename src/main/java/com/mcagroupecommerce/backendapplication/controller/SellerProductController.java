package com.mcagroupecommerce.backendapplication.controller;

import com.mcagroupecommerce.backendapplication.exception.ProductException;
import com.mcagroupecommerce.backendapplication.request.CreateProductRequest;
import com.mcagroupecommerce.backendapplication.responses.ApiResponse;
import com.mcagroupecommerce.backendapplication.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apis/seller")
public class SellerProductController {
    private ProductService productService;
    public SellerProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] reqs) throws ProductException {

        for(CreateProductRequest product:reqs) {
            productService.createProduct(product);
        }

        ApiResponse res=new ApiResponse("products created successfully",true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
    }

}
