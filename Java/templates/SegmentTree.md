A **Segment Tree** is a data structure that allows efficient querying and updating of intervals or ranges of data. It is particularly useful for problems where you need to perform range queries (like sum, min, max) and updates to elements, all in logarithmic time.

The **Segment Tree** is a binary tree where each node represents an interval (or range) of elements in the array. The tree can be used to:
1. **Query** for information about a range of elements (e.g., sum, minimum, maximum).
2. **Update** an element in the array and efficiently propagate changes in the tree.

### **Key Operations**
1. **Build**: Construct the segment tree from the array.
2. **Query**: Get the result for a range of elements (like sum, minimum, maximum).
3. **Update**: Modify an element in the array, and update the tree accordingly.

### **Structure**
- **Leaf nodes** represent individual elements of the array.
- **Internal nodes** represent the aggregate of the values in the segments of the array. For example, if you're building a segment tree for range sum, each internal node stores the sum of its child nodes.

### **Common Use Cases**
- **Range Sum Query**
- **Range Minimum Query (RMQ)**
- **Range Maximum Query**
- **Range GCD/LCM Query**
- **Point Update**

### **Building a Segment Tree**

For a given array of size `n`, the segment tree will have up to `4 * n` nodes to ensure there is enough space for all possible segment combinations. Each node stores information about a segment or range of the array.

### **Example Problem: Range Sum Query**
Let’s consider a simple example where we need to construct a segment tree for range sum queries.

Given an array `arr[] = [1, 3, 5, 7, 9, 11]`, we want to:
- Build the segment tree.
- Query the sum of the elements in a range, e.g., the sum of elements between index 1 and 3.
- Update an element, e.g., update index 2 to 6, and then query again.

### **Segment Tree Code Example (Range Sum Query)**

```java
class SegmentTree {
    int[] tree;
    int[] arr;

    // Constructor to build the Segment Tree
    public SegmentTree(int[] arr) {
        this.arr = arr;
        int n = arr.length;
        // Segment tree size is 4 times the array size
        tree = new int[4 * n];
        build(0, 0, n - 1);
    }

    // Function to build the tree
    private void build(int node, int start, int end) {
        if (start == end) {
            // Leaf node will hold the value of the array element
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            // Recursively build the segment tree
            build(2 * node + 1, start, mid);
            build(2 * node + 2, mid + 1, end);
            // Internal node will store the sum of the left and right children
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    // Function to get the sum of elements in the range [L, R]
    public int query(int node, int start, int end, int L, int R) {
        // If the range [L, R] completely overlaps with the segment [start, end]
        if (R < start || end < L) {
            return 0; // No overlap
        }
        if (L <= start && end <= R) {
            return tree[node]; // Total overlap
        }
        // Partial overlap: query the left and right children
        int mid = (start + end) / 2;
        int left = query(2 * node + 1, start, mid, L, R);
        int right = query(2 * node + 2, mid + 1, end, L, R);
        return left + right;
    }

    // Function to update the value at index idx
    public void update(int node, int start, int end, int idx, int value) {
        if (start == end) {
            // Leaf node will hold the updated value
            arr[idx] = value;
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;
            if (start <= idx && idx <= mid) {
                update(2 * node + 1, start, mid, idx, value);
            } else {
                update(2 * node + 2, mid + 1, end, idx, value);
            }
            // After updating, recalculate the sum for this node
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
}

public class RangeSumQuery {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree segmentTree = new SegmentTree(arr);

        // Query for the sum of elements in the range [1, 3]
        int result = segmentTree.query(0, 0, arr.length - 1, 1, 3);
        System.out.println("Sum of elements in range [1, 3]: " + result);  // Output: 15

        // Update the value at index 2
        segmentTree.update(0, 0, arr.length - 1, 2, 6);  // Update 5 to 6
        result = segmentTree.query(0, 0, arr.length - 1, 1, 3);
        System.out.println("Sum of elements in range [1, 3] after update: " + result);  // Output: 16
    }
}
```

### **Explanation of Code**

- **`build()`**: This function constructs the segment tree by dividing the array into segments. It recursively builds the tree such that each internal node stores the sum of its children.
  
- **`query()`**: This function is used to perform a range query. Given a range `[L, R]`, it recursively checks for three conditions:
  - If the range does not overlap with the segment, it returns 0.
  - If the range completely overlaps with the segment, it returns the value stored at the node.
  - If the range partially overlaps, it queries both the left and right child nodes and sums their results.

- **`update()`**: This function updates the value at a specific index and then updates all affected internal nodes to reflect the new value. The function updates the leaf node and then moves upward to update the sum at each internal node.

### **Time Complexity**
- **Build**: \( O(N) \), where \( N \) is the number of elements in the array.
- **Query**: \( O(\log N) \), where \( N \) is the number of elements in the array.
- **Update**: \( O(\log N) \), for a single update.

### **Space Complexity**
- The space complexity is \( O(N) \) due to the storage required for the segment tree.

### **Applications of Segment Trees**
- **Range Queries**: Such as sum, minimum, or maximum over a range.
- **Point Updates**: Update an element and propagate the change in the tree.
- **Interval Merging**: Merge intervals in various applications like interval scheduling problems.
- **Lazy Propagation**: Allows for efficient range updates in segment trees.

### **Variants**
- **Lazy Segment Tree**: For efficient range updates (such as range addition).
- **Segment Tree for Minimum/Maximum**: Instead of sum, store the minimum or maximum value in each segment.
  
Segment trees are highly efficient for range query problems, especially when updates and queries need to be handled in logarithmic time.