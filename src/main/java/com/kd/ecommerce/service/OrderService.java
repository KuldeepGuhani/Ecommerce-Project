package com.kd.ecommerce.service;


import com.kd.ecommerce.Repository.CartRepository;
import com.kd.ecommerce.Repository.OrderRepository;
import com.kd.ecommerce.Repository.UserRepository;
import com.kd.ecommerce.model.CartItem;
import com.kd.ecommerce.model.Order;
import com.kd.ecommerce.model.OrderItem;
import com.kd.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    
//    ✅ Fetch user's cart, convert items into an order, and store it.
//✅ Clear cart after placing an order.

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Order placeOrder(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty. Add products before placing an order.");
        }

        List<OrderItem> orderItems = cartItems.stream().map(cartItem ->
                new OrderItem(null, cartItem.getProduct(), cartItem.getQuantity(), cartItem.getProduct().getPrice() * cartItem.getQuantity())
        ).collect(Collectors.toList());

        double totalPrice = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();

        Order order = new Order(user, orderItems, totalPrice, "Pending");
        orderItems.forEach(item -> item.setOrder(order));

        Order savedOrder = orderRepository.save(order);
        cartRepository.deleteByUser(user);

        return savedOrder;
    }

    public List<Order> getUserOrders(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUser(user);
    }
}
