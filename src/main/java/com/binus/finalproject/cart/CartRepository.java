package com.binus.finalproject.cart;

import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Integer> {

    public boolean existsByUserId(int userId);
    public Cart findByUserId(int userId);
}
