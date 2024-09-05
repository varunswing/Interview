## Problem Statement
    ```design ola uber
	requirements

	Users can book rides.
	Users can see ride history on user's side.
	Users can pay for rides.
	Driver can accept rides.
	Driver can see ride history that they accepted or rejected.
    Defined schema, Databases, caching on location, normalization of SQL table of rides.
    ```

Ola/Uber App Design
In this design, the system allows users to book rides, view their ride history, make payments, and provides drivers with the ability to accept/reject rides and see their history. The architecture includes service components, schema, and caching mechanisms to handle real-time ride management efficiently.

## 1. Core Requirements

**Users (Passengers)**
1. Users can book rides based on their current location and desired destination.
2. Users can see their ride history.
3. Users can pay for rides using multiple payment methods.

**Drivers**
1. Drivers can accept or reject ride requests.
2. Drivers can see the ride history for the rides they accepted or rejected.

**System**
1. The system must match riders with nearby drivers in real time.
2. Implement caching for location data to efficiently find the nearest driver.
3. Rides data should be normalized in SQL.

## 2. Core Entities

1. User (Passenger): Represents the person booking the ride.
2. Driver: Represents the person driving the vehicle.
3. Ride: Represents the trip booked by the user and driven by the driver.
4. Payment: Represents payment details for the ride.
5. Vehicle: Represents the vehicle driven by the driver.

## 3. Service Components

1. UserService: Manages user authentication, profile, ride booking, and ride history.
2. DriverService: Manages driver profile, ride requests (accept/reject), and ride history.
3. RideService: Manages real-time ride matching, ride status (accepted, completed, etc.), and ride details.
4. PaymentService: Handles payment processing for rides.
5. LocationService: Manages real-time driver location tracking and caching.
6. NotificationService: Sends notifications to users and drivers regarding ride requests, status updates, etc.

## 4. Database Schema Design

### 4.1. User Table

|Column        |	Type   |	Description                     |
|--------------|-----------|------------------------------------|
|user_id|	VARCHAR	|Primary key, unique ID for user
|name	|VARCHAR|	User's full name
|email|	VARCHAR	|User's email
|password|	VARCHAR	|Hashed password
|phone_number|	VARCHAR|	User's phone number

### 4.2. Driver Table
|Column        |	Type   |	Description                     |
|--------------|-----------|------------------------------------|
|driver_id|	VARCHAR	|Primary key, unique ID for driver
|name|	VARCHAR	|Driver's full name
|email|	VARCHAR	|Driver's email
|password|	VARCHAR|	Hashed password
|phone_number	|VARCHAR|	Driver's phone number
|vehicle_id|	VARCHAR	|Foreign key to Vehicle table
|is_available|	BOOLEAN	|Availability status of the driver

### 4.3. Vehicle Table
|Column        |	Type   |	Description                     |
|--------------|-----------|------------------------------------|
|vehicle_id	|VARCHAR	|Primary key, unique ID for vehicle
|license_plate|	VARCHAR	|Vehicle's license plate
|make|	VARCHAR	|Vehicle make (e.g., Toyota)
|model|	VARCHAR|	Vehicle model (e.g., Camry)
|driver_id	|VARCHAR	|Foreign key to Driver table

### 4.4. Ride Table
|Column        |	Type   |	Description                     |
|--------------|-----------|------------------------------------|
|ride_id|	VARCHAR|	Primary key, unique ID for ride
|user_id	|VARCHAR|	Foreign key to User table
|driver_id	|VARCHAR	|Foreign key to Driver table
|pickup_location|	VARCHAR	|Pickup location (latitude, longitude)
|drop_location|	VARCHAR|	Drop location (latitude, longitude)
|fare_amount|	DECIMAL	|Fare charged for the ride
|status|	ENUM|	Status of the ride (Accepted, Rejected, Completed, Cancelled)
|request_time|	TIMESTAMP|	Time when ride was requested
|start_time	|TIMESTAMP|	Time when ride started
|end_time|	TIMESTAMP|	Time when ride ended

### 4.5. Payment Table
|Column        |	Type   |	Description                     |
|--------------|-----------|------------------------------------|
|payment_id	|VARCHAR|	Primary key, unique ID for payment
|ride_id|	VARCHAR|	Foreign key to Ride table
|user_id|	VARCHAR	|Foreign key to User table
|amount|	DECIMAL	|Amount paid
|payment_method|	ENUM|	Payment method (Credit Card, PayPal, etc.)
|payment_time|	TIMESTAMP|	Time when payment was made

## 5. Database Normalization

### Normalization of Ride Table
* 1NF (First Normal Form): Each column should have atomic values.
    * For the Ride table, each ride record should store only one pickup location, one drop location, one status, etc. No repeating groups.
* 2NF (Second Normal Form): All non-key attributes must be fully functional dependent on the primary key.
    * fare_amount, status, request_time, start_time, and end_time all depend on ride_id, so the table complies with 2NF.
* 3NF (Third Normal Form): No transitive dependencies (i.e., non-key attributes should not depend on other non-key attributes).
    * No transitive dependency exists in the Ride table as attributes like pickup_location, drop_location, etc., depend only on the primary key ride_id.
This normalized design ensures that rides are stored efficiently without redundancy or unnecessary data duplication.

## 6. Caching on Location
Use Case for Caching:
The system frequently looks up driver locations to match the nearest available driver to a user’s ride request. Caching can be used to minimize database calls and reduce latency.
Cache Strategy:
Driver Location Cache: Cache the real-time locations of all available drivers using a distributed caching system like Redis. This cache will store each driver's location (latitude, longitude) and their availability status.
Key: driver_id
Value: Location (latitude, longitude), availability status
TTL (Time-to-Live): Set an expiry time for location data (e.g., 10 seconds) to ensure that the cache remains up-to-date.
Workflow with Caching:
When a user requests a ride:

The system queries the cache for available drivers within a certain radius of the user’s current location.
If an available driver is found in the cache, the system assigns the ride.
If no driver is found, the system queries the database (fallback) to check for available drivers.
When a driver changes their location or availability:

Update the cache with the new location and availability status.
## 7. Class Diagrams and Definitions
7.1. User
```java
public class User {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    // Constructors, Getters, and Setters
}
```

7.2. Driver
```java
public class Driver {
    private String driverId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String vehicleId;
    private boolean isAvailable;
    
    // Constructors, Getters, and Setters
}
```

7.3. Ride
```java
import java.time.LocalDateTime;

public class Ride {
    private String rideId;
    private String userId;
    private String driverId;
    private String pickupLocation;
    private String dropLocation;
    private BigDecimal fareAmount;
    private RideStatus status;
    private LocalDateTime requestTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Constructors, Getters, and Setters
}

public enum RideStatus {
    REQUESTED,
    ACCEPTED,
    REJECTED,
    COMPLETED,
    CANCELLED
}
```

7.4. Payment
```java
import java.time.LocalDateTime;

public class Payment {
    private String paymentId;
    private String rideId;
    private String userId;
    private BigDecimal amount;
    private PaymentMethod method;
    private LocalDateTime paymentTime;

    // Constructors, Getters, and Setters
}

public enum PaymentMethod {
    CREDIT_CARD,
    PAYPAL,
    CASH
}
```

## 8. User Flow

8.1. User Booking a Ride

1. User requests a ride by specifying the pickup and drop locations.
2. The system checks the cache for nearby available drivers.
3. A driver is assigned and notified.
4. User receives ride confirmation and real-time tracking.
5. After the ride, the user pays via the app or cash.

8.2. Driver Flow

1. The driver receives a ride request notification.
2. The driver can accept or reject the ride.
3. Once accepted, the ride details are updated in the system.
4. The driver completes the ride and marks it as "Completed."

8.3. Ride History

1. Both users and drivers can view their past ride history, showing accepted/completed rides.

## 9. Conclusion
This design outlines a scalable and real-time ride-sharing platform with features for booking, payment, and ride history management. With proper normalization, caching for location data, and a clear service architecture, the system can efficiently handle high-volume user and driver interactions.