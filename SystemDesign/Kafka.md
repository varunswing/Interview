# Kafka Interview Questions And Answers

## Que 1. Share your Kafka work exp

1. Used Kafka to initiate refunds and retry refunds**

> Used kafka to initate refunds as from UPI JOBs (incase of retry) service we push initate request in kafka 
> Which is received by UPI CONSUMERs service which then do a bank health check through UPI Gateway service (NPCI & Payee bank heatlh check), 
> After getting response for bank health check, it passes the request to UPI SWITCH service for M2A (merchant/pool account to payer bank account via NPCI)
> Which then do the refund txn and passes the acknowledgement to UPI CONSUMERs and UPI REFUND service

2. Used it to verify failed job

> Used is to match the refund txn with failed status with the UPI SWITCH service.

3. Used it to perform recon

> Used to match the refund txn with details with the UPI SWITCH service.

I worked extensively with Kafka to build and optimize event-driven systems. I used Kafka for asynchronous communication between microservices, ensuring reliable and scalable message transmission across different components.

Some key aspects of my Kafka experience include:

1. **Creating and Managing Topics:** I designed Kafka topics to handle different event streams and ensured that partitions were configured for optimal throughput.
2. **Producer and Consumer Implementations:** I developed Java-based Kafka producers and consumers to handle various business processes, such as payment processing, refunds, and order updates.
3. **Error Handling and Retries:** I implemented retry mechanisms to handle transient failures, using Kafka’s dead-letter queues (DLQ) to capture failed messages and prevent data loss.
4. **Monitoring and Optimization:** I used Grafana and Kibana dashboards to monitor Kafka metrics such as lag and throughput, ensuring smooth operations and identifying bottlenecks early.
5. **Kafka Streams and Real-Time Analytics:** In some projects, I utilized Kafka Streams to perform lightweight transformations on event data in real-time, improving data enrichment.
6. **Scaling and Performance:** I worked on optimizing Kafka configurations like batch sizes, retention policies, and partitioning, ensuring it could handle high message volumes efficiently.

Overall, Kafka helped us improve the resilience and scalability of our systems, and I actively collaborated with other developers to ensure seamless integration with microservices. My experience with Kafka gave me deep insights into building robust, distributed systems."

## Que 2. Explain Key Kafka Concepts

**Key Kafka concepts** — brokers, partitions, consumer groups, and offset management—to enhance your understanding:

### 1. Broker
A broker is a Kafka server responsible for storing and managing messages in Kafka. Each Kafka cluster consists of multiple brokers that work together to provide high availability, fault tolerance, and scalability. Brokers receive, store, and serve messages to consumers.

**How brokers work:**

When a producer sends a message to Kafka, the broker stores it in the appropriate topic and partition.
Each broker in the cluster handles multiple partitions and distributes the load.
If a broker fails, the Kafka cluster ensures the data is available by replicating it on other brokers.
Example: In a 3-broker Kafka cluster, data is spread across all brokers. If one broker goes down, others ensure the system remains functional, preventing data loss.

### 2. Partition
A partition is a way to divide a Kafka topic into smaller, ordered segments that can be stored on different brokers. Partitions allow Kafka to handle large volumes of data efficiently by enabling parallel processing.

**Key aspects of partitions:**

Each partition is ordered: Messages inside a partition are written sequentially, which ensures message order is maintained within a partition.
**Parallelism:** Multiple partitions can be processed simultaneously by different consumers.
**Load distribution:** Partitions distribute data across brokers for improved scalability.
**Example:**
A "Transactions" topic could have 10 partitions, with each partition containing a subset of transaction events. This allows multiple consumers to read and process different partitions simultaneously, improving system performance.

### 3. Consumer Group
A consumer group is a set of consumers that work together to consume messages from a topic’s partitions. Kafka assigns partitions to individual consumers within the group, ensuring that each message is processed only once within the group.

Key characteristics of consumer groups:**

**Load balancing:** Kafka ensures that each partition is processed by only one consumer within a group, distributing the load.
**Fault tolerance:** If one consumer in the group fails, Kafka reassigns its partition(s) to other consumers to ensure continuous processing.
**Multiple consumers:** If multiple consumer groups are reading from the same topic, each group will get its own copy of the messages.
**Example:**
If you have a topic with 6 partitions and a consumer group with 3 consumers, Kafka will assign 2 partitions to each consumer. If one consumer fails, its partitions are reassigned to the remaining consumers.

### 4. Offset Management
An offset is a unique identifier for each message within a partition. It represents the position of a message in the log, allowing Kafka to keep track of which messages have been consumed.

**Types of offset management:**

**Automatic Offset Management:** Kafka automatically commits the offsets when a message is read, ensuring the consumer can resume from the last committed offset in case of failure.
**Manual Offset Management:** In more critical applications, consumers can manually commit offsets after processing a message. This ensures that only successfully processed messages are acknowledged, avoiding data loss or duplication.
**Example:**
If a consumer processes the first 50 messages from a partition, its offset will be 50. If the consumer restarts, it can continue from offset 50, ensuring no messages are missed or reprocessed.

**How These Concepts Work Together:**
Brokers store messages across partitions.
Partitions enable parallel consumption by distributing messages across multiple consumers.
Consumer groups ensure scalability and fault tolerance by balancing the load and reassigning partitions in case of failures.
Offsets track the progress of each consumer, ensuring they process messages exactly once (or as needed, depending on the offset strategy).

## Que 3. Expalin terms of kafka like brokers, partitions, consumer groups, and offset with diagram

Apache Kafka is an open-source, distributed streaming platform. Here's an explanation of the terms you requested, along with a simple diagram:
**Brokers:** Kafka clusters consist of multiple servers, called brokers. Each broker is responsible for maintaining and distributing data. Producers send data to brokers, which then forward it to consumers.
**Partitions:** Topics in Kafka are divided into partitions, which are ordered, immutable logs. Each partition is stored on a single broker, but can be replicated across multiple brokers for fault tolerance. Partitions allow for parallel processing and scalability.
**Consumer Groups:** Consumers can be organized into consumer groups, which are used to subscribe to topics. Each consumer group maintains its offset, which is the last message consumed by the group. This ensures that messages are processed only once by each group.
**Offset:** The offset is the last message consumed by a consumer group. It's stored in a special Kafka topic called __consumer_offsets. When a consumer group consumes a message, it commits the offset to this topic, so that it can resume from the last consumed message in case of a failure.
Here's a simple diagram to illustrate these concepts:
                      +---------------+
                      |  Producer  |
                      +---------------+
                             |
                             |
                             v
                      +---------------+
                      |  Broker 1  |
                      |  (Topic: X)  |
                      |  Partition 0 |
                      |  Partition 1 |
                      +---------------+
                             |
                             |
                             v
                      +---------------+
                      |  Broker 2  |
                      |  (Topic: X)  |
                      |  Partition 2 |
                      |  Partition 3 |
                      +---------------+
                             |
                             |
                             v
                      +---------------+
                      | Consumer  |
                      |  Group 1    |
                      |  (Offset: 10) |
                      +---------------+
                             |
                             |
                             v
                      +---------------+
                      | Consumer  |
                      |  Group 2    |
                      |  (Offset: 5)  |
                      +---------------+
In this diagram:
The producer sends data to Broker 1, which is responsible for Topic X.
Topic X is divided into partitions (Partition 0, Partition 1, etc.) stored across multiple brokers (Broker 1, Broker 2, etc.).
Consumer Group 1 and Consumer Group 2 subscribe to Topic X and maintain their own offsets (Offset: 10 and Offset: 5, respectively).
I hope this helps! Let me know if you have any further questions.

## Que 4. How to Manage Kafka-related issues such as consumer lag, offset handling.

Managing Kafka-related issues such as consumer lag, offset handling, and other performance bottlenecks is crucial for ensuring reliable message streaming and smooth system performance. Here’s a breakdown of common Kafka issues and practical strategies to manage them.

### 1. Consumer Lag Management
Consumer lag occurs when a consumer is slower than the producer, resulting in a growing gap between the latest message in a partition and the one the consumer has processed.

**How to Identify and Monitor Lag:**
**Monitoring Tools:** Use Kafka Monitoring tools like,
Grafana with Prometheus to visualize lag metrics.
Kibana if you are using Elasticsearch for logs.
Kafka Offset Explorer or Burrow to monitor lag.
**Metrics to Watch:**
consumer_lag:** Measures the difference between the producer’s latest offset and the consumer’s committed offset.
**Throughput (bytes/sec):** Shows how fast the consumer is reading data.
**How to Handle Lag:**
Increase Consumer Parallelism:** Add more consumers to the consumer group to distribute the load across multiple partitions.
**Tune Consumer Configurations:**
Adjust fetch.min.bytes and max.poll.records to control the number of records fetched per poll.
Increase session.timeout.ms to avoid unnecessary rebalancing.
**Optimize Consumers:** Ensure consumers are processing messages efficiently (e.g., minimize I/O operations in message processing).
**Kafka Partitioning:** Add more partitions to the topic if the consumer group is overwhelmed with too much data.

### 2. Offset Management Strategies
Offsets are crucial for tracking the progress of consumers in Kafka. Poor offset management can lead to data loss or duplicate processing.

**Key Strategies for Offset Management:**
**Automatic Offset Commit:**

**When to Use:** Use for non-critical applications where processing failures don’t affect business logic.
**How:** Set enable.auto.commit=true in the consumer configuration to automatically commit offsets after polling messages.
**Manual Offset Commit:**

When to Use:** Use in critical workflows (e.g., payment or order processing) to ensure that only successfully processed messages are acknowledged.
**How:** Call commitSync() or commitAsync() after processing messages.
**Example:**
```java
try {
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
    for (ConsumerRecord<String, String> record :** records) {
        // Process the message
    }
    consumer.commitSync(); // Commit offset after processing
} catch (Exception e) {
    // Handle failure
}
```

**Use Dead Letter Queues (DLQ):** If a message cannot be processed after multiple retries, send it to a DLQ to avoid blocking other messages.

### 3. Rebalancing Issues and Consumer Timeouts
Frequent rebalancing can cause lag and disrupt processing, especially when consumer instances join or leave the group.

**How to Manage Rebalancing:**
**Optimize max.poll.interval.ms:** Set an appropriate value so that Kafka does not assume the consumer is dead while it’s processing large batches.
**Sticky Partition Assignment:** Use sticky assignment strategies to minimize rebalancing.
**Monitor Heartbeats:** Ensure that consumers send heartbeats regularly to avoid timeouts by setting heartbeat.interval.ms appropriately.

### 4. Broker and Partition Issues
Kafka brokers can experience issues such as broker failures or under-replicated partitions.

**How to Manage Broker and Partition Issues:**
**Replication Factor:** Set a replication factor greater than 1 to ensure high availability and fault tolerance.
**Monitor Broker Health:** Use Grafana to track broker metrics such as disk usage, CPU, and memory.
**Partition Rebalancing:** If a broker fails, Kafka rebalances partitions to other brokers. Use the kafka-reassign-partitions tool to manually rebalance if needed.

### 5. Tuning Kafka Configuration for Performance
Kafka’s default configuration might not suit all workloads. Proper tuning helps avoid bottlenecks.

**Key Configuration Parameters:**
**Producer Settings:**

**linger.ms:** Controls the time a producer waits before sending batches. Increase for better throughput.
**batch.size:** Set an appropriate batch size to balance latency and throughput.
**Consumer Settings:**

**max.poll.records:** Tune to control how many messages are processed in each poll.
**fetch.min.bytes:** Increase to reduce the number of polls and improve throughput.

### 6. Handling Data Loss and Duplicates
Kafka is designed to be fault-tolerant, but mismanagement of offsets or brokers can lead to data loss or duplicate messages.

**How to Handle Data Loss/Duplicates:**
**At-least-once Delivery:** Ensure consumers reprocess messages if they fail to commit the offset, even at the risk of duplicate processing.
**Exactly-once Semantics:** Use Kafka Streams or idempotent producers (by enabling enable.idempotence=true) to prevent duplicate message delivery.

### 7. Summary of Best Practices
**Monitor Lag and Broker Health:** Use tools like Grafana and Kibana to track Kafka’s health.
**Optimize Consumer Performance:** Tune max.poll.records and fetch.min.bytes to balance latency and throughput.
**Manage Offsets Carefully:** Use manual commits in critical workflows and leverage DLQs for error handling.
**Minimize Rebalancing Disruptions:** Use sticky partition assignments and appropriate timeouts.
**Ensure High Availability:** Use replication and partition rebalancing to prevent failures.
By following these strategies, you can effectively manage Kafka-related issues like lag, offsets, and broker failures, ensuring your event-driven system remains reliable and scalable.

## Que 5. What is maximum no of Kafka consumers we can have and what happens if we exceeds

The maximum number of consumers in a Kafka consumer group is limited by the number of partitions in the topic being consumed. Here’s how it works:

**Partition-Consumer Relationship:**
In Kafka, each partition in a topic can be assigned to only one consumer within a consumer group.
If you have N partitions, you can have at most N consumers in a consumer group actively consuming data.
Any additional consumers beyond the partition count will remain idle (i.e., they won’t receive any messages but will stay connected to the group).
**Example:**
If a topic has 10 partitions, you can have up to 10 active consumers in one consumer group.
If you add an 11th consumer, it won’t receive messages because all partitions are already assigned to other consumers.
**Impact of More Consumers than Partitions:**
**Ideal Case:** When the number of consumers is equal to or fewer than the partitions, each partition is assigned to exactly one consumer.
**Idle Consumers:** If you add more consumers than partitions, the extra consumers stay idle but will take over if another consumer fails (ensuring fault tolerance).
**Multiple Consumer Groups:**
Kafka allows multiple consumer groups to consume the same topic independently.

Each group will get its own copy of the data from the topic, regardless of how many partitions exist.
This is useful when different applications/processes need to consume the same topic data in parallel.
**How to Scale:**
If you need more active consumers, increase the number of partitions in the topic.
Be cautious:** Kafka’s performance may degrade if you have too many partitions (hundreds or thousands per broker), so it's essential to strike the right balance.
**Summary:**
Max Consumers per Group = Number of Partitions in the Topic.
Extra Consumers (more than partitions) will remain idle but can help with fault tolerance.
For more scaling, consider increasing the number of partitions or using multiple consumer groups.