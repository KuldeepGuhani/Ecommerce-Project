/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kd.ecommerce.service;

import com.kd.ecommerce.Repository.UserRepository;
import com.kd.ecommerce.config.JwtUtil;
import com.kd.ecommerce.dto.AuthRequest;
import com.kd.ecommerce.dto.UserDto;
import com.kd.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String registerUser(UserDto userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.getRoles().add("USER");

        userRepository.save(user);
        return "User registered successfully!";
    }

    public String login(AuthRequest authRequest) {
        System.out.println("USER NAME :::: "+authRequest.getUsername());
        System.out.println("PASSWORD :::: "+authRequest.getPassword());
      try{
          authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
      }catch(Exception e){
          e.printStackTrace();
      }

        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        return jwtUtil.generateToken(user.getUsername());
    }
}
