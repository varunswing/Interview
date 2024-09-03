package Interview.SystemDesign.LLD.OfferManagementSystem.Services;

public class InventoryService {
    
    public boolean checkAndReserveInventory(String productId, int quantity) {
        // Check inventory and reserve stock if available
    }
    
    public void releaseInventory(String productId, int quantity) {
        // Release reserved inventory
    }
    
    public void deductInventory(String productId, int quantity) {
        // Deduct inventory after successful order
    }
}

