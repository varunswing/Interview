package Interview.SystemDesign.LLD.OfferManagementSystem.Services;

import Interview.SystemDesign.LLD.OfferManagementSystem.Models.User;

public class UserService {
    
    public User registerUser(String name, String email, String password, String address, String phoneNumber) {
        // Logic to register user and save to database
    }
    
    public User authenticateUser(String email, String password) {
        // Logic to authenticate user credentials
    }
    
    public User getUserDetails(String userId) {
        // Fetch user details from database
    }
}

