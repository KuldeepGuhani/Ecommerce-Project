package com.kd.ecommerce.Controller;


import com.kd.ecommerce.model.CartItem;
import com.kd.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("http://localhost:4200") // Allows frontend access
public class CartController {
    
    //✅ Fetch all cart items (GET /api/cart).
    //✅ Add item to cart (POST /api/cart/add).
    //✅ Remove an item (DELETE /api/cart/remove/{cartItemId}).
    //✅ Clear entire cart (DELETE /api/cart/clear).
    
    @Autowired
    private CartService cartService;

    // Get user's cart items
    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems(Authentication authentication) {
        return ResponseEntity.ok(cartService.getCartItems(authentication.getName()));
    }

    // Add item to cart
    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestParam Long productId, @RequestParam int quantity, Authentication authentication) {
        return ResponseEntity.ok(cartService.addToCart(authentication.getName(), productId, quantity));
    }

    // Remove an item from cart
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartItemId, Authentication authentication) {
        //System.out.println("USER NAME :::::: "+authentication.getName()+" :    CART ITEM ID :::::::::::::::::::::::: "+cartItemId);
        cartService.removeFromCart(authentication.getName(), cartItemId);
        return ResponseEntity.ok("Item removed successfully.");
    }

    // Clear cart
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(Authentication authentication) {
        cartService.clearCart(authentication.getName());
        return ResponseEntity.ok("Cart cleared successfully.");
    }
    
    // Update product quantity in the cart
    @PutMapping("/update")
    public ResponseEntity<CartItem> updateItemQuantity(@RequestParam Long userId,
                                                           @RequestParam Long cartItemId,
                                                           @RequestParam int quantity) {
        CartItem updatedCart = cartService.updateItemQuantity(userId, cartItemId, quantity);
        return ResponseEntity.ok(updatedCart);
    }
    
}
