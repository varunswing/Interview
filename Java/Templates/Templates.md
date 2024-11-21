# JAVA Templates

## Here are some famous & frequently used JAVA templates:

---

### 1. **Check Prime**

**Code:**  
```java
public static boolean isPrime(int n) {
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    for (int i = 5; i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) return false;
    }
    return true;
}
```

**Explanation:**  
This function checks if a number `n` is prime:
1. Numbers ≤ 1 are not prime.
2. Numbers 2 and 3 are prime.
3. Numbers divisible by 2 or 3 are not prime.
4. It checks divisors from 5 up to √n, skipping multiples of 2 and 3 for efficiency.

---

### 2. **Find GCD**

**Code:**  
```java
public static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
}
```

**Explanation:**  
This function calculates the greatest common divisor (GCD) of two numbers using the **Euclidean algorithm**. If `b` is 0, `a` is the GCD; otherwise, it recursively computes `gcd(b, a % b)`.

**How the Euclidean Algorithm Works**
If you have two numbers \( a \) and \( b \):
1. Divide \( a \) by \( b \), and find the remainder \( r \).  
   \( r = a \mod b \)
2. Replace \( a \) with \( b \), and \( b \) with \( r \).
3. Repeat until \( b = 0 \). At this point, \( a \) is the GCD of the original two numbers.

---

### 3. **Find LCM**

**Code:**  
```java
public static int lcm(int a, int b) {
    return (a*b)/gcd(a, b);
}
```

**Explanation:**  
This function computes the least common multiple (LCM) of two numbers using the formula:  
\[ \text{LCM}(a, b) = \frac{a \times b}{\text{GCD}(a, b)} \]  
It reuses the GCD function.

---

### 4. **Binary Search**

**Code:**  
```java
public static int binarySearch(int[] arr, int target) {
    int low = 0, high = arr.length - 1;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) low = mid + 1;
        else high = mid - 1;
    }
    return -1;
}
```

**Explanation:**  
Performs a binary search to find the `target` in a sorted array:
1. Uses two pointers `low` and `high`.
2. Calculates the middle index `mid` and compares the target with the middle element.
3. Adjusts `low` or `high` to narrow the search range.
4. Returns the index of the `target` or `-1` if not found.

---

### 5. **Reverse a String**

**Code:**  
```java
public static String reverseString(String s) {
    return new StringBuilder(s).reverse().toString();
}
```

**Explanation:**  
This function reverses a given string using Java's `StringBuilder` class, which provides an efficient method `reverse()` to reverse characters in a string.

---

### 6. **Merge Two Sorted Arrays**

**Code:**  
```java
public static int[] mergeArrays(int[] arr1, int[] arr2) {
    int[] merged = new int[arr1.length + arr2.length];
    int i = 0, j = 0, k = 0;
    while (i < arr1.length && j < arr2.length) {
        merged[k++] = arr1[i] < arr2[j] ? arr1[i++] : arr2[j++];
    }
    while (i < arr1.length) merged[k++] = arr1[i++];
    while (j < arr2.length) merged[k++] = arr2[j++];
    return merged;
}
```

**Explanation:**  
Merges two sorted arrays into one sorted array:
1. Uses two pointers `i` and `j` to traverse `arr1` and `arr2`.
2. Adds the smaller element to the `merged` array.
3. Appends remaining elements from either array after one is exhausted.

---

### 7. **Fibonacci with Memoization**

**Code:**  
```java
private static Map<Integer, Long> memo = new HashMap<>();

public static long fib(int n) {
    if (n <= 1) return n;
    if (!memo.containsKey(n)) {
        memo.put(n, fib(n - 1) + fib(n - 2));
    }
    return memo.get(n);
}
```

**Explanation:**  
Calculates the nth Fibonacci number using **memoization** to store results of previous computations:
1. Avoids recalculating Fibonacci values for the same input.
2. Reduces the time complexity from \(O(2^n)\) to \(O(n)\).

---

### 8. **Factorial**

**Code:**  
```java
public static long factorial(int n) {
    long result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}
```

**Explanation:**  
Calculates the factorial of a number `n` using an iterative approach.

---

### 9. **Check Palindrome (String)**

**Code:**  
```java
public static boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
        if (s.charAt(left++) != s.charAt(right--)) return false;
    }
    return true;
}
```

**Explanation:**  
Checks if a string is a palindrome by comparing characters from both ends.

---

### 10. **Breadth-First Search (BFS)**

**10.1 BFS for a 2D Array (Grid)**
```java
public static void bfs2D(int[][] grid, int startX, int startY) {
    int rows = grid.length, cols = grid[0].length;
    boolean[][] visited = new boolean[rows][cols];
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{startX, startY});
    visited[startX][startY] = true;

    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int x = current[0], y = current[1];
        System.out.println("Visited: (" + x + ", " + y + ")");

        for (int[] dir : directions) {
            int newX = x + dir[0], newY = y + dir[1];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                visited[newX][newY] = true;
                queue.add(new int[]{newX, newY});
            }
        }
    }
}
```

---
**10.2 BFS for Iterative Tree Traversal**
```java
public static void bfsTree(TreeNode root) {
    if (root == null) return;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        System.out.println("Visited: " + node.val);

        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
    }
}
```

---

**10.3 BFS for Graph with Adjacency List**
```java
public static void bfsGraph(int start, List<List<Integer>> graph, int n) {
    boolean[] visited = new boolean[n];
    Queue<Integer> queue = new LinkedList<>();
    queue.add(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
        int node = queue.poll();
        System.out.println("Visited: " + node);

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.add(neighbor);
            }
        }
    }
}
```

---

### 11. **Depth-First Search (DFS)**

**11.1 DFS for a 2D Array (Grid)**
```java
public static void dfs2D(int[][] grid, int x, int y, boolean[][] visited) {
    int rows = grid.length, cols = grid[0].length;
    if (x < 0 || x >= rows || y < 0 || y >= cols || visited[x][y]) return;

    visited[x][y] = true;
    System.out.println("Visited: (" + x + ", " + y + ")");
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    for (int[] dir : directions) {
        dfs2D(grid, x + dir[0], y + dir[1], visited);
    }
}
```

---

**11.2 DFS for Iterative Tree Traversal**
#### Iterative DFS (Using Stack):
```java
public static void dfsTree(TreeNode root) {
    if (root == null) return;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        System.out.println("Visited: " + node.val);

        if (node.right != null) stack.push(node.right); // Right first
        if (node.left != null) stack.push(node.left);   // Left next
    }
}
```

---

**11.3 DFS for Graph with Adjacency List**
#### Recursive DFS:
```java
public static void dfsGraph(int node, List<List<Integer>> graph, boolean[] visited) {
    visited[node] = true;
    System.out.println("Visited: " + node);

    for (int neighbor : graph.get(node)) {
        if (!visited[neighbor]) {
            dfsGraph(neighbor, graph, visited);
        }
    }
}
```

#### Iterative DFS (Using Stack):
```java
public static void dfsGraphIterative(int start, List<List<Integer>> graph, int n) {
    boolean[] visited = new boolean[n];
    Stack<Integer> stack = new Stack<>();
    stack.push(start);

    while (!stack.isEmpty()) {
        int node = stack.pop();
        if (!visited[node]) {
            visited[node] = true;
            System.out.println("Visited: " + node);

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                }
            }
        }
    }
}
```

---

#### Example Data Structures for Testing:

**TreeNode Definition**
```java
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}
```

**Graph as Adjacency List**
```java
List<List<Integer>> graph = new ArrayList<>();
int n = 5;
for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

// Add edges
graph.get(0).add(1);
graph.get(0).add(2);
graph.get(1).add(3);
graph.get(1).add(4);
```

---

**Explanation:**  
Performs **Depth-First Search** (DFS) on a graph using recursion:
1. Marks a node as visited.
2. Recursively explores unvisited neighbors.

---

### 12. **Two-Pointer Technique for Sorted Array**

**Code:**  
```java
public static boolean hasPairWithSum(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left < right) {
        int sum = arr[left] + arr[right];
        if (sum == target) return true;
        if (sum < target) left++;
        else right--;
    }
    return false;
}
```

**Explanation:**  
Checks if there exists a pair of numbers in a sorted array whose sum equals the target:
1. Uses two pointers moving inward based on the sum.
2. Efficient with a time complexity of \(O(n)\).

---

### 13. **Find Maximum in Array**

**Code:**  
```java
public static int findMax(int[] arr) {
    int max = Integer.MIN_VALUE;
    for (int num : arr) {
        if (num > max) max = num;
    }
    return max;
}
```

**Explanation:**  
Finds the maximum element in an array by iterating through all elements.

---

### 14. **Quick Sort**

**Code:**  
```java
public static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
        if (arr[j] <= pivot) {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    return i + 1;
}
```

**Explanation:**  
Implements **Quick Sort**:
1. Picks a pivot and partitions the array.
2. Recursively sorts the left and right partitions.

---

### 15. **Matrix Transpose**

**Code:**  
```java
public static int[][] transposeMatrix(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[][] transposed = new int[cols][rows];


    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            transposed[j][i] = matrix[i][j];
        }
    }
    return transposed;
}
```

**Explanation:**  
Creates a transpose of a given matrix by swapping rows and columns.

--- 

### 16. **All Divisor**

**Code:**  
```java
public static List<Integer> findDivisors(int n) {
    List<Integer> divisors = new ArrayList<>();
    for (int i = 1; i * i <= n; i++) {
        if (n % i == 0) {
            divisors.add(i); // Add the smaller divisor
            if (i != n / i) {
                divisors.add(n / i); // Add the paired larger divisor
            }
        }
    }
    Collections.sort(divisors); // Optional: To get divisors in sorted order
    return divisors;
}
```



**Explanation**
1. Loop through numbers \( 1 \) to \( \sqrt{n} \):
   - If \( n \% i == 0 \), \( i \) is a divisor.
   - Compute \( n/i \) as the paired divisor.
2. Add both \( i \) and \( n/i \) to the list.
3. Ensure no duplicates (e.g., when \( i = \sqrt{n} \), add \( i \) only once).

---

### 17 **Median**

**Code**
```java
import java.util.Arrays;

class Median {
    public double findMedianUnsortedArray(int[] nums) {
        Arrays.sort(nums);  
        int n = nums.length;
        if (n % 2 == 1) {
            return nums[n / 2];  
        } else {
            return (nums[n / 2 - 1] + nums[n / 2]) / 2.0;  
        }
    }
}
```