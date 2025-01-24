# DSA

## Que 1. Graph (Flight delay)

A network of flights contains flight_nodes number of flights denoted by [1, 2, .....flight nodes]. There is a list of dependencies among flights denoted by the arrays flight_from[], flights leaving a city, and flight to[], flights arriving in a city. Each pair (flight from[i), flight to[i]) denotes that flight from[i] depends on flight to[i] and must depart only after flight to[i] has landed. If a flight is delayed, all the flights dependent on this flight and their corresponding dependencies are also delayed.

Given a list of k initially delayed flights and the network as described, find the list of all delayed flights. Return the list sorted in increasing order of flight numbers.

Example

Consider flight_nodes = 4, and the number of dependencies m = 2.

flight from [4,3]

flight to [1, 2]

The number of delayed flights k = 2, and delayed = [1, 3].



Flight 1 is delayed.

* Flight 4 depends on flight 1, so flight 4 is delayed.


* Flight 3 is delayed.

* There are no flights dependent on flight 3.


Return the sorted array of delayed flight [1,3,4]

give the best solution and approach with the data structure technique in java

To solve this problem efficiently, we can model it as a **graph problem**. Here’s the step-by-step solution approach:

### **Approach**
1. **Model the Problem as a Directed Graph**:
   - Treat each flight as a node.
   - Treat each dependency `(flight_from[i], flight_to[i])` as a directed edge from `flight_to[i]` to `flight_from[i]`. This signifies that the flight `flight_from[i]` can only depart after `flight_to[i]` has landed.

2. **Graph Traversal**:
   - Use **Breadth-First Search (BFS)** or **Depth-First Search (DFS)** to propagate delays through the graph.
   - Starting from the initially delayed flights (`k` delayed flights), traverse the graph to find all reachable nodes.

3. **Data Structures**:
   - Use an **adjacency list** to represent the graph, as it is efficient for sparse graphs.
   - Use a **queue** for BFS traversal to determine the set of all delayed flights.

4. **Sorting**:
   - Store all delayed flights in a `Set` to ensure uniqueness, then convert it to a list and sort it.

### **Time Complexity**
- Building the graph: \(O(m)\), where \(m\) is the number of dependencies.
- BFS/DFS traversal: \(O(n + m)\), where \(n\) is the number of flights.
- Sorting the result: \(O(d \log d)\), where \(d\) is the number of delayed flights.
- **Overall Complexity**: \(O(n + m + d \log d)\).

---

### **Java Implementation**
```java
import java.util.*;

public class FlightDelays {
    public static List<Integer> findDelayedFlights(int flightNodes, int[] flightFrom, int[] flightTo, int[] delayed) {
        // Create adjacency list for the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= flightNodes; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        // Build the graph from the dependencies
        for (int i = 0; i < flightFrom.length; i++) {
            graph.get(flightTo[i]).add(flightFrom[i]);
        }
        
        // Set to store all delayed flights
        Set<Integer> delayedFlights = new HashSet<>();
        
        // Queue for BFS traversal
        Queue<Integer> queue = new LinkedList<>();
        for (int flight : delayed) {
            queue.add(flight);
            delayedFlights.add(flight);
        }
        
        // Perform BFS
        while (!queue.isEmpty()) {
            int currentFlight = queue.poll();
            for (int dependentFlight : graph.get(currentFlight)) {
                if (!delayedFlights.contains(dependentFlight)) {
                    delayedFlights.add(dependentFlight);
                    queue.add(dependentFlight);
                }
            }
        }
        
        // Convert to a sorted list
        List<Integer> result = new ArrayList<>(delayedFlights);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        int flightNodes = 4;
        int[] flightFrom = {4, 3};
        int[] flightTo = {1, 2};
        int[] delayed = {1, 3};
        
        List<Integer> delayedFlights = findDelayedFlights(flightNodes, flightFrom, flightTo, delayed);
        System.out.println(delayedFlights); // Output: [1, 3, 4]
    }
}
```

---

### **Explanation of the Code**

1. **Graph Construction**:
   - An adjacency list is created using a `Map<Integer, List<Integer>>`. Each flight is a key, and its value is a list of flights that depend on it.

2. **Initial Queue Setup**:
   - All initially delayed flights are added to a `Queue` and a `Set`.

3. **BFS Traversal**:
   - For each delayed flight, mark all its dependent flights as delayed and add them to the queue.

4. **Sorting**:
   - After the traversal, convert the `Set` of delayed flights to a `List` and sort it in ascending order.

---

### **Test Cases**
#### Input:
```java
int flightNodes = 5;
int[] flightFrom = {5, 4, 3};
int[] flightTo = {1, 2, 1};
int[] delayed = {1};
```
#### Output:
```plaintext
[1, 3, 4, 5]
```

#### Input:
```java
int flightNodes = 6;
int[] flightFrom = {4, 5, 6};
int[] flightTo = {2, 3, 2};
int[] delayed = {2};
```
#### Output:
```plaintext
[2, 4, 6]
```

To analyze the space complexity of the solution, let's consider the memory used by various components of the algorithm:

---

### **Components Contributing to Space Complexity**

1. **Graph Representation (Adjacency List)**:
   - We store the graph as a `Map<Integer, List<Integer>>`. 
   - Each node (flight) is a key in the map, and the value is a list of dependent flights.
   - For \(n\) nodes (flights) and \(m\) edges (dependencies), the adjacency list requires:
     - **Keys**: \(O(n)\) for the map keys.
     - **Edges**: \(O(m)\) for the total number of directed edges.
   - **Total Space for the Graph**: \(O(n + m)\).

2. **Queue for BFS**:
   - The queue stores nodes (flights) during BFS traversal. In the worst case, all \(n\) nodes are delayed, and the queue will contain all nodes.
   - **Space for the Queue**: \(O(n)\).

3. **Set for Delayed Flights**:
   - The `Set` keeps track of delayed flights to avoid duplicates. In the worst case, all \(n\) flights are delayed.
   - **Space for the Set**: \(O(n)\).

4. **Output List**:
   - The result list stores all delayed flights. In the worst case, all \(n\) flights are delayed.
   - **Space for the Result List**: \(O(n)\).

5. **Temporary Variables**:
   - Temporary variables such as integers, loop counters, etc., use constant space.
   - **Space for Temporary Variables**: \(O(1)\).

---

### **Total Space Complexity**
Adding up all the components:
- Adjacency List: \(O(n + m)\)
- BFS Queue: \(O(n)\)
- Set for Delayed Flights: \(O(n)\)
- Output List: \(O(n)\)

**Total Space Complexity**:  
\[
O(n + m)
\]

Here:
- \(n\) is the number of flights (nodes).
- \(m\) is the number of dependencies (edges).

---

### **Efficiency**
The algorithm is space-efficient because it avoids redundant storage and uses graph-based data structures that scale well with sparse dependencies (common in real-world flight networks).

## Que 2. Ideal properties that should be part of sorting algorithm

Sorting algorithms can vary widely in design and application, but certain ideal properties make them efficient, robust, and suitable for different use cases. Below are the key properties that are desirable in a sorting algorithm:

---

### **1. Stability**
- A sorting algorithm is **stable** if it preserves the relative order of records with equal keys (values).
- Example: If two elements `A` and `B` are equal, and `A` appears before `B` in the input, then `A` should still appear before `B` in the sorted output.
- **Importance**: Stability is crucial in multi-level sorting (e.g., first by age, then by name) or when working with data that has secondary sorting keys.

---

### **2. Time Complexity**
- The efficiency of a sorting algorithm is measured by its time complexity:
  - **Best-case performance**: How quickly it sorts already sorted data.
  - **Worst-case performance**: How it behaves with highly disordered input.
  - **Average-case performance**: Its behavior on random input.
- **Desirable Time Complexity**:
  - \(O(n \log n)\) is optimal for comparison-based sorting.
  - \(O(n)\) is achievable for non-comparison-based algorithms like **Counting Sort** or **Radix Sort**.

---

### **3. Space Complexity**
- Refers to the additional memory the algorithm requires beyond the input array:
  - **In-place Sorting**: Uses \(O(1)\) or \(O(\log n)\) extra space (e.g., Quicksort, Heap Sort).
  - Algorithms like Merge Sort require \(O(n)\) extra space.
- **Desirable Property**: Minimized space complexity is preferred, especially in memory-constrained environments.

---

### **4. Adaptability**
- An **adaptive** sorting algorithm performs better on nearly sorted data.
- Example: Insertion Sort has \(O(n)\) time complexity for nearly sorted data.
- **Use Case**: Situations where input is often already sorted or partially sorted.

---

### **5. Deterministic Behavior**
- A sorting algorithm should consistently produce the same sorted output for the same input, regardless of system-specific factors.
- Example: Deterministic partitioning in Quicksort ensures consistent output.

---

### **6. Parallelizability**
- Sorting algorithms like **Merge Sort** are easier to parallelize, while others like **Quicksort** are harder to parallelize due to their recursive nature.
- **Desirable Property**: Parallelizable sorting is beneficial for large datasets on multi-core systems.

---

### **7. Simplicity and Ease of Implementation**
- A sorting algorithm should be straightforward to implement and debug.
- Example: Bubble Sort is simple but inefficient, while Merge Sort balances simplicity with efficiency.

---

### **8. Flexibility**
- The algorithm should be adaptable to various data types and constraints (e.g., integer, floating-point, strings, or custom objects).

---

### **9. Robustness**
- The algorithm should handle edge cases gracefully:
  - Empty array, single-element array.
  - Arrays with duplicate elements.
  - Very large datasets.

---

### **10. Comparative Efficiency**
- Algorithms should perform well for the dataset size and characteristics:
  - Small datasets: Simpler algorithms like Insertion Sort or Bubble Sort may suffice.
  - Large datasets: More advanced algorithms like Quicksort, Merge Sort, or non-comparison-based algorithms (Radix Sort).

---

### **Comparison of Common Sorting Algorithms**

| Algorithm         | Time Complexity (Best/Worst/Average) | Stability | In-place | Adaptive | Parallelizable |
|--------------------|---------------------------------------|-----------|----------|----------|----------------|
| Bubble Sort        | \(O(n)\) / \(O(n^2)\) / \(O(n^2)\)   | Yes       | Yes      | Yes      | No             |
| Insertion Sort     | \(O(n)\) / \(O(n^2)\) / \(O(n^2)\)   | Yes       | Yes      | Yes      | No             |
| Merge Sort         | \(O(n \log n)\) / \(O(n \log n)\)    | Yes       | No       | No       | Yes            |
| Quicksort          | \(O(n \log n)\) / \(O(n^2)\)         | No        | Yes      | No       | Hard           |
| Heap Sort          | \(O(n \log n)\) / \(O(n \log n)\)    | No        | Yes      | No       | No             |
| Counting Sort      | \(O(n + k)\) / \(O(n + k)\)          | Yes       | No       | No       | No             |

---

### **Conclusion**
The choice of a sorting algorithm depends on:
1. **Input size**.
2. **Stability requirements**.
3. **Space constraints**.
4. **Likelihood of nearly sorted data**.
5. **Parallel processing capability**.

For general-purpose use, **Merge Sort** or **Timsort** (used in Python's `sorted` and Java's `Arrays.sort()`) are preferred due to their balance of stability, efficiency, and scalability.

## Que 3. For sorting linkedlist which sorting best

Sorting a linked list efficiently depends on the structure of the list and the constraints on time and space. Since a linked list does not allow random access (unlike arrays), certain sorting algorithms are more suitable. Here's an analysis of the best sorting algorithms for linked lists:

---

### **Best Sorting Algorithms for Linked Lists**
1. **Merge Sort (Preferred)**:
   - **Why Best for Linked List**:
     - Works efficiently with linked lists because it doesn’t require random access.
     - Splitting the list into halves and merging sorted sublists can be done naturally by adjusting pointers.
   - **Time Complexity**: \(O(n \log n)\), where \(n\) is the number of nodes.
   - **Space Complexity**:
     - If implemented recursively: \(O(\log n)\) due to recursion stack.
     - If implemented iteratively: \(O(1)\) additional space.
   - **Stability**: Yes (preserves the relative order of equal elements).
   - **Ideal Use Case**: General-purpose sorting for singly or doubly linked lists.

   **Implementation**:
   ```java
   class Node {
       int data;
       Node next;
       Node(int data) {
           this.data = data;
           this.next = null;
       }
   }

   public class LinkedListSort {
       // Merge Sort for Linked List
       public static Node mergeSort(Node head) {
           // Base case: if head is null or has only one element
           if (head == null || head.next == null) {
               return head;
           }

           // Split the list into two halves
           Node middle = getMiddle(head);
           Node nextOfMiddle = middle.next;
           middle.next = null;

           // Recursively sort the two halves
           Node left = mergeSort(head);
           Node right = mergeSort(nextOfMiddle);

           // Merge the sorted halves
           return sortedMerge(left, right);
       }

       // Merge two sorted lists
       private static Node sortedMerge(Node a, Node b) {
           if (a == null) return b;
           if (b == null) return a;

           Node result;
           if (a.data <= b.data) {
               result = a;
               result.next = sortedMerge(a.next, b);
           } else {
               result = b;
               result.next = sortedMerge(a, b.next);
           }
           return result;
       }

       // Find the middle of the linked list
       private static Node getMiddle(Node head) {
           if (head == null) return head;
           Node slow = head, fast = head;
           while (fast.next != null && fast.next.next != null) {
               slow = slow.next;
               fast = fast.next.next;
           }
           return slow;
       }
   }
   ```

---

2. **Quick Sort**:
   - **Why Use**:
     - Can be adapted for linked lists using the "partitioning" approach with pointers.
   - **Time Complexity**:
     - Average case: \(O(n \log n)\).
     - Worst case: \(O(n^2)\) if the pivot selection is poor.
   - **Space Complexity**: \(O(\log n)\) for recursion stack.
   - **Stability**: No (does not preserve relative order of equal elements).
   - **Drawback**: Partitioning linked lists efficiently is tricky compared to arrays.
   - **Ideal Use Case**: When space is not a constraint and recursion is acceptable.

---

3. **Insertion Sort**:
   - **Why Use**:
     - Suitable for small or nearly sorted linked lists.
     - Works efficiently on linked lists since elements can be inserted without shifting others.
   - **Time Complexity**: 
     - Best case: \(O(n)\) (nearly sorted list).
     - Worst case: \(O(n^2)\).
   - **Space Complexity**: \(O(1)\) (in-place).
   - **Stability**: Yes.
   - **Ideal Use Case**: Small or nearly sorted linked lists.

   **Implementation**:
   ```java
   public static Node insertionSort(Node head) {
       if (head == null) return head;

       Node sorted = null;
       Node current = head;
       while (current != null) {
           Node next = current.next;

           // Insert current node into sorted list
           if (sorted == null || sorted.data >= current.data) {
               current.next = sorted;
               sorted = current;
           } else {
               Node temp = sorted;
               while (temp.next != null && temp.next.data < current.data) {
                   temp = temp.next;
               }
               current.next = temp.next;
               temp.next = current;
           }

           current = next;
       }
       return sorted;
   }
   ```

---

### **Comparison of Sorting Algorithms for Linked Lists**

| Algorithm      | Time Complexity | Space Complexity | Stability | Use Case                                   |
|----------------|-----------------|------------------|-----------|--------------------------------------------|
| **Merge Sort** | \(O(n \log n)\) | \(O(1)\) or \(O(\log n)\) | Yes       | General-purpose sorting for linked lists. |
| **Quick Sort** | \(O(n \log n)\) (avg), \(O(n^2)\) (worst) | \(O(\log n)\) | No        | When recursion and pivoting are feasible. |
| **Insertion Sort** | \(O(n^2)\) (worst), \(O(n)\) (best) | \(O(1)\)          | Yes       | Small or nearly sorted linked lists.      |

---

### **Conclusion**
- For general-purpose sorting of linked lists, **Merge Sort** is the best choice due to its efficiency, stability, and natural compatibility with linked list structures.
- Use **Insertion Sort** for small or nearly sorted linked lists.
- Avoid **Quick Sort** unless you have specific reasons, as it is less efficient and harder to implement for linked lists.

## Que 4. Why to choose DP over others

### **What is Dynamic Programming (DP)?**

Dynamic Programming (DP) is a technique used to solve problems by breaking them down into smaller, overlapping subproblems. Instead of solving the same subproblem multiple times, DP solves each subproblem once and stores the result (memoization or tabulation) for future use. This approach makes it highly efficient for problems with overlapping subproblems and optimal substructure.

---

### **Key Properties of DP Problems**

1. **Optimal Substructure**:
   - A problem exhibits optimal substructure if the solution to the overall problem can be constructed from the solutions of its subproblems.
   - Example: In the shortest path problem, the shortest path from \(A \to C\) passing through \(B\) is the sum of \(A \to B\) and \(B \to C\).

2. **Overlapping Subproblems**:
   - A problem exhibits overlapping subproblems if the same subproblems are solved multiple times in a naive recursive solution.
   - Example: In the Fibonacci sequence, \(F(n) = F(n-1) + F(n-2)\), where \(F(n-1)\) and \(F(n-2)\) are repeatedly computed in a naive solution.

---

### **DP Techniques**

1. **Memoization (Top-Down Approach)**:
   - Solve problems recursively and store the results of subproblems in a data structure (e.g., array, map).
   - Avoid recomputation by checking if the result already exists before solving.
   - **Example**:
     ```java
     public class Fibonacci {
         private static int[] memo;

         public static int fib(int n) {
             if (n <= 1) return n;
             if (memo[n] != 0) return memo[n];
             memo[n] = fib(n - 1) + fib(n - 2);
             return memo[n];
         }

         public static void main(String[] args) {
             int n = 10;
             memo = new int[n + 1];
             System.out.println(fib(n));
         }
     }
     ```

2. **Tabulation (Bottom-Up Approach)**:
   - Iteratively solve subproblems and store results in a table (array or matrix).
   - Build the solution from the smallest subproblems to the original problem.
   - **Example**:
     ```java
     public class Fibonacci {
         public static int fib(int n) {
             if (n <= 1) return n;

             int[] dp = new int[n + 1];
             dp[0] = 0;
             dp[1] = 1;

             for (int i = 2; i <= n; i++) {
                 dp[i] = dp[i - 1] + dp[i - 2];
             }
             return dp[n];
         }

         public static void main(String[] args) {
             System.out.println(fib(10));
         }
     }
     ```

---

### **How DP Differs from Other Techniques**

| **Feature**                | **Dynamic Programming**                                  | **Divide and Conquer**                               | **Greedy Algorithms**                                |
|----------------------------|---------------------------------------------------------|-----------------------------------------------------|-----------------------------------------------------|
| **Overlapping Subproblems** | Solves overlapping subproblems efficiently.             | Does not explicitly handle overlapping subproblems. | Does not handle overlapping subproblems.           |
| **Optimal Substructure**    | Yes, required.                                          | Yes, required.                                      | Yes, required.                                      |
| **Subproblem Dependency**   | Solves all subproblems and combines results.            | Solves subproblems independently and combines them. | Solves based on local decisions.                   |
| **Efficiency**              | Efficient for problems with overlapping subproblems.   | Can be inefficient due to recomputation.            | Highly efficient for problems with local optima.   |
| **Example Problems**        | Fibonacci, Knapsack, Longest Common Subsequence (LCS). | Merge Sort, Quick Sort, Binary Search.              | Huffman Coding, Dijkstra’s Algorithm, Kruskal’s.   |

---

### **Real-Life Applications of Dynamic Programming**

1. **Optimization Problems**:
   - **Knapsack Problem**: Maximizing value with weight constraints.
   - **Subset Sum Problem**: Checking if a subset with a given sum exists.

2. **String Problems**:
   - Longest Common Subsequence (LCS).
   - Longest Palindromic Subsequence.
   - Edit Distance (Levenshtein Distance).

3. **Graph Problems**:
   - Shortest Path Algorithms (e.g., Floyd-Warshall, Bellman-Ford).
   - All-Pairs Shortest Path.

4. **Combinatorics**:
   - Counting the number of ways to reach a target.
   - Catalan Numbers.

---

### **Why Choose DP?**
Dynamic Programming is ideal for problems

## Que 5. cons of DP

While **Dynamic Programming (DP)** is a powerful technique for solving optimization and combinatorial problems, it has some **limitations and drawbacks**:

---

### **1. High Space Complexity**
- **Problem**: DP often requires a table or array to store intermediate results, leading to significant memory usage, especially in problems with large input sizes or multi-dimensional DP.
- **Example**: For problems like matrix chain multiplication or longest common subsequence, a \(O(n^2)\) table can quickly consume large amounts of memory.
- **Mitigation**:
  - Use **space optimization techniques**, such as only storing the last row or column when previous results are not needed (common in problems like Fibonacci or LCS).

---

### **2. High Time Complexity**
- **Problem**: Although DP reduces redundant calculations, the time complexity can still be high if the state space (number of subproblems) is large.
- **Example**: Problems with multiple dimensions or numerous states, such as traveling salesman problem (TSP), have exponential or factorial complexity even with DP.
- **Mitigation**:
  - Reduce state space using heuristics or problem-specific insights.

---

### **3. Requires Problem Structuring**
- **Problem**: Not all problems are suited for DP. It requires that the problem satisfies:
  1. **Optimal Substructure**: The solution can be constructed from subproblems.
  2. **Overlapping Subproblems**: Subproblems are solved multiple times in a naive approach.
- If either of these properties is absent, DP cannot be applied effectively.
- **Mitigation**:
  - Reformulate the problem if possible or consider alternative techniques like greedy algorithms or divide-and-conquer.

---

### **4. Complex Implementation**
- **Problem**: Designing a DP solution can be challenging, especially for problems with:
  - Multiple states or dimensions.
  - Complex state transitions.
  - Backtracking requirements for reconstructing the solution.
- **Example**: Multi-dimensional DP (e.g., for 3D problems) can become difficult to implement and debug.
- **Mitigation**:
  - Break the problem into smaller steps.
  - Start with a brute force approach, then optimize using DP.

---

### **5. Lack of Intuition**
- **Problem**: DP solutions are often counter-intuitive for beginners because:
  - They require thinking in terms of states and transitions.
  - The iterative or recursive structure is not always obvious.
- **Mitigation**:
  - Practice common DP problems to develop intuition.
  - Use visual aids like tables or graphs to understand state transitions.

---

### **6. Not Always Optimal for Real-Time Systems**
- **Problem**: DP may not be suitable for real-time applications due to its potentially high computational and memory requirements.
- **Example**: In scenarios like video game AI or embedded systems, where responses must be immediate, DP may not meet performance requirements.
- **Mitigation**:
  - Use greedy algorithms or heuristics for faster, approximate solutions.

---

### **7. Risk of Redundant States**
- **Problem**: Poorly designed state definitions or transitions can lead to recomputing or storing redundant states, increasing time and space complexity unnecessarily.
- **Example**: Incorrect memoization can lead to duplicated calculations in problems like knapsack.
- **Mitigation**:
  - Carefully analyze and define the state space.
  - Ensure all redundant states are eliminated during design.

---

### **8. Limited Scalability**
- **Problem**: DP solutions may fail to scale for very large input sizes because of the exponential growth of states.
- **Example**: Problems like TSP with DP have \(O(n^2 \cdot 2^n)\) complexity, which becomes impractical for larger \(n\).
- **Mitigation**:
  - Use approximation algorithms or metaheuristics like genetic algorithms for large-scale problems.

---

### **Key Takeaways**
Dynamic Programming is a powerful tool, but its drawbacks include high space and time complexity, the need for problem restructuring, and potential implementation challenges. Careful problem analysis, optimization techniques, and a clear understanding of DP principles can help mitigate these issues.