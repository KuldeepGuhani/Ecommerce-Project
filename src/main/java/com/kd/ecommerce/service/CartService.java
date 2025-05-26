package com.kd.ecommerce.service;


import com.kd.ecommerce.Repository.CartRepository;
import com.kd.ecommerce.Repository.ProductRepository;
import com.kd.ecommerce.Repository.UserRepository;
import com.kd.ecommerce.model.CartItem;
import com.kd.ecommerce.model.Product;
import com.kd.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    
//    ✅ Fetch all cart items for a user.
//✅ Add items to the cart (increase quantity if item exists).
//✅ Remove an item from the cart.
//✅ Clear the cart after checkout.

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CartItem> getCartItems(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user);
    }

    public CartItem addToCart(String username, Long productId, int quantity) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingCartItem = cartRepository.findByUserAndProduct(user, product);

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            return cartRepository.save(cartItem);
        } else {
            CartItem newCartItem = new CartItem(user, product, quantity);
            return cartRepository.save(newCartItem);
        }
    }

    public void removeFromCart(String username, Long cartItemId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItem cartItem = cartRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        System.out.println("CART ITEM :"+cartItem);
        if (!cartItem.getUser().equals(user)) {
            throw new RuntimeException("Unauthorized access");
        }

        cartRepository.delete(cartItem);
    }

    public void clearCart(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        cartRepository.deleteByUser(user);
    }
    
    
public CartItem updateItemQuantity(Long userId, Long cartItemId, int quantity) {
        
 CartItem cartItem = cartRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
    System.out.println("CART ::::::\n\n"+cartItem);
    if (cartItem == null) {
        throw new RuntimeException("Cart item not found");
    }
        cartItem.setQuantity(quantity);
        cartRepository.save(cartItem);
        return cartItem;
    }
     
     
     
}
