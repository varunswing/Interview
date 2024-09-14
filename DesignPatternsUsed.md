# Design Patterns Used Till Date

## 1. Singleton Pattern


**Main Idea:** Ensures a class has only one instance and provides a global point of access to that instance.<br>
**Where Used:** Commonly used in resource management, such as database connections or service clients.<br>
**Example:**
Kafka Consumer: Maintaining a single instance of a Kafka consumer across multiple refund transactions to manage retries efficiently and prevent resource exhaustion.
Database Connection Pool: Ensuring a single instance of a database connection pool to handle multiple threads querying multiple banks during the refund system migration.<br>

## 2. Factory Pattern

**Main Idea:** Provides a way to create objects without specifying the exact class of the object that will be created.<br>
**Where Used:** Useful when the object creation logic is complex or dependent on runtime information.<br>
**Example:**
Payment Gateway Factory: Creating specific services for handling refunds, passbook management, or UPI based on payment type (credit, debit, UPI, etc.).
API Request Builder: Depending on the transaction type (refund, payment, passbook), different request objects were created dynamically by a factory to handle multiple downstream services.<br>

## 3. Strategy Pattern

**Main Idea:** Defines a family of algorithms, encapsulates each one, and makes them interchangeable.<br>
**Where Used:** Used when multiple methods can be applied to achieve the same goal, but you need to switch between them dynamically.<br>
**Example:**
Transaction Retry Strategies: Implemented different retry strategies in cron jobs for refund transactions depending on failure reasons (network issue vs service downtime).
Scaling Service: During UPI transaction lag issues, different scaling strategies could be applied dynamically to services (e.g., scale horizontally or upgrade resources).<br>

## 4. Observer Pattern

**Main Idea:** Allows an object (subject) to notify multiple observers about state changes automatically.<br>
**Where Used:** Commonly used in event-driven architectures or notification systems.<br>
**Example:**
Kafka Event Listeners: The refund system was integrated with Kafka, where downstream services (observers) subscribed to specific topics and acted on transaction status updates (events).
Real-time Monitoring: Grafana acted as an observer for system metrics (like Kafka consumer lag), sending alerts when thresholds were breached, allowing immediate action.<br>

## 5. Builder Pattern

**Main Idea:** Simplifies the construction of complex objects step by step, with flexibility over which parts to build.<br>
**Where Used:** Useful when dealing with complex object creation that involves multiple fields or options.<br>
**Example:**
API Response Construction: Building detailed refund or transaction responses that had multiple fields (e.g., transaction ID, status, amount, etc.), allowing the flexibility to include/exclude fields based on user context.
Database Queries: Constructing dynamic queries for transaction searches or sharding, depending on the bank or transaction type.<br>

## 6. Template Method Pattern

**Main Idea:** Defines the skeleton of an algorithm, deferring specific steps to subclasses.<br>
**Where Used:** Ensures consistent workflows with customizable steps.<br>
**Example:**
Refund Process Flow: A general refund process could be defined with steps like validation, transaction logging, and confirmation, but individual banks could override specific steps based on their requirements.
Batch Processing Cron Jobs: A template for handling cron job retries, where the core logic was the same, but specific retry mechanisms could differ based on the transaction type or the bank's protocol.<br>

## 7. Decorator Pattern

**Main Idea:** Adds responsibilities to an object dynamically without altering its structure.<br>
**Where Used:** Often used for enhancing behavior, such as logging, security, or validation, in a flexible and maintainable way.<br>
**Example:**
API Logging: You could decorate your REST API calls with additional logging or validation logic dynamically for debugging and performance metrics.
Transaction Status Enrichment: Adding additional metadata (such as timestamps, failure reasons) to transaction objects dynamically before sending them to downstream services.<br>

## 8. Proxy Pattern

**Main Idea:** Controls access to an object, adding a layer of indirection.<br>
**Where Used:** Used in distributed systems or for lazy loading resources.<br>
**Example:**
Downstream Service Callbacks: Using proxies when accessing downstream services (e.g., external banks or payment systems) in your passbook and payment system to handle retries, authentication, or fallback mechanisms.
Database Access: Implementing a proxy for database calls to manage sharding and partitioning logic dynamically based on the transaction or bank type.<br>

## 9. Adapter Pattern

**Main Idea:** Converts the interface of a class into another interface clients expect. It allows classes to work together that couldn't otherwise because of incompatible interfaces.<br>
**Where Used:** Commonly used when integrating with third-party services or legacy systems.<br>
**Example:**
Bank Integration: If you had to integrate different banks with their own APIs into your refund system, you could use an adapter to standardize their APIs into a single interface your application could interact with.
Legacy Payment Systems: Adapting an older payment system's API to work with your new transaction processing logic, ensuring backward compatibility while allowing new systems to be used.
Conclusion