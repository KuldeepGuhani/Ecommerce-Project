package com.kd.ecommerce.model;

//    Now, we will implement the Order Management System, where users can:
//✅ Place an order (checkout the cart).
//✅ Store orders in the database (with status: Pending, Shipped, Delivered, Canceled).
//✅ Track order history (users can view their past orders).
    


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

//    ✅ Each order belongs to a user.
//✅ An order can have multiple items.
//✅ Total price and status fields are included.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Each order belongs to a user

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<OrderItem> orderItems; // List of products in the order

    private double totalPrice;
    private String status; // Pending, Shipped, Delivered, Canceled
    private String paymentMethod;
    private String shippingAddress;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    

  

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    public Order() {
        this.orderDate = new Date();
    }

    public Order(User user, List<OrderItem> orderItems, double totalPrice, String status,String paymentMethod,String shippingAddress) {
        this.user = user;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.status = status;
        this.paymentMethod  = paymentMethod;
        this.shippingAddress  = shippingAddress;
        this.orderDate = new Date();
    }
    
     public Order(User user, List<OrderItem> orderItems, double totalPrice, String status) {
        this.user = user;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDate = new Date();
    }

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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", user=" + user + ", orderItems=" + orderItems + ", totalPrice=" + totalPrice + ", status=" + status + ", paymentMethod=" + paymentMethod + ", shippingAddress=" + shippingAddress + ", orderDate=" + orderDate + '}';
    }
    
    
}
