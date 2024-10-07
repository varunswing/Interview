# Resume Topics 

**Migration of Refund System:**

I led the complex migration of our entire refund system, which involved moving critical components like Kafka, databases, and deployment services from Paytm Bank to four other banks. This project required a lot of technical expertise and careful planning to ensure there was no impact on customers or merchants during the migration. Managing a project of this scale under pressure helped me strengthen my ability to deliver without disruptions.

**Team Transitions and Learning:**

Throughout my journey, I had the opportunity to work across three different teams – Fastag, UPI Refund, and Passbook & Payment Combination. These roles exposed me to a wide range of systems like user and pass management, refunds, and payment screens. Managing these systems simultaneously allowed me to gain a deep understanding of each domain and sharpen my ability to switch contexts and adapt quickly to new challenges.

**OnCall and Issue Resolution:**

As an OnCall engineer for the 24/7 UPI system, I was responsible for monitoring live production issues, such as success rate drops, database lags, and Kafka consumer lags. My debugging skills improved significantly during this time, as I learned to troubleshoot effectively using Kibana logs, analyze code, monitor Grafana graphs, and diagnose database issues. This experience gave me a better understanding of complex, real-time systems and how to resolve issues under pressure.

**Cron Jobs and Kafka:**

I implemented a series of cron jobs that increased our refund transaction success rate to 99.5% for an avg of 3-4Lack refund txns every day for around 80L merchant txns every Day. These jobs handled retries, reconciliation, and status checks. In addition, I worked with Kafka to manage fault-tolerant message delivery by implementing retry mechanisms for failed transactions, which gave me valuable insights into data pipelines and event-driven architectures.

**DownStreamCallbacks and CountDownLatch (Async calls, MultiThreading):**

Implemented downstream service callbacks using Spring Boot with both synchronous (RestTemplate) and asynchronous (WebClient) approaches to retrieve essential information for a passbook and payment combination system, fetching details such as sender/receiver information (VPA, account ID, bank details, etc.), ensuring smooth and accurate transaction processing.

**MySQL Database Management:**

I took the lead in migrating refund transaction data across multiple banks, optimizing our database queries for better performance, and managing db partitioning and sharding. This project really highlighted the importance of efficient database management and how to handle complex, multi-bank systems securely and efficiently.

**Grafana and Real-time Monitoring:**

Using Grafana, I set up precise alerts and monitored system health, which helped maintain a success rate above 99%. This proactive monitoring approach allowed me to catch discrepancies early and ensure quick resolution, keeping our systems running smoothly.

**API Development and Testing:**

I’ve developed and tested APIs across different teams, focusing on enhancing their functionality while making sure they met all business needs. I’m committed to rigorous testing and ensuring reliability, which helped me refine my RESTful API design skills.

**Jenkins and Deployments:**

In my work with Jenkins, I learned how to build and deploy services across multiple environments—dev, stage, and production. By managing deployments end-to-end, I ensured smooth and efficient releases, and this hands-on experience gave me confidence in automating and streamlining deployment processes.

**Test-Driven Development and SonarQube:**

By maintaining over 90% unit test coverage, I ensured our codebase was always reliable. I actively resolved issues identified by SonarQube, improving code quality by 45% and reducing production incidents by 30%. This focus on quality really strengthened my ability to deliver cleaner, more robust code.

**SDLC & Team Collaboration:**

I played an active role in conducting code reviews, tracking project progress in Jira, and leading Scrum meetings. These practices helped improve team synchronization and ensured that our projects stayed on track. My involvement in these processes strengthened my understanding of the software development lifecycle and the importance of team collaboration.


## Problems And Challenges Faced

1. One of the major challenges I faced was during the migration of the refund system from Paytm Bank to four other banks. Coordinating such a large-scale migration required managing multiple services like Kafka, databases, deployment properties, and real-time monitoring through Grafana and Kibana. The biggest difficulty was ensuring that there was no downtime for customers or merchants. To manage this, I had to be extremely careful with planning and coordination across different teams and ensure seamless integration between the systems. We ran several rounds of testing and monitoring to minimize risk, and during the live migration, we kept a close eye on all the services. Despite the scale of the task, we were able to execute it successfully without impacting any transactions.

2. Another challenge was during my time as an OnCall engineer for the UPI system. The UPI system requires 24/7 monitoring, and there were instances where we saw success rates dropping or database lags during peak hours. One particular issue I remember was a Kafka consumer lag that started affecting transaction processing speed. The challenge was to identify the root cause quickly, as it was affecting real-time transactions. I had to analyze logs in Kibana, monitor related graphs in Grafana, and dive into the relevant parts of the code to find out what was slowing down the consumers. After thorough debugging, we realized that one of the services wasn’t scaling properly, so I worked with the team to scale up the service and resolve the issue.

3. Handling database migrations and optimizations was another tricky area. When moving refund transaction data across multiple banks, we faced challenges around performance and partitioning. There was a lot of pressure to ensure that queries were optimized and that there was no data loss during migration. By implementing db sharding and partitioning, I was able to reduce the load on individual databases and make the whole system more efficient.

4. Throughout my time at Paytm, one recurring challenge was ensuring code quality and adhering to best practices, especially when managing tight deadlines. SonarQube was a big help here, as it flagged issues that we needed to address, but working with it required discipline in fixing bugs and blockers while also maintaining productivity. This process really strengthened my focus on delivering clean, high-quality code, even when under pressure."



## Design Patterns Used Till Date

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