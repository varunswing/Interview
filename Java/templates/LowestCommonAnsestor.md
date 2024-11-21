Here’s the complete code for **three different approaches** to finding the **Longest Common Ancestor (LCA)** of two nodes in a binary tree:

### 1. **Naive Recursive Approach**

```java
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

class LCA {
    public TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        
        // If the root is one of the nodes, return root
        if (root == p || root == q) {
            return root;
        }
        
        // Recursively find the LCA in left and right subtrees
        TreeNode leftLCA = findLCA(root.left, p, q);
        TreeNode rightLCA = findLCA(root.right, p, q);
        
        // If both left and right are non-null, root is the LCA
        if (leftLCA != null && rightLCA != null) {
            return root;
        }
        
        // Otherwise, return the non-null child
        return (leftLCA != null) ? leftLCA : rightLCA;
    }
}
```

### 2. **Binary Lifting Approach**

```java
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

class LCA {
    private static final int MAX = 20; // Maximum power for binary lifting (log(N))
    private int[][] parent;
    private int[] depth;

    public LCA(TreeNode root, int n) {
        parent = new int[n + 1][MAX]; // Store the 2^i-th ancestor for each node
        depth = new int[n + 1];
        dfs(root, 0, -1);
    }

    private void dfs(TreeNode node, int d, int p) {
        if (node == null) return;
        depth[node.val] = d;
        parent[node.val][0] = p;
        
        for (int i = 1; i < MAX; i++) {
            if (parent[node.val][i - 1] != -1) {
                parent[node.val][i] = parent[parent[node.val][i - 1]][i - 1];
            }
        }
        
        dfs(node.left, d + 1, node.val);
        dfs(node.right, d + 1, node.val);
    }

    public int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Bring u and v to the same depth
        for (int i = MAX - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = parent[u][i];
            }
        }

        if (u == v) return u;

        // Binary lifting
        for (int i = MAX - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        return parent[u][0]; // The parent of u or v is the LCA
    }
}
```

### 3. **Euler Tour + RMQ Approach**

For **Euler Tour + RMQ**, we need to do the following:
- Perform an Euler Tour and store the first occurrence of each node.
- Use a **Segment Tree** or **Sparse Table** to perform Range Minimum Query (RMQ) efficiently.

Below is the basic code for **Euler Tour + RMQ using Segment Tree**.

```java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

class LCA {
    private List<Integer> eulerTour;
    private List<Integer> firstOccurrence;
    private int[] depth;
    private SegmentTree segmentTree;
    
    public LCA(TreeNode root) {
        eulerTour = new ArrayList<>();
        firstOccurrence = new ArrayList<>();
        depth = new int[100]; // Adjust according to the tree size
        dfs(root, 0, -1);
        segmentTree = new SegmentTree(depth);
    }
    
    private void dfs(TreeNode node, int d, int parent) {
        if (node == null) return;
        
        depth[node.val] = d;
        eulerTour.add(node.val);
        firstOccurrence.add(eulerTour.size() - 1);
        
        if (node.left != null) {
            dfs(node.left, d + 1, node.val);
            eulerTour.add(node.val);
        }
        if (node.right != null) {
            dfs(node.right, d + 1, node.val);
            eulerTour.add(node.val);
        }
    }

    public int getLCA(int u, int v) {
        int l = Math.min(firstOccurrence.get(u), firstOccurrence.get(v));
        int r = Math.max(firstOccurrence.get(u), firstOccurrence.get(v));
        int index = segmentTree.rangeMinimumQuery(l, r);
        return eulerTour.get(index);
    }
    
    class SegmentTree {
        private int[] segmentTree;
        
        public SegmentTree(int[] arr) {
            segmentTree = new int[4 * arr.length];
            build(arr, 0, 0, arr.length - 1);
        }
        
        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                segmentTree[node] = start;
            } else {
                int mid = (start + end) / 2;
                build(arr, 2 * node + 1, start, mid);
                build(arr, 2 * node + 2, mid + 1, end);
                segmentTree[node] = (arr[segmentTree[2 * node + 1]] <= arr[segmentTree[2 * node + 2]]) 
                        ? segmentTree[2 * node + 1] 
                        : segmentTree[2 * node + 2];
            }
        }
        
        public int rangeMinimumQuery(int l, int r) {
            return rmq(0, 0, eulerTour.size() - 1, l, r);
        }
        
        private int rmq(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return -1;
            if (l <= start && end <= r) return segmentTree[node];
            int mid = (start + end) / 2;
            int left = rmq(2 * node + 1, start, mid, l, r);
            int right = rmq(2 * node + 2, mid + 1, end, l, r);
            if (left == -1) return right;
            if (right == -1) return left;
            return (depth[eulerTour.get(left)] <= depth[eulerTour.get(right)]) ? left : right;
        }
    }
}
```

---

### **Explanation of Each Approach**

1. **Naive Recursive Approach**:
   - This approach traverses the tree from the root and returns the LCA when both nodes are found in different subtrees.
   - **Time Complexity**: \( O(N) \) per query.
   - **Space Complexity**: \( O(N) \) (Recursive stack).

2. **Binary Lifting**:
   - This approach preprocesses the tree to calculate \( 2^i \)-th ancestors for each node and uses binary lifting to efficiently answer LCA queries.
   - **Preprocessing Time Complexity**: \( O(N \log N) \).
   - **Query Time Complexity**: \( O(\log N) \).
   - **Space Complexity**: \( O(N \log N) \).

3. **Euler Tour + RMQ**:
   - The tree is traversed using an Euler Tour, and then an RMQ is used to find the LCA. A Segment Tree is used to efficiently find the minimum depth in the range.
   - **Preprocessing Time Complexity**: \( O(N) \) for Euler Tour and Segment Tree build.
   - **Query Time Complexity**: \( O(\log N) \).
   - **Space Complexity**: \( O(N) \).

---

### **Which to Use?**
- **Naive Approach**: Works well for small trees or single queries.
- **Binary Lifting**: Ideal for large trees and multiple queries after preprocessing.
- **Euler Tour + RMQ**: Efficient for offline queries where multiple LCA queries need to be answered after preprocessing.

Let me know if you need further clarification on any of these methods!