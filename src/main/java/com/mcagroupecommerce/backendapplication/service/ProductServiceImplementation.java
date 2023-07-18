package com.mcagroupecommerce.backendapplication.service;

import com.mcagroupecommerce.backendapplication.exception.ProductException;
import com.mcagroupecommerce.backendapplication.model.Category;
import com.mcagroupecommerce.backendapplication.model.Product;
import com.mcagroupecommerce.backendapplication.repository.CategoryRepository;
import com.mcagroupecommerce.backendapplication.repository.ProductRepository;
import com.mcagroupecommerce.backendapplication.request.CreateProductRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ProductServiceImplementation implements ProductService {
    private ProductRepository productRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;

    public ProductServiceImplementation(ProductRepository productRepository
            ,UserService userService,CategoryRepository categoryRepository
    ) {
        this.productRepository=productRepository;
        this.userService=userService;
        this.categoryRepository=categoryRepository;
    }


    @Override
    public Product createProduct(CreateProductRequest req) {

        Category topLevel=categoryRepository.findByName(req.getTopLavelCategory());

        if(topLevel==null) {

            Category topLavelCategory=new Category();
            topLavelCategory.setName(req.getTopLavelCategory());
            topLavelCategory.setLevel(1);

            topLevel= categoryRepository.save(topLavelCategory);
        }

        Category secondLevel=categoryRepository.
                findByNameAndParant(req.getSecondLavelCategory(),topLevel.getName());
        if(secondLevel==null) {

            Category secondLavelCategory=new Category();
            secondLavelCategory.setName(req.getSecondLavelCategory());
            secondLavelCategory.setParentCategory(topLevel);
            secondLavelCategory.setLevel(2);

            secondLevel= categoryRepository.save(secondLavelCategory);
        }

        Category thirdLevel=categoryRepository.findByNameAndParant(req.getThirdLavelCategory(),secondLevel.getName());
        if(thirdLevel==null) {

            Category thirdLavelCategory=new Category();
            thirdLavelCategory.setName(req.getThirdLavelCategory());
            thirdLavelCategory.setParentCategory(secondLevel);
            thirdLavelCategory.setLevel(3);

            thirdLevel=categoryRepository.save(thirdLavelCategory);
        }


        Product product=new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountPersent(req.getDiscountPersent());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct= productRepository.save(product);

        System.out.println("products - "+product);

        return savedProduct;
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }



}
