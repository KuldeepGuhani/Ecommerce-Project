package com.kd.ecommerce.Repository;




import com.kd.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Search products by name (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Get products by category
    List<Product> findByCategory(String category);
}
