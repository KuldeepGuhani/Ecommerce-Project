/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kd.ecommerce.Controller;

import com.kd.ecommerce.dto.CheckoutRequest;
import com.kd.ecommerce.model.Order;
import com.kd.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin("*")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/process")
    public ResponseEntity<Order> checkout(@RequestBody CheckoutRequest request) {
        Order order = checkoutService.checkout(request);
        return ResponseEntity.ok(order);
    }
}
