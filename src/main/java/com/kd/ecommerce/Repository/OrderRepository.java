package com.kd.ecommerce.Repository;


import com.kd.ecommerce.model.Order;
import com.kd.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//✅ Fetch orders for a specific user.

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user); // Get orders by user
    List<Order> findByUserId(Long userId);
}
