package com.kd.ecommerce.model;


import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

//✅ Each cart item is linked to a user and product.
//✅ Quantity field stores the number of items added.
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Each cart item belongs to a user
//    
//    @OneToMany(mappedBy = "cart_items", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<CartItem> items;  // A list of cart items

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // The product in the cart

    private int quantity;

    public CartItem() {
    }

    public CartItem(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

//    public List<CartItem> getCartItems() {
//        return items;
//    }
//    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
