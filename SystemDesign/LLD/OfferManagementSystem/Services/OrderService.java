package Interview.SystemDesign.LLD.OfferManagementSystem.Services;

public class OrderService {
    
    public Order createOrder(String userId, List<OrderItem> items) {
        // Validate cart items, reserve inventory, and create order
    }
    
    public Order getOrderById(String orderId) {
        // Retrieve order details
    }
    
    public void updateOrderStatus(String orderId, OrderStatus status) {
        // Update the status of an order
    }
}

