package com.binus.finalproject.order;

import com.binus.finalproject.cart.Cart;
import com.binus.finalproject.cart.CartDetail;
import com.binus.finalproject.cart.CartDetailRepository;
import com.binus.finalproject.cart.CartRepository;
import com.binus.finalproject.order.request.CheckoutRequest;
import com.binus.finalproject.product.Product;
import com.binus.finalproject.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("order/checkout")
    public void checkout(@RequestBody CheckoutRequest checkout) {
        Cart userCart = this.cartRepository.findByUserId(checkout.getUserId());

        // Today's date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        // Create new order
        Order order = new Order();
        order.setUser_id(checkout.getUserId());
        order.setDate(dateFormat.format(date));
        order.setStatus("payment");
        order = this.orderRepository.save(order);

        double totalPrice, productPrice;
        totalPrice = productPrice = 0;
        Product product;
        Iterable<CartDetail> cartDetails = this.cartDetailRepository.findByCartId(userCart.getId());
        for(CartDetail data: cartDetails) {
            product = this.productRepository.findById(data.getProductId()).get();

            productPrice = product.getPrice();
            totalPrice += productPrice;

            // Create new order detail
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setProductId(product.getId());
            orderDetail.setAmount(data.getAmount());
            orderDetail.setProductPrice(productPrice);

            this.orderDetailRepository.save(orderDetail);
            this.cartDetailRepository.delete(data);
        }

        order.setTotal_price(totalPrice);
        this.orderRepository.save(order);
    }
}
