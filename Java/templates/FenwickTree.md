The **Fenwick Tree** (also known as a **Binary Indexed Tree** or BIT) is a data structure used for efficiently performing operations on an array, such as:

1. **Point Updates**: Modify the value of an element at a specific index.
2. **Prefix Queries**: Compute the sum of elements from the beginning of the array up to a given index.

---

### **Key Characteristics**
- The Fenwick Tree has an internal structure of size \( n+1 \), where \( n \) is the size of the array.
- It allows operations in \( O(\log n) \) time, making it much faster than a naive \( O(n) \) approach for range operations.

---

### **Formulas**
1. **Updating an index**:  
   \[
   \text{index} = \text{index} + (\text{index} \& -\text{index})
   \]
2. **Getting a prefix sum**:  
   \[
   \text{index} = \text{index} - (\text{index} \& -\text{index})
   \]

---

### **Implementation in Java**

#### **Building the Fenwick Tree**
```java
class FenwickTree {
    private int[] tree;

    public FenwickTree(int size) {
        tree = new int[size + 1]; // 1-based indexing
    }

    // Point update: Add value to the given index
    public void update(int index, int value) {
        while (index < tree.length) {
            tree[index] += value;
            index += index & -index; // Move to the next index
        }
    }

    // Query: Get the prefix sum from 1 to index
    public int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & -index; // Move to the parent index
        }
        return sum;
    }

    // Range sum: Get the sum between left and right (inclusive)
    public int rangeSum(int left, int right) {
        return query(right) - query(left - 1);
    }
}
```

---

### **Example Usage**
```java
public static void main(String[] args) {
    int[] arr = {0, 3, 2, -1, 6, 5, 4, -3, 3, 7, 2}; // 1-based indexing (0 is a dummy)

    FenwickTree fenwickTree = new FenwickTree(arr.length - 1);

    // Build the tree by updating each index
    for (int i = 1; i < arr.length; i++) {
        fenwickTree.update(i, arr[i]);
    }

    // Query the prefix sum
    System.out.println("Sum of first 5 elements: " + fenwickTree.query(5)); // Output: 15

    // Range sum
    System.out.println("Sum of elements from 3 to 7: " + fenwickTree.rangeSum(3, 7)); // Output: 11

    // Point update
    fenwickTree.update(4, 2); // Add 2 to the 4th element
    System.out.println("After update, sum of first 5 elements: " + fenwickTree.query(5)); // Output: 17
}
```

---

### **Explanation of Operations**

#### **Point Update**
When you update a value at a specific index, you update all relevant nodes in the tree that depend on this index. The next index to update is determined using:
\[
\text{index} = \text{index} + (\text{index} \& -\text{index})
\]

#### **Prefix Query**
When querying the prefix sum, you traverse the tree backward, summing up all the values that contribute to the prefix sum. The parent index is determined using:
\[
\text{index} = \text{index} - (\text{index} \& -\text{index})
\]

#### **Range Query**
A range sum is computed using the prefix sum:
\[
\text{RangeSum}(L, R) = \text{Query}(R) - \text{Query}(L-1)
\]

---

### **Time Complexity**
- **Update**: \( O(\log n) \)
- **Query**: \( O(\log n) \)
- **Space**: \( O(n) \)

---

### **When to Use Fenwick Tree**
- Efficiently handle frequent updates and prefix/range sum queries.
- Alternative to Segment Trees for sum operations, with simpler implementation and slightly lower memory overhead.

Let me know if you'd like more advanced examples or comparisons with Segment Trees!