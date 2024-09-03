package Interview.SystemDesign.LLD.OfferManagementSystem.Models;

public class Inventory {
    private String inventoryId;
    private String productId;
    private int totalQuantity;
    private int reservedQuantity;
    
    // Constructors, Getters, and Setters
    
    public synchronized boolean reserveStock(int quantity) {
        if (totalQuantity - reservedQuantity >= quantity) {
            reservedQuantity += quantity;
            return true;
        }
        return false;
    }
    
    public synchronized void releaseStock(int quantity) {
        reservedQuantity -= quantity;
    }
    
    public synchronized void deductStock(int quantity) {
        totalQuantity -= quantity;
        reservedQuantity -= quantity;
    }
}

