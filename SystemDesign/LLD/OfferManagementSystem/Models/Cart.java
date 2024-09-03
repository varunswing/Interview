package Interview.SystemDesign.LLD.OfferManagementSystem.Models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private String cartId;
    private String userId;
    private Map<String, Integer> products; // productId -> quantity
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    
    // Constructors, Getters, and Setters
    
    public void addProduct(String productId, int quantity) {
        products.put(productId, products.getOrDefault(productId, 0) + quantity);
    }
    
    public void removeProduct(String productId) {
        products.remove(productId);
    }
    
    public void clearCart() {
        products.clear();
    }
}

