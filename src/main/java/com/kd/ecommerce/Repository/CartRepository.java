package com.kd.ecommerce.Repository;


import com.kd.ecommerce.model.CartItem;
import com.kd.ecommerce.model.Product;
import com.kd.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    
//    ✅ Fetch cart items for a user.
//✅ Find a specific cart item for a user.
//✅ Delete all items when checkout happens.

    List<CartItem> findByUser(User user); // Get all cart items for a user
    CartItem findByUserId(Long userId);  // Get cart by user ID

    Optional<CartItem> findByUserAndProduct(User user, Product product); // Find a cart item for a specific user and product

    void deleteByUser(User user); // Delete all cart items for a user
}
