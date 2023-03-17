package com.revature.services;

import org.springframework.stereotype.Service;
import com.revature.models.Product;
import com.revature.repos.ProductRepo;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id){
        return productRepo.findById(id).orElse(null);
    }

    public void addProduct(Product product){
        productRepo.save(product);
    }

}
