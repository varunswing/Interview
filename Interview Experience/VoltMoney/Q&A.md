# Technical Interview at VoltMoney

## Ways to optimize db queries

Optimizing database queries is crucial for improving the performance and efficiency of applications. Here are some effective strategies for query optimization:

---

### **1. Indexing**
- **Use appropriate indexes:** Create indexes on columns frequently used in `WHERE`, `JOIN`, `GROUP BY`, and `ORDER BY` clauses.
- **Covering indexes:** Ensure the index contains all the columns needed by the query to avoid accessing the table.
- **Unique indexes:** Use unique indexes where appropriate to speed up searches and ensure data integrity.
- **Avoid over-indexing:** Too many indexes can slow down write operations (INSERT/UPDATE/DELETE).

---

### **2. Query Optimization**
- **Select only necessary columns:** Use `SELECT column1, column2` instead of `SELECT *`.
- **Filter data early:** Use `WHERE` clauses to limit the rows processed.
- **Use LIMIT:** For large datasets, limit the number of rows retrieved.
- **Optimize JOINs:** Prefer smaller datasets first in the join order and use appropriate join types (INNER JOIN, LEFT JOIN, etc.).
- **Avoid correlated subqueries:** Replace them with JOINs where possible.

---

### **3. Analyze and Rewrite Queries**
- **Use EXPLAIN plans:** Analyze query execution plans to understand how the database processes the query.
- **Decompose complex queries:** Break down large queries into smaller, manageable parts.
- **Use Common Table Expressions (CTEs):** Simplify readability and debugging of complex queries.
- **Denormalization:** When appropriate, denormalize data to reduce the need for joins.

---

### **4. Database Configuration**
- **Connection pooling:** Use connection pools to reuse database connections efficiently.
- **Caching:** Use in-memory caching mechanisms (e.g., Redis, Memcached) for frequently accessed data.
- **Partitioning:** Partition large tables to reduce the size of the dataset being scanned.
- **Sharding:** Distribute data across multiple databases for high scalability.

---

### **5. Optimize Aggregations**
- **Use materialized views:** Precompute and store complex aggregates.
- **Batch processing:** Aggregate data in batches rather than processing it in real time.
- **Index aggregates:** Use indexed views or pre-aggregated tables where necessary.

---

### **6. Reduce Lock Contention**
- **Batch inserts/updates:** Break large operations into smaller batches.
- **Use row-level locking:** Avoid table locks by using row-level locking mechanisms.
- **Optimize transactions:** Keep transactions short and avoid unnecessary locks.

---

### **7. Use Query Caching**
- Enable query caching at the database or application layer for repetitive queries.

---

### **8. Data Optimization**
- **Archive old data:** Move rarely used data to a separate archive database.
- **Normalize where applicable:** Reduce redundancy while maintaining necessary joins.

---

### **9. Monitor and Tune Regularly**
- Use tools like Kibana, Grafana, or database-specific monitoring tools to identify slow queries and optimize them.
- Perform periodic database maintenance tasks such as re-indexing and updating statistics.


## How does db query impact us and after optimization what perks we get

### **Impact of Database Queries on System Performance**

Poorly optimized database queries can significantly affect the performance, user experience, and cost-effectiveness of your application:

---

#### **1. Increased Latency**
- **Slow response times:** Unoptimized queries take longer to execute, increasing latency for end users.
- **Cascading delays:** Slow queries in critical paths can bottleneck entire workflows, affecting dependent services.

#### **2. High Resource Utilization**
- **CPU and memory spikes:** Complex or inefficient queries consume excessive computational resources, leaving less for other operations.
- **I/O overhead:** Scanning large datasets or performing many joins increases disk reads and network usage.

#### **3. Scalability Bottlenecks**
- **Limited user support:** High query execution times reduce the system's ability to handle concurrent users.
- **Downtime risks:** As the load increases, slow queries may cause database locking or even crashes.

#### **4. Costs**
- **Infrastructure expenses:** High resource consumption leads to increased cloud or hardware costs.
- **Operational overhead:** Engineers spend more time troubleshooting and optimizing under pressure.

#### **5. User Experience**
- **Customer dissatisfaction:** Slow performance can lead to frustrated users abandoning the system.
- **Business impact:** Poor user experience affects retention rates, revenue, and overall brand reputation.

---

### **Perks of Query Optimization**

Optimizing database queries yields multiple benefits across technical, financial, and operational domains:

---

#### **1. Improved Performance**
- **Faster response times:** Queries execute more quickly, leading to better performance for users.
- **Reduced latency:** Efficient data retrieval ensures that applications respond in real time.

#### **2. Enhanced Scalability**
- **Support for more users:** Optimized queries handle larger workloads with the same hardware resources.
- **Future-proofing:** Efficient queries allow systems to scale seamlessly as data grows.

#### **3. Lower Resource Consumption**
- **Reduced server load:** Optimized queries minimize CPU, memory, and disk usage.
- **Energy efficiency:** Lower computational demand translates to reduced power consumption.

#### **4. Cost Savings**
- **Infrastructure costs:** Reduced resource usage cuts costs for cloud storage and computing power.
- **Maintenance overhead:** Less troubleshooting and downtime mean fewer operational expenses.

#### **5. Better User Experience**
- **Higher satisfaction:** Faster systems lead to happier users and better retention rates.
- **Competitive advantage:** Improved performance can differentiate your product from competitors.

#### **6. Operational Reliability**
- **Fewer system crashes:** Reduced contention and resource usage lead to more stable systems.
- **Efficient debugging:** Clearer execution plans and predictable behavior simplify problem resolution.

#### **7. Analytics and Reporting**
- **Faster insights:** Optimized aggregation and reporting queries provide quicker access to data for decision-making.
- **Real-time monitoring:** Better performance enables live dashboards and analytics.

---

### **Example Scenario**
**Before Optimization:**
- A query fetching transaction logs takes 5 seconds due to a full table scan.  
- This increases page load time, server utilization, and user frustration.  

**After Optimization:**
- Adding an index to the `transaction_id` column reduces execution time to 0.5 seconds.  
- Results:
  - 90% faster user experience.
  - Server resources freed for other tasks.
  - Infrastructure costs reduced due to optimized resource usage.


## which all nosql database are there

NoSQL databases are designed to handle a variety of data types and models beyond traditional relational databases. Here are the major categories of NoSQL databases and examples of each:

---

### **1. Document-Oriented Databases**
- Store data in document formats like JSON, BSON, or XML, making them ideal for semi-structured or hierarchical data.
- Examples:
  - **MongoDB**
  - **Couchbase**
  - **CouchDB**
  - **Amazon DocumentDB**
  - **RethinkDB**

---

### **2. Key-Value Databases**
- Data is stored as a collection of key-value pairs, similar to a dictionary or hash table.
- Examples:
  - **Redis**
  - **Amazon DynamoDB**
  - **Riak**
  - **Aerospike**
  - **Hazelcast**

---

### **3. Column-Family Databases**
- Store data in columns instead of rows, enabling efficient handling of large datasets with sparse columns.
- Examples:
  - **Apache Cassandra**
  - **HBase**
  - **ScyllaDB**
  - **Hypertable**

---

### **4. Graph Databases**
- Focus on relationships between data points, using nodes (entities) and edges (relationships).
- Examples:
  - **Neo4j**
  - **Amazon Neptune**
  - **ArangoDB** (multi-model, with strong graph support)
  - **OrientDB** (multi-model, including graph support)
  - **TigerGraph**

---

### **5. Multi-Model Databases**
- Support multiple types of data models (e.g., document, graph, key-value) within the same database.
- Examples:
  - **ArangoDB**
  - **OrientDB**
  - **MarkLogic**
  - **Couchbase**

---

### **6. Time-Series Databases**
- Optimized for handling time-stamped or time-series data.
- Examples:
  - **InfluxDB**
  - **TimescaleDB** (built on PostgreSQL)
  - **Graphite**
  - **Prometheus**
  - **OpenTSDB**

---

### **7. Object-Oriented Databases**
- Store objects as defined in object-oriented programming languages.
- Examples:
  - **db4o**
  - **ObjectDB**
  - **InterSystems Caché**

---

### **8. Search Engines (NoSQL for Full-Text Search)**
- Designed for full-text search and analytics.
- Examples:
  - **Elasticsearch**
  - **Apache Solr**
  - **Splunk**

---

### **9. Wide-Column Stores**
- Similar to column-family databases but optimized for large-scale data analytics.
- Examples:
  - **Google Bigtable** (inspiration for Cassandra and HBase)
  - **Azure Cosmos DB** (also supports multiple models)

---

### **10. Ledger Databases**
- Specialized for immutable, cryptographically verifiable transaction logs.
- Examples:
  - **Amazon QLDB**
  - **Hyperledger Fabric** (blockchain-based ledger)

---

### **11. Real-Time NoSQL Databases**
- Optimized for real-time streaming and analytics.
- Examples:
  - **Redis Streams**
  - **Apache Kafka** (used more for messaging but supports real-time processing)

---

### **12. In-Memory Databases**
- Store data entirely in memory for ultra-fast access.
- Examples:
  - **Redis**
  - **Memcached**
  - **SAP HANA**
  - **VoltDB**

---

#### **How to Choose a NoSQL Database**
- **Key-Value:** Use for caching, session management (e.g., Redis).
- **Document:** Use for content management, catalogs (e.g., MongoDB).
- **Column-Family:** Use for IoT data, analytics (e.g., Cassandra).
- **Graph:** Use for social networks, fraud detection (e.g., Neo4j).
- **Time-Series:** Use for monitoring, metrics (e.g., InfluxDB).


## how to implement thread through executor service

Using `ExecutorService` in Java is a recommended approach for implementing and managing threads. It provides a higher-level replacement for manually managing threads and makes multithreading more manageable, scalable, and efficient.

---

### **Steps to Implement Threads Using `ExecutorService`**

1. **Import Required Classes**:
   - `java.util.concurrent.ExecutorService`
   - `java.util.concurrent.Executors`

2. **Create a Task**:
   Implement the `Runnable` or `Callable` interface for the task you want to run.

3. **Use `ExecutorService` to Manage Threads**:
   Create a thread pool using `Executors` and submit the tasks.

---

### **Example with `Runnable`**
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyTask implements Runnable {
    private String taskName;

    public MyTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is executing " + taskName);
    }
}

public class ExecutorServiceExample {
    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit tasks to the executor
        for (int i = 1; i <= 5; i++) {
            MyTask task = new MyTask("Task " + i);
            executor.submit(task);
        }

        // Shutdown the executor
        executor.shutdown();
    }
}
```

#### **Output Example**
```
pool-1-thread-1 is executing Task 1
pool-1-thread-2 is executing Task 2
pool-1-thread-3 is executing Task 3
pool-1-thread-1 is executing Task 4
pool-1-thread-2 is executing Task 5
```

---

### **Example with `Callable` (Returning Results)**
`Callable` allows tasks to return a value or throw an exception.

```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyCallable implements Callable<String> {
    private String taskName;

    public MyCallable(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName() + " completed " + taskName;
    }
}

public class CallableExample {
    public static void main(String[] args) {
        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            // Submit Callable tasks
            Future<String> result1 = executor.submit(new MyCallable("Task 1"));
            Future<String> result2 = executor.submit(new MyCallable("Task 2"));

            // Get results
            System.out.println(result1.get());
            System.out.println(result2.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Shutdown the executor
            executor.shutdown();
        }
    }
}
```

#### **Output Example**
```
pool-1-thread-1 completed Task 1
pool-1-thread-2 completed Task 2
```

---

### **Types of Thread Pools in `Executors`**
1. **Fixed Thread Pool**: Predefined number of threads.
   ```java
   ExecutorService executor = Executors.newFixedThreadPool(3);
   ```

2. **Cached Thread Pool**: Creates new threads as needed and reuses idle threads.
   ```java
   ExecutorService executor = Executors.newCachedThreadPool();
   ```

3. **Single Thread Executor**: Single thread executes tasks sequentially.
   ```java
   ExecutorService executor = Executors.newSingleThreadExecutor();
   ```

4. **Scheduled Thread Pool**: For scheduling tasks with delays or at fixed intervals.
   ```java
   ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
   ```

---

### **Benefits of `ExecutorService`**
- **Thread Management**: Automatically handles thread creation and termination.
- **Performance**: Efficient resource utilization by reusing threads in a pool.
- **Flexibility**: Supports both one-time and periodic task execution.
- **Scalability**: Suitable for large-scale concurrent applications.

## How to make sure that a lock is there in thread

To ensure that a lock is being used properly in a multithreaded program, you can use Java's synchronization mechanisms such as `synchronized` blocks, `ReentrantLock`, or other concurrency utilities in the `java.util.concurrent.locks` package. Here's how you can ensure a lock is in place:

---

### **1. Using `synchronized` Blocks**
The `synchronized` keyword ensures that only one thread can access a block of code or method at a time for a particular object.

#### Example:
```java
class SharedResource {
    public void doWork() {
        synchronized (this) {  // Lock on the current object
            System.out.println(Thread.currentThread().getName() + " is executing");
            try {
                Thread.sleep(1000);  // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SynchronizedExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Runnable task = () -> resource.doWork();

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}
```

#### Output (Guaranteed Locking):
```
Thread 1 is executing
Thread 2 is executing
```
*Only one thread executes at a time.*

---

### **2. Using `ReentrantLock`**
`ReentrantLock` is part of `java.util.concurrent.locks`. It provides more control over locking, like trying to acquire a lock with a timeout or checking if the lock is held.

#### Example:
```java
import java.util.concurrent.locks.ReentrantLock;

class SharedResource {
    private final ReentrantLock lock = new ReentrantLock();

    public void doWork() {
        lock.lock();  // Acquire the lock
        try {
            System.out.println(Thread.currentThread().getName() + " is executing");
            Thread.sleep(1000);  // Simulate some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // Release the lock
        }
    }
}

public class ReentrantLockExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Runnable task = () -> resource.doWork();

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}
```

#### Output (Guaranteed Locking):
```
Thread 1 is executing
Thread 2 is executing
```

---

### **3. Using `tryLock` with Timeout (Advanced ReentrantLock)**
You can ensure a lock is acquired without causing deadlocks by using `tryLock` with a timeout.

#### Example:
```java
import java.util.concurrent.locks.ReentrantLock;

class SharedResource {
    private final ReentrantLock lock = new ReentrantLock();

    public void doWork() {
        try {
            if (lock.tryLock()) {  // Attempt to acquire the lock
                try {
                    System.out.println(Thread.currentThread().getName() + " is executing");
                    Thread.sleep(1000);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class TryLockExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Runnable task = () -> resource.doWork();

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}
```

#### Output (If a lock is not available):
```
Thread 1 is executing
Thread 2 could not acquire the lock
```

---

### **4. Using `ReadWriteLock` for Shared Resources**
If your application involves multiple readers and writers, you can use `ReadWriteLock` to allow multiple threads to read simultaneously but only one thread to write.

#### Example:
```java
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedResource {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is reading");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is writing");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}

public class ReadWriteLockExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Runnable reader = () -> resource.read();
        Runnable writer = () -> resource.write();

        Thread t1 = new Thread(reader, "Reader 1");
        Thread t2 = new Thread(reader, "Reader 2");
        Thread t3 = new Thread(writer, "Writer");

        t1.start();
        t2.start();
        t3.start();
    }
}
```

#### Output:
```
Reader 1 is reading
Reader 2 is reading
Writer is writing
```

---

### **How to Ensure the Lock is Being Used**
1. **Debugging**: Use logs to verify that threads acquire and release locks as expected.
2. **Thread Dump Analysis**: Check thread states during runtime to confirm proper lock usage.
3. **Testing**: Simulate concurrent access with tools like JUnit or stress testing frameworks.
4. **Deadlock Detection**: Avoid nested locks and use tools like VisualVM to detect deadlocks. 

These mechanisms help ensure the correctness and efficiency of thread locking

## if there is fixed no of threads that can be executed, and comes more than that how to manage that

When dealing with a fixed number of threads and more tasks than can be executed simultaneously, a **thread pool** is the most efficient way to manage this situation. Java's **Executor framework** provides tools to manage such scenarios gracefully.

### Using a Thread Pool

A **thread pool** creates a fixed number of threads that execute tasks from a queue. If tasks come in excess of the pool's capacity, they are queued and executed when a thread becomes available. This ensures:
1. Efficient resource utilization.
2. Controlled thread creation.
3. Better system performance under heavy loads.

---

### Steps to Manage with a Fixed Number of Threads:
1. Use `Executors.newFixedThreadPool(int nThreads)` to create a thread pool with a fixed number of threads.
2. Submit tasks to the pool using `submit()` or `execute()`.
3. The tasks exceeding the capacity are queued and executed as threads become available.

---

### Example: Fixed Thread Pool with Overflowing Tasks

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {

    public static void main(String[] args) {
        // Step 1: Create a thread pool with a fixed number of threads
        int threadCount = 3; // Fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        // Step 2: Simulate more tasks than threads
        int totalTasks = 10; // Total number of tasks
        for (int i = 1; i <= totalTasks; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running on " + Thread.currentThread().getName());
                try {
                    // Simulate task processing time
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Task interrupted: " + taskId);
                }
                System.out.println("Task " + taskId + " completed on " + Thread.currentThread().getName());
            });
        }

        // Step 3: Shut down the executor
        executor.shutdown(); // No new tasks will be accepted; running tasks will complete
        System.out.println("All tasks submitted.");
    }
}
```

---

### Explanation:
1. **Thread Pool Creation**:
   - `Executors.newFixedThreadPool(3)` creates a thread pool with exactly 3 threads.
   - Only 3 threads can run tasks concurrently.

2. **Task Submission**:
   - A total of 10 tasks are submitted to the thread pool.
   - The first 3 tasks are immediately assigned to the 3 threads.
   - The remaining 7 tasks are queued and executed when threads become free.

3. **Output**:
   - Tasks are executed in batches, ensuring only 3 run simultaneously.
   - The program waits for all tasks to complete before exiting.

---

### Benefits of Fixed Thread Pool:
1. **Queue Management**:
   - Excess tasks are queued automatically, ensuring no task is dropped.

2. **Efficient Resource Usage**:
   - Prevents overloading the system by controlling the number of active threads.

3. **Easy to Scale**:
   - You can adjust the `nThreads` parameter based on system capacity.

---

### Advanced: Custom Thread Pool with `ThreadPoolExecutor`
For fine-grained control, you can use `ThreadPoolExecutor` directly, allowing you to configure:
- Core thread pool size.
- Maximum thread pool size.
- Keep-alive time for idle threads.
- The task queue type (e.g., bounded or unbounded).

```java
import java.util.concurrent.*;

public class CustomThreadPoolExample {

    public static void main(String[] args) {
        // Custom thread pool configuration
        int corePoolSize = 3; // Core threads
        int maximumPoolSize = 5; // Max threads
        long keepAliveTime = 10; // Time for idle threads to wait before termination
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(10); // Task queue with capacity 10

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            TimeUnit.SECONDS,
            queue
        );

        // Submit tasks
        for (int i = 1; i <= 15; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " is running on " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate task processing
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " completed on " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
```

---

### Key Considerations:
- **Task Overflow**: Ensure the queue size (`LinkedBlockingQueue` or `ArrayBlockingQueue`) is appropriate to prevent overflow. For unbounded queues, monitor system resources carefully.
- **Rejected Execution Policy**: Handle cases where tasks cannot be queued or executed by setting a `RejectedExecutionHandler`. For example:
  - `AbortPolicy`: Throws an exception.
  - `CallerRunsPolicy`: The task is executed in the caller's thread.
  - `DiscardPolicy`: Silently discards the task.
  - `DiscardOldestPolicy`: Discards the oldest queued task.

By using these techniques, you can effectively manage scenarios where the number of tasks exceeds the number of threads available.

## timeout on threads

Timeouts on threads can be implemented in Java using various techniques to ensure a thread doesn't run indefinitely. Below are common methods to handle thread timeouts effectively:

---

### **1. Using `Thread.join(timeout)`**
You can use the `join(timeout)` method to wait for a thread to complete its execution within a specified time. If the thread doesn't finish in time, the calling thread resumes execution.

#### Example:
```java
class MyTask extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);  // Simulate long-running task
            System.out.println("Task completed");
        } catch (InterruptedException e) {
            System.out.println("Task interrupted");
        }
    }
}

public class ThreadTimeoutExample {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        task.start();

        try {
            task.join(2000);  // Wait for a maximum of 2 seconds
            if (task.isAlive()) {
                System.out.println("Task timed out");
                task.interrupt();  // Interrupt the task
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

#### Output:
```
Task timed out
Task interrupted
```

---

### **2. Using `ExecutorService` with Timeout**
With `ExecutorService`, you can submit a task and use the `Future.get(timeout, TimeUnit)` method to wait for a task's completion. If it doesn't complete in time, a `TimeoutException` is thrown.

#### Example:
```java
import java.util.concurrent.*;

public class ExecutorTimeoutExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            Thread.sleep(5000);  // Simulate long-running task
            return "Task completed";
        };

        Future<String> future = executor.submit(task);

        try {
            System.out.println(future.get(2, TimeUnit.SECONDS));  // Wait for a maximum of 2 seconds
        } catch (TimeoutException e) {
            System.out.println("Task timed out");
            future.cancel(true);  // Cancel the task
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
```

#### Output:
```
Task timed out
```

---

### **3. Using `ScheduledExecutorService` for Timeout**
You can use a `ScheduledExecutorService` to terminate a thread after a timeout.

#### Example:
```java
import java.util.concurrent.*;

public class ScheduledTimeoutExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                Thread.sleep(5000);  // Simulate long-running task
                System.out.println("Task completed");
            } catch (InterruptedException e) {
                System.out.println("Task interrupted");
            }
        };

        Thread taskThread = new Thread(task);
        taskThread.start();

        // Schedule a timeout to interrupt the thread after 2 seconds
        scheduler.schedule(() -> {
            if (taskThread.isAlive()) {
                System.out.println("Task timed out");
                taskThread.interrupt();
            }
        }, 2, TimeUnit.SECONDS);

        scheduler.shutdown();
    }
}
```

#### Output:
```
Task timed out
Task interrupted
```

---

### **4. Using `FutureTask` with Timeout**
A `FutureTask` can wrap a task and set a timeout to interrupt it if necessary.

#### Example:
```java
import java.util.concurrent.*;

public class FutureTaskTimeoutExample {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(5000);  // Simulate long-running task
            return "Task completed";
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            System.out.println(futureTask.get(2, TimeUnit.SECONDS));  // Wait for a maximum of 2 seconds
        } catch (TimeoutException e) {
            System.out.println("Task timed out");
            thread.interrupt();  // Interrupt the thread
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
```

#### Output:
```
Task timed out
```

---

### **5. Key Points**
- **Interrupt Handling**: Ensure tasks handle `InterruptedException` properly to allow graceful termination.
- **Resource Management**: Always clean up resources (e.g., shutdown executors) to avoid memory leaks.
- **Timeout Settings**: Choose timeout durations carefully based on your application's needs to balance performance and responsiveness.

By implementing these timeout mechanisms, you can ensure your threads do not run indefinitely, improving application stability and performance.









