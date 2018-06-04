package com.binus.finalproject.product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    public Iterable<Product> findByCategoryId(int categoryId);
}
