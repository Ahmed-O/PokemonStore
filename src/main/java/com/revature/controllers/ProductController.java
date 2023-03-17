package com.revature.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.revature.services.ProductService;
import com.revature.models.Product;
import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000" }, allowCredentials = "true")

public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

 
    // Getting all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    // Getting product by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return ResponseEntity.status(200).body(productService.getProductById(id));
    }


    // Add a product
    @PostMapping("/products")
    public ResponseEntity<List<Product>> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(201).build();
    }
    

}
