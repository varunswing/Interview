package Interview.SystemDesign.LLD.OfferManagementSystem.Models;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private String orderId;
    private String userId;
    private List<OrderItem> orderItems;
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime orderTime;
    private Payment payment;
    
    // Constructors, Getters, and Setters
}

public class OrderItem {
    private String productId;
    private int quantity;
    private double price;
    
    // Constructors, Getters, and Setters
}

public enum OrderStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    COMPLETED
}

