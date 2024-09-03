package Interview.SystemDesign.LLD.OfferManagementSystem.Models;

import java.time.LocalDateTime;

public class Payment {
    private String paymentId;
    private String orderId;
    private PaymentMethod method;
    private PaymentStatus status;
    private double amount;
    private LocalDateTime paymentTime;
    private String transactionId;
    
    // Constructors, Getters, and Setters
}

public enum PaymentMethod {
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    NET_BANKING,
    UPI
}

public enum PaymentStatus {
    PENDING,
    SUCCESS,
    FAILED,
    CANCELLED
}

