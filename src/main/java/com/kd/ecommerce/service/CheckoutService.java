package com.kd.ecommerce.service;



import com.kd.ecommerce.Repository.CartRepository;
import com.kd.ecommerce.Repository.OrderRepository;
import com.kd.ecommerce.Repository.UserRepository;
import com.kd.ecommerce.dto.CheckoutRequest;
import com.kd.ecommerce.model.CartItem;
import com.kd.ecommerce.model.Order;
import com.kd.ecommerce.model.OrderItem;
import com.kd.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckoutService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;


    public Order checkout(CheckoutRequest request) {
        CartItem cart = cartRepository.findByUserId(request.getUserId());
        if (cart == null) {
            throw new RuntimeException("Shopping cart is empty!");
        }
        
        // Create a new order
        Order order = new Order();
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        order.setUser(user);
        order.setShippingAddress(request.getShippingAddress());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setStatus("Pending");
        // Convert cart items to order items
//        List<OrderItem> orderItems = cart.getCartItems().stream().map(cartItem -> {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setProduct(cartItem.getProduct());
//            orderItem.setQuantity(cartItem.getQuantity());
//            orderItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
//            return orderItem;
//        }).collect(Collectors.toList());
//        
        
 
 
 
 
 
 
 
 
 

//        order.setOrderItems(orderItems);
//        order.setTotalPrice(orderItems.stream().mapToDouble(OrderItem::getPrice).sum());

        orderRepository.save(order);

        // Clear shopping cart after successful checkout
        cartRepository.delete(cart);

        return order;
    }
}

//Key Operations:
//    Retrieves the user's cart.
//    Converts cart items into order items.
//Saves the order with status "Pending".
//Deletes the cart after successful checkout