package com.kd.ecommerce.config;


import com.kd.ecommerce.Repository.UserRepository;
import com.kd.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);
//        if (user.equals("")) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        User loadedUser = user.get();
//        System.out.println("load user "+loadedUser);
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(loadedUser.getUsername())
//                .password(loadedUser.getPassword())
//                .roles(loadedUser.getRoles().toArray(new String[0]))
//                .build();
//    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<User> user = userRepository.findByUsername(username);
        if (user != null) {
            User loadedUser = user.get();
            System.out.println("ROLE :::::::::::::::::::::::::::: "+loadedUser.getRoles().toArray(new String[0]).toString()+" ROLE 2::::::::::::::::::: "+loadedUser.getRoles());
            return org.springframework.security.core.userdetails.User.builder()
                    .username(loadedUser.getUsername())
                    .password(loadedUser.getPassword())
                    .roles(loadedUser.getRoles().toArray(new String[0])) ///roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
    
}
