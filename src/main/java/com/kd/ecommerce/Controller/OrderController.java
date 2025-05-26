package com.kd.ecommerce.Controller;


import com.kd.ecommerce.model.Order;
import com.kd.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*") // Allows frontend access
public class OrderController {
    
//    ✅ Checkout (POST /api/orders/place).
//✅ Get user orders (GET /api/orders).

    @Autowired
    private OrderService orderService;

    // Place an order (Checkout)
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(Authentication authentication) {
        return ResponseEntity.ok(orderService.placeOrder(authentication.getName()));
    }

    // Get all orders for the logged-in user
    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders(Authentication authentication) {
        return ResponseEntity.ok(orderService.getUserOrders(authentication.getName()));
    }
}
