package com.binus.finalproject.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public Iterable<Category> index() {
        return this.categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category show(@PathVariable int id) {
        return this.categoryRepository.findById(id).get();
    }

    @PostMapping("/categories")
    public Category storeCategory(@RequestBody Category category) {
        this.categoryRepository.save(category);
        return category;
    }
}
