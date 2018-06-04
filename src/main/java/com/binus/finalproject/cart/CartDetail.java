package com.binus.finalproject.cart;

import com.binus.finalproject.product.Product;

import javax.persistence.*;

@Entity
@Table(name="carts_detail")
public class CartDetail {

    @Id
    private int id;
    private int cartId;
    private int productId;
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
