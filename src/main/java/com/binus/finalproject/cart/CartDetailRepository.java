package com.binus.finalproject.cart;

import org.springframework.data.repository.CrudRepository;

public interface CartDetailRepository extends CrudRepository<CartDetail, Integer> {

    public Iterable<CartDetail> findByCartId(int cartId);
    public CartDetail findByCartIdAndProductId(int cartId, int productId);
    public boolean existsByCartIdAndProductId(int cartId, int productId);
}
