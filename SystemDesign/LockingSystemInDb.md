# 🚀 Locking System in Database: Pessimistic vs Optimistic

---

### 🔥 Pessimistic Locking:
- **Assumes conflicts will happen.**
- **Locks data immediately** when reading or writing.
- Other transactions **must wait** until the lock is released.
- Common in **high-contention** systems.

✅ Good for:  
- Heavy write conflicts  
- Critical financial operations

**Example:**  
> `SELECT * FROM users WHERE id=5 FOR UPDATE;` → locks row immediately.

---

### ⚡ Optimistic Locking:
- **Assumes conflicts are rare.**
- **No lock while reading.**  
- When updating, it **checks if data changed** (usually using a version number or timestamp).
- If changed, **transaction fails** and must retry.

✅ Good for:  
- Read-heavy, less conflict-prone systems  
- Better performance in low contention

**Example:**  
> 1. Read row with version `v1`.  
> 2. Try to update with `WHERE version=v1`.  
> 3. If no row updated → data changed → retry.

---

### 🎯 Quick Comparison:

| | Pessimistic Locking | Optimistic Locking |
|:---|:---|:---|
| **When** | Before conflict | After detecting conflict |
| **Locking** | Immediate | During commit |
| **Performance** | Slower under low conflict | Faster under low conflict |
| **Use case** | High contention | Low contention |

---

Awesome! Here’s a **quick and clear Java/Spring Boot** example for both **Pessimistic** and **Optimistic** locking:

---

### 🔒 Pessimistic Locking Example:

```java
@Entity
public class User {
    @Id
    private Long id;
    private String name;
}

public interface UserRepository extends JpaRepository<User, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findByIdForUpdate(@Param("id") Long id);
}
```
**Explanation:**  
- Acquires **DB lock** immediately.
- Other updates will **block** until this one finishes.

---

### ⚡ Optimistic Locking Example:

```java
@Entity
public class User {
    @Id
    private Long id;
    private String name;

    @Version
    private Long version; // Auto-managed by JPA
}

public interface UserRepository extends JpaRepository<User, Long> {
}
```
**Usage:**
```java
User user = userRepository.findById(1L).orElseThrow();
// Do some changes
user.setName("New Name");
userRepository.save(user); // JPA will check the version automatically
```
**Explanation:**  
- No DB lock while reading.
- During `save()`, **version** is checked.
- If someone else updated in between → **OptimisticLockException** is thrown.

---

🔵 **In short:**
- `@Lock(PESSIMISTIC_WRITE)` → For **pessimistic**.
- `@Version` → For **optimistic**.

---

Perfect! Here's a **real-world smart combo**:

---

### 🏦 Real-World Example: **Banking Transaction System**

**Problem:**  
- Money transfer between two accounts.
- Must **guarantee** no double withdrawal.
- Must also be **fast** under normal load.

---

### 💥 Smart Strategy:
| Operation | Locking Type | Why |
|:---|:---|:---|
| Withdraw money | **Pessimistic Locking** | Critical section: **lock immediately** to prevent double deduction. |
| Update user profile (non-money data) | **Optimistic Locking** | Changes are rare: **no need to block**. Just retry if collision. |

---

### 🔥 Flow:
1. **Withdrawal (`Account` Entity)**
   ```java
   @Lock(LockModeType.PESSIMISTIC_WRITE)
   Account findAccountForUpdate(Long id);
   ```
   - Lock account row immediately.
   - Deduct amount safely.
   
2. **Profile Update (`UserProfile` Entity)**
   ```java
   @Version
   private Long version;
   ```
   - Read and edit user email, address, etc.
   - Save without locking DB.
   - Retry if someone else changed at the same time.

---

### 🎯 Why this is smart?
- **Critical operations** (like money) are *safely locked* → **No loss**.
- **Non-critical operations** are *fast and scalable* → **Better user experience**.

---

