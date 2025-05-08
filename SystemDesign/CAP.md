# **CAP Theorem: Consistency, Availability, Partition Tolerance**

## ✅ **1. What is CAP Theorem?**

CAP Theorem (also called **Brewer’s Theorem**) states that **in a distributed system, it is impossible to simultaneously guarantee all three of the following**:

* **C** – **Consistency**
* **A** – **Availability**
* **P** – **Partition Tolerance**

> You can **only achieve two out of the three** at any given time.

It’s a **foundational theory** for designing **distributed databases and microservices architectures**.

---

## ✅ **2. CAP Components Explained**

### 🔹 **Consistency (C)**

All nodes see the **same data at the same time**.

> Example: If you write to Node A, and immediately read from Node B, you get the updated value.

* Strong consistency = Linearizability.
* Ensures no stale reads.

### 🔹 **Availability (A)**

Every request gets a **non-error response**, even if it's not the most recent data.

> Example: Even if some nodes are down, the system responds.

* System is always responsive.
* No guarantee about data freshness.

### 🔹 **Partition Tolerance (P)**

The system continues to operate even if **network partitions** (communication failures between nodes) occur.

> Must tolerate dropped or delayed messages.

* **Mandatory** in distributed systems.
* You can't avoid partitions in real-world networks.

---

## ✅ **3. Why Can’t You Have All 3?**

Imagine a **network partition** (P) occurs:

* If you choose **C**, you must reject writes/reads to keep consistency → lose **A**.
* If you choose **A**, you serve from available nodes → risk **inconsistent** data → lose **C**.

> Hence, under partition, you **must choose between C and A**.

---

## ✅ **4. CAP Combinations (You Can Pick Two)**

| Model  | Guarantees                         | Compromises                                                   |
| ------ | ---------------------------------- | ------------------------------------------------------------- |
| **CP** | Consistency + Partition Tolerance  | Sacrifice Availability                                        |
| **AP** | Availability + Partition Tolerance | Sacrifice Consistency                                         |
| **CA** | Consistency + Availability         | No Partition Tolerance (not practical in distributed systems) |

---

## ✅ **5. Real-World Examples**

| System                               | Type    | Notes                                                               |
| ------------------------------------ | ------- | ------------------------------------------------------------------- |
| **MongoDB (default)**                | AP      | Prioritizes availability, eventual consistency                      |
| **Cassandra**                        | AP      | Eventual consistency; tunable consistency levels                    |
| **HBase**                            | CP      | Strong consistency; not always available under partition            |
| **Zookeeper**                        | CP      | Prioritizes consistency (used in coordination systems)              |
| **Redis (Cluster)**                  | Depends | Can be AP or CP based on config                                     |
| **Relational DBs (non-distributed)** | CA      | But not partition tolerant, so not usable for distributed workloads |

---

## ✅ **6. When to Use What?**

### 🔸 **CP (Consistency + Partition Tolerance)**

> Use when correctness is critical.
> Examples:

* Banking systems
* Inventory management
* Account balances

**Trade-off**: May become unavailable during network issues.

---

### 🔸 **AP (Availability + Partition Tolerance)**

> Use when high availability is more important than strict consistency.
> Examples:

* Social media feeds
* Product catalogs
* Messaging apps

**Trade-off**: Can serve stale or eventually consistent data.

---

### 🔸 **CA (Consistency + Availability)**

> Only works when **partition tolerance isn’t needed**, i.e., in non-distributed or local systems.
> Examples:

* Standalone SQL databases

**Trade-off**: Breaks under real network partitions.

---

## ✅ **7. Consistency Models Beyond CAP**

Distributed systems also deal with **weaker forms of consistency**:

* **Strong Consistency**: Every read gets the latest write.
* **Eventual Consistency**: Data converges over time.
* **Causal Consistency**: Operations respect cause-effect relationships.
* **Read-Your-Writes**: User sees their own changes immediately.

---

## ✅ **8. Diagram (Simplified Triangle)**

```
          Consistency (C)
              /\
             /  \
            /    \
           /      \
  (CA)    /        \   (CP)
        /          \
       /            \
Availability ------- Partition Tolerance
           (AP)
```

You can **only sit on one edge of the triangle**.

---

## ✅ **9. Practical Tips**

* **Network partitions are inevitable**, so **Partition Tolerance (P)** is a must in most systems.
* Decide between **Consistency (C)** and **Availability (A)** based on your business use case.
* **Use tunable consistency** (like in Cassandra, DynamoDB) to balance trade-offs dynamically.
* Consider **idempotent operations**, **retries**, **compensation** in AP systems.

---

## ✅ **10. Summary**

| Question                                 | If Yes → Choose |
| ---------------------------------------- | --------------- |
| Is data correctness critical?            | CP              |
| Is system responsiveness more important? | AP              |
| Is network always reliable (unlikely)?   | CA              |

---