A **Sparse Table** is a data structure that is used for answering range queries in constant time, specifically for **idempotent operations** like **minimum** and **maximum** over a range. It is particularly effective when you only need to perform **range queries** and the array is **immutable** (i.e., no updates after the table is built).

### **Key Features**
1. **Preprocessing**: The Sparse Table requires an initial preprocessing step to build the table. This takes **O(n log n)** time.
2. **Query Time**: Once the table is built, the query time is **O(1)**, which is optimal for range queries.
3. **Updates**: The Sparse Table is not efficient for handling updates. Once built, the table is immutable, which makes it unsuitable for dynamic scenarios where the array elements change frequently.

### **Use Case**
Sparse Table is typically used for range minimum query (RMQ) or range maximum query (RMQ), where the operation is **idempotent**, meaning that the result of applying the operation multiple times does not change after the first application (e.g., `min(min(x, y), z)` is the same as `min(x, y, z)`).

### **Construction**
The Sparse Table is built such that for each element of the array, it stores the result of applying the query (e.g., minimum) for every possible range of length \(2^k\). In other words, the table is built for all intervals of size 1, 2, 4, 8, etc., and the results are precomputed.

### **Sparse Table Construction Algorithm**
1. **Precompute** the minimums (or maximums) for all ranges of length \(2^k\) for each element in the array.
2. **Use dynamic programming** to fill the table by using the following recurrence relation:
   - `st[i][j] = min(st[i][j-1], st[i + 2^(j-1)][j-1])` (for RMQ)
   - This means that the minimum (or maximum) in the range starting at `i` and of length \(2^j\) can be derived from two smaller ranges of length \(2^{j-1}\).

### **Sparse Table Code (Range Minimum Query)**
Let's look at an example implementation of a Sparse Table for Range Minimum Query (RMQ):

```java
class SparseTable {
    int[][] st;   // Sparse Table
    int[] log;    // Array to store the logarithms for fast access
    int n;        // Length of the array

    // Constructor to initialize the Sparse Table for Range Minimum Query (RMQ)
    public SparseTable(int[] arr) {
        n = arr.length;
        log = new int[n + 1];
        // Calculate the logarithms
        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }
        // Initialize the Sparse Table
        st = new int[n][log[n] + 1];
        build(arr);
    }

    // Function to build the Sparse Table
    private void build(int[] arr) {
        // Initialize st[i][0] to be arr[i] for all i
        for (int i = 0; i < n; i++) {
            st[i][0] = arr[i];
        }

        // Fill the Sparse Table using dynamic programming
        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 0; i + (1 << j) - 1 < n; i++) {
                // st[i][j] stores the minimum in the range [i, i + 2^j - 1]
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    // Function to get the minimum value in the range [L, R]
    public int query(int L, int R) {
        // Calculate the largest power of 2 that fits in the range [L, R]
        int length = R - L + 1;
        int j = log[length];
        // Return the minimum in the range [L, R]
        return Math.min(st[L][j], st[R - (1 << j) + 1][j]);
    }
}

public class SparseTableExample {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 7, 9, 11, 4, 5};
        SparseTable sparseTable = new SparseTable(arr);

        // Query for the minimum in the range [1, 4]
        int result = sparseTable.query(1, 4);
        System.out.println("Minimum in range [1, 4]: " + result);  // Output: 2

        // Query for the minimum in the range [0, 7]
        result = sparseTable.query(0, 7);
        System.out.println("Minimum in range [0, 7]: " + result);  // Output: 1
    }
}
```

### **Explanation of Code**

1. **Logarithms Array (`log`)**: The `log[]` array stores the logarithm values for fast computation of the largest power of 2 less than or equal to the length of any range. This helps in determining the largest sub-range that fits within a query range.
  
2. **Sparse Table Array (`st`)**: The `st[][]` array stores the precomputed minimum values for all possible ranges in powers of 2. The value at `st[i][j]` represents the minimum value in the range starting at index `i` and with length \(2^j\).
  
3. **Build Function**: The `build()` function initializes the first column (`st[i][0]`), which represents ranges of size 1 (just individual elements). Then, using dynamic programming, it fills up the rest of the table by merging the results of smaller ranges (e.g., using two ranges of size \(2^{j-1}\) to compute a range of size \(2^j\)).

4. **Query Function**: The `query()` function answers a range minimum query in constant time. It finds the largest power of 2 that fits within the range `[L, R]` and then queries two intervals from the table to find the minimum.

### **Time Complexity**
- **Build**: \( O(n \log n) \), where \( n \) is the length of the array, because we need to process each range size for each element.
- **Query**: \( O(1) \) for a range minimum query since we only look up two values in the table.
  
### **Space Complexity**
- **Space**: \( O(n \log n) \) due to the storage needed for the sparse table and logarithms array.

### **Applications**
- **Range Queries**: Particularly suited for problems involving range minimum queries (RMQ), range maximum queries, and similar problems where the array is static and no updates are required.
- **Immutable Data**: Sparse Tables work best when the array does not change, i.e., when there are no updates to the array.

### **Limitations**
- **Updates**: Sparse Table does not efficiently support dynamic updates. It is a static structure, meaning once it is built, you cannot modify the array elements without rebuilding the table.

Sparse Tables are a great choice for problems where you need fast range queries and the data is static or doesn't change frequently.