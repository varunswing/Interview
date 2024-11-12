[Detailed](https://github.com/keertipurswani/Uber-Ola-Low-Level-Design)

![Alt Tex](images/uber_lld.png)

1. Trip Manager
    i. Trip
    ii. TripMetaData
    iii. RiderManager
        a. RiderDetails
    iv. DriverManager
        a. DriverDetails
    v. StrategyManager
        a. PricingStrategy (Default, RatingBased)
        b. DriverMatchingStrategy (LeastTimeBased, RatingBased)


### 1. **Functional Requirements**

- **User Management**: 
  - Allow users to sign up, log in, and manage profiles.
- **Driver Management**:
  - Drivers can sign up, set availability, and manage profiles.
- **Booking System**:
  - Allow customers to book rides.
  - Match customers with available drivers.
  - Provide trip details, including estimated fare and ETA.
- **Ride Management**:
  - Track ride status (e.g., requested, in-progress, completed).
  - Support GPS tracking for both the driver and rider.
- **Payment Processing**:
  - Allow payment through multiple options (wallets, cards, cash).
- **Notification Service**:
  - Notify users and drivers about ride statuses, offers, etc.
- **Rating and Feedback**:
  - Riders and drivers can rate each other and provide feedback.

### 2. **Non-Functional Requirements**

- **Scalability**: The system should handle a high volume of users and transactions.
- **Availability**: High uptime is essential to support real-time ride booking.
- **Consistency**: The booking status should remain consistent across the system.
- **Latency**: Low latency is required for real-time booking and tracking.
- **Security**: Secure user data and payment information.
- **Reliability**: The system should handle failures gracefully.

---

### 3. **System Components**

#### a. **APIs**

1. **User Management APIs**
   - `POST /api/v1/user/signup`: Register a new user.
   - `POST /api/v1/user/login`: Log in an existing user.
   - `GET /api/v1/user/profile`: Retrieve user profile information.
   - `PUT /api/v1/user/profile`: Update user profile information.

2. **Driver Management APIs**
   - `POST /api/v1/driver/signup`: Register a new driver.
   - `POST /api/v1/driver/login`: Log in an existing driver.
   - `PUT /api/v1/driver/status`: Update driver availability status.

3. **Booking APIs**
   - `POST /api/v1/ride/request`: Request a ride with pick-up and drop-off details.
   - `GET /api/v1/ride/estimate`: Get fare and ETA estimates.
   - `GET /api/v1/ride/{ride_id}/status`: Get the current status of a ride.
   - `PUT /api/v1/ride/{ride_id}/cancel`: Cancel a ride.

4. **Ride Management APIs**
   - `PUT /api/v1/ride/{ride_id}/start`: Start the ride (driver action).
   - `PUT /api/v1/ride/{ride_id}/end`: End the ride (driver action).

5. **Payment APIs**
   - `POST /api/v1/payment/initiate`: Initiate a payment for the completed ride.
   - `POST /api/v1/payment/confirm`: Confirm payment after processing.

6. **Rating and Feedback APIs**
   - `POST /api/v1/ride/{ride_id}/rating`: Submit a rating and feedback for the driver or rider.

7. **Notification APIs**
   - `POST /api/v1/notification/send`: Send notifications to users or drivers.

---

#### b. **Services**

1. **User Service**: Manages user sign-up, login, and profile details.
2. **Driver Service**: Manages driver sign-up, login, profile, and availability status.
3. **Booking Service**: Handles ride requests, matches drivers, and manages booking lifecycle.
4. **Ride Service**: Manages ride status updates (e.g., start, end) and GPS tracking.
5. **Payment Service**: Processes payments and manages billing.
6. **Notification Service**: Sends real-time notifications to users and drivers.
7. **Rating and Feedback Service**: Manages rating and feedback system.

---

### 4. **Database Schema**

Here’s a simplified schema design:

#### a. **Users Table**

| Column       | Type         | Description                   |
|--------------|--------------|-------------------------------|
| user_id      | INT (PK)     | Unique user ID               |
| name         | VARCHAR      | User's full name             |
| email        | VARCHAR      | User's email                 |
| password     | VARCHAR      | User's encrypted password    |
| phone        | VARCHAR      | User's phone number          |
| rating       | FLOAT        | Average rating as a rider    |

#### b. **Drivers Table**

| Column       | Type         | Description                   |
|--------------|--------------|-------------------------------|
| driver_id    | INT (PK)     | Unique driver ID             |
| name         | VARCHAR      | Driver's full name           |
| email        | VARCHAR      | Driver's email               |
| phone        | VARCHAR      | Driver's phone number        |
| license      | VARCHAR      | Driver’s license number      |
| vehicle_id   | INT (FK)     | Linked to vehicle            |
| is_available | BOOLEAN      | Availability status          |
| rating       | FLOAT        | Average rating as a driver   |

#### c. **Rides Table**

| Column       | Type         | Description                   |
|--------------|--------------|-------------------------------|
| ride_id      | INT (PK)     | Unique ride ID               |
| user_id      | INT (FK)     | User who requested the ride  |
| driver_id    | INT (FK)     | Driver assigned to the ride  |
| pickup_lat   | FLOAT        | Pick-up location latitude    |
| pickup_long  | FLOAT        | Pick-up location longitude   |
| dropoff_lat  | FLOAT        | Drop-off location latitude   |
| dropoff_long | FLOAT        | Drop-off location longitude  |
| fare         | DECIMAL      | Fare for the ride            |
| status       | ENUM         | Ride status (e.g., requested, in-progress, completed, canceled) |
| start_time   | TIMESTAMP    | Ride start time              |
| end_time     | TIMESTAMP    | Ride end time                |

#### d. **Vehicles Table**

| Column       | Type         | Description                   |
|--------------|--------------|-------------------------------|
| vehicle_id   | INT (PK)     | Unique vehicle ID            |
| driver_id    | INT (FK)     | Owner of the vehicle         |
| model        | VARCHAR      | Vehicle model                |
| license_plate| VARCHAR      | License plate number         |
| color        | VARCHAR      | Vehicle color                |

#### e. **Payments Table**

| Column       | Type         | Description                   |
|--------------|--------------|-------------------------------|
| payment_id   | INT (PK)     | Unique payment ID            |
| ride_id      | INT (FK)     | Linked to the ride           |
| amount       | DECIMAL      | Payment amount               |
| status       | ENUM         | Payment status (e.g., initiated, confirmed) |
| method       | ENUM         | Payment method               |

---

### 5. **System Diagram**

The **system architecture** typically includes:

- **Client Apps**: Mobile applications for riders and drivers.
- **API Gateway**: Routes requests to the appropriate services.
- **Microservices**: Services (User, Driver, Ride, Payment, Notification, Rating) communicate with each other and the database.
- **Data Storage**: MySQL for structured data, Redis for caching, and Kafka for handling real-time data streams.
- **Geo-Services**: External APIs (e.g., Google Maps) for routing and distance calculation.
- **Monitoring and Logging**: Grafana and Kibana for monitoring and debugging.

---

### 6. **Non-Functional Instruments**

- **Load Balancers**: Distribute traffic among instances.
- **Caching**: Use Redis to cache frequently accessed data, like driver locations.
- **Data Partitioning**: Partition user and ride data to improve scalability.
- **Database Replication**: Ensure high availability with read replicas.
- **CI/CD Pipeline**: Automate deployment and testing to ensure reliability.
- **Monitoring and Alerting**: Use tools like Grafana and Kibana for performance monitoring and alerts.
- **Rate Limiting**: Prevent abuse of the system by limiting the rate of requests from clients.

This LLD captures the core functionalities and requirements of a scalable and efficient ride-hailing system like Uber. Let me know if you need deeper insights into any specific section!


Given the scope of Uber’s system, providing fully fleshed-out code for all APIs and services here would be extensive. Instead, I’ll outline some representative code snippets, particularly focusing on the structure and key components for each service. I’ll implement examples using Java with Spring Boot, which is a popular choice for building microservices in production.

For each service, I’ll cover these essentials:

1. A simplified Controller class to expose REST APIs.
2. A Service class to handle business logic.
3. A basic Repository interface to interact with the database (assuming we’re using JPA).
4. Entity classes to define database schemas.

### Prerequisites

To begin, set up a **Spring Boot** project for each service or as a single monolithic project (if you're not separating them into independent microservices). Include dependencies like `spring-boot-starter-web`, `spring-boot-starter-data-jpa`, `spring-boot-starter-security` (for user authentication), and `spring-boot-starter-validation` (for request validation).

---

### 1. **User Management Service**

#### User Entity

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String password;
    private String phone;
    private Float rating;

    // Getters and Setters
}
```

#### User Repository

```java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
```

#### User Service

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
```

#### User Controller

```java
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return userService.getUserById(userId)
            .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
```

---

### 2. **Driver Management Service**

#### Driver Entity

```java
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private String name;
    private String email;
    private String phone;
    private String license;
    private Boolean isAvailable;

    // Getters and Setters
}
```

#### Driver Repository

```java
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
```

#### Driver Service

```java
@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public Driver registerDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Optional<Driver> getDriverById(Long driverId) {
        return driverRepository.findById(driverId);
    }

    public void updateAvailability(Long driverId, boolean isAvailable) {
        driverRepository.findById(driverId).ifPresent(driver -> {
            driver.setIsAvailable(isAvailable);
            driverRepository.save(driver);
        });
    }
}
```

#### Driver Controller

```java
@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/signup")
    public ResponseEntity<Driver> signUp(@RequestBody Driver driver) {
        Driver registeredDriver = driverService.registerDriver(driver);
        return new ResponseEntity<>(registeredDriver, HttpStatus.CREATED);
    }

    @PutMapping("/{driverId}/status")
    public ResponseEntity<Void> updateAvailability(@PathVariable Long driverId, @RequestParam boolean isAvailable) {
        driverService.updateAvailability(driverId, isAvailable);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
```

---

### 3. **Ride Management Service**

#### Ride Entity

```java
@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rideId;

    private Long userId;
    private Long driverId;
    private String pickupLocation;
    private String dropoffLocation;
    private BigDecimal fare;
    private String status;  // "requested", "in-progress", "completed"

    // Getters and Setters
}
```

#### Ride Repository

```java
public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByUserId(Long userId);
}
```

#### Ride Service

```java
@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;

    public Ride requestRide(Ride ride) {
        ride.setStatus("requested");
        return rideRepository.save(ride);
    }

    public Ride startRide(Long rideId) {
        return updateRideStatus(rideId, "in-progress");
    }

    public Ride endRide(Long rideId) {
        return updateRideStatus(rideId, "completed");
    }

    private Ride updateRideStatus(Long rideId, String status) {
        return rideRepository.findById(rideId).map(ride -> {
            ride.setStatus(status);
            return rideRepository.save(ride);
        }).orElseThrow(() -> new RuntimeException("Ride not found"));
    }
}
```

#### Ride Controller

```java
@RestController
@RequestMapping("/api/v1/ride")
public class RideController {
    @Autowired
    private RideService rideService;

    @PostMapping("/request")
    public ResponseEntity<Ride> requestRide(@RequestBody Ride ride) {
        Ride requestedRide = rideService.requestRide(ride);
        return new ResponseEntity<>(requestedRide, HttpStatus.CREATED);
    }

    @PutMapping("/{rideId}/start")
    public ResponseEntity<Ride> startRide(@PathVariable Long rideId) {
        Ride startedRide = rideService.startRide(rideId);
        return new ResponseEntity<>(startedRide, HttpStatus.OK);
    }

    @PutMapping("/{rideId}/end")
    public ResponseEntity<Ride> endRide(@PathVariable Long rideId) {
        Ride completedRide = rideService.endRide(rideId);
        return new ResponseEntity<>(completedRide, HttpStatus.OK);
    }
}
```

---

### 4. **Payment Service**

This service would follow a similar structure to handle the processing of payments. Here’s a brief example:

#### Payment Entity

```java
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long rideId;
    private BigDecimal amount;
    private String status;  // "initiated", "confirmed"

    // Getters and Setters
}
```

#### Payment Controller

```java
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<Payment> initiatePayment(@RequestBody Payment payment) {
        Payment initiatedPayment = paymentService.initiatePayment(payment);
        return new ResponseEntity<>(initiatedPayment, HttpStatus.CREATED);
    }

    @PostMapping("/{paymentId}/confirm")
    public ResponseEntity<Payment> confirmPayment(@PathVariable Long paymentId) {
        Payment confirmedPayment = paymentService.confirmPayment(paymentId);
        return new ResponseEntity<>(confirmedPayment, HttpStatus.OK);
    }
}
```

Each of these service classes would include more complex logic and validations in a real application, such as handling driver-user matching, GPS tracking, security features, and error handling. This setup provides a foundational code structure for building out each microservice component in a larger, scalable system like Uber.