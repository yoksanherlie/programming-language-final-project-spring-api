package com.binus.finalproject.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;

    private Cart getUserCart(int userId) {
        if (this.cartRepository.existsByUserId(userId)) {
            return this.cartRepository.findByUserId(userId);
        } else {
            Cart cart = new Cart();
            cart.setUserId(userId);
            return this.cartRepository.save(cart);
        }
    }

    @GetMapping("carts/{user_id}")
    public Iterable<CartDetail> index(@PathVariable int user_id) {
        Cart cart = this.getUserCart(user_id);

        return this.cartDetailRepository.findByCartId(cart.getId());
    }

    @PostMapping("carts/{user_id}")
    public CartDetail storeCart(@PathVariable int user_id, @RequestBody CartDetail cartDetail) {
        Cart cart = this.getUserCart(user_id);

        boolean exist = this.cartDetailRepository.existsByCartIdAndProductId(cart.getId(), cartDetail.getProductId());
        if (!exist) {
            return this.cartDetailRepository.save(cartDetail);
        } else {
            CartDetail itemDetail = this.cartDetailRepository.findByCartIdAndProductId(cart.getId(), cartDetail.getProductId());
            itemDetail.setAmount(itemDetail.getAmount() + 1);
            cartDetailRepository.save(itemDetail);
            return itemDetail;
        }
    }

    @PostMapping("carts/{user_id}/detail/{id}")
    public CartDetail updateAmount(@PathVariable int user_id, @PathVariable int id, @RequestBody CartDetail cartDetail) {
        CartDetail findDetail = this.cartDetailRepository.findById(id).get();

        findDetail.setAmount(cartDetail.getAmount());
        this.cartDetailRepository.save(findDetail);
        return findDetail;
    }

    @DeleteMapping("carts/{user_id}/detail/{id}")
    public String deleteCartDetail(@PathVariable int user_id, @PathVariable int id) {
        CartDetail findDetail = this.cartDetailRepository.findById(id).get();
        this.cartDetailRepository.delete(findDetail);

        return "Delete success";
    }
}
