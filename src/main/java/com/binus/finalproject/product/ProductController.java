package com.binus.finalproject.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public Iterable<Product> index() {
        return this.productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product show(@PathVariable int id) {
        return this.productRepository.findById(id).get();
    }

    @GetMapping("/categories/{categoryId}/products")
    public Iterable<Product> getByCategory(@PathVariable int categoryId) {
        return this.productRepository.findByCategoryId(categoryId);
    }

    @PostMapping("/products")
    public Product storeProduct(@RequestBody Product product) {
        return this.productRepository.save(product);
    }
}
