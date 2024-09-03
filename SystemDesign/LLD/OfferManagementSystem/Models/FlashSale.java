package Interview.SystemDesign.LLD.OfferManagementSystem.Models;

import java.time.LocalDateTime;

public class FlashSale {
    private String flashSaleId;
    private String productId;
    private double discountedPrice;
    private int availableQuantity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    // Constructors, Getters, and Setters
    
    public boolean isSaleActive() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(startTime) && now.isBefore(endTime);
    }
    
    public synchronized boolean reserveSaleStock(int quantity) {
        if (availableQuantity >= quantity) {
            availableQuantity -= quantity;
            return true;
        }
        return false;
    }
}

