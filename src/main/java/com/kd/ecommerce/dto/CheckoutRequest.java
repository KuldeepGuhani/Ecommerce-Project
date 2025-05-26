/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kd.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutRequest {
    private Long userId;
    private String shippingAddress;
    private String paymentMethod;  // "PayPal"
}
