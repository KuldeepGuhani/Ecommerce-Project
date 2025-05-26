/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kd.ecommerce.Controller;

import com.kd.ecommerce.dto.AuthRequest;
import com.kd.ecommerce.dto.UserDto;
import com.kd.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDTO) {
        return authService.registerUser(userDTO);
    }
    
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }
}
