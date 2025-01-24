# Coding problem 

## Que 1. 

There is a farmer who has to cross river with all the animals and he can cross the river with either one animal two animal at a time for given n provide the number of diffeent ways farmer can cross the river 

for n=0 there is 1 way
for n=1 there is 1 way
for n=2 there is 2 way 
for n=3 there is 4 ways

This problem is essentially about finding the number of ways to partition \(n\) animals into groups such that each group contains either 1 or 2 animals. The solution can be derived using **dynamic programming**.

---

### **Approach**
1. **Base Cases:**
   - \(f(0) = 1\): If there are no animals, there's only 1 way (do nothing).
   - \(f(1) = 1\): If there's 1 animal, there's only 1 way (take it in the boat).
   - \(f(2) = 2\): If there are 2 animals, the farmer can either:
     - Take one animal twice (1 + 1).
     - Take both animals together (2).

2. **Recursive Relation:**
   For \(n > 2\), the number of ways to cross the river can be expressed as:
   \[
   f(n) = f(n-1) + f(n-2)
   \]
   - **Explanation:**
     - If the farmer takes 1 animal first, the remaining \(n-1\) animals need to be arranged (\(f(n-1)\)).
     - If the farmer takes 2 animals first, the remaining \(n-2\) animals need to be arranged (\(f(n-2)\)).

3. **Dynamic Programming Table:**
   Use a bottom-up approach to calculate \(f(n)\) iteratively, storing intermediate results to avoid redundant calculations.

4. **Optimization:**
   Instead of maintaining an array for all values up to \(n\), use only two variables to store \(f(n-1)\) and \(f(n-2)\) since \(f(n)\) depends only on these two previous values.

---

### **Algorithm**
1. Initialize variables for \(f(0)\) and \(f(1)\).
2. Iterate from \(2\) to \(n\), updating the values for \(f(n-1)\) and \(f(n-2)\).
3. Return the final result.

---

### **Code Implementation**

```java
public class FarmerCrossRiver {
    public static int countWaysToCrossRiver(int n) {
        // Base cases
        if (n == 0) return 1;
        if (n == 1) return 1;

        // Variables to store previous two results
        int prev2 = 1; // f(0)
        int prev1 = 1; // f(1)
        int current = 0;

        // Bottom-up calculation
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2; // f(n) = f(n-1) + f(n-2)
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    public static void main(String[] args) {
        int n = 5; // Example input
        System.out.println("Number of ways to cross the river with " + n + " animals: " + countWaysToCrossRiver(n));
    }
}
```

---

### **Example**
For \(n = 5\):
- \(f(0) = 1\)
- \(f(1) = 1\)
- \(f(2) = 2\)
- \(f(3) = 3\) (\(1+1+1, 1+2, 2+1\))
- \(f(4) = 5\)
- \(f(5) = 8\)

Result: \(8\) ways.

---

### **Time Complexity**
- \(O(n)\): The solution iterates from \(2\) to \(n\), performing a constant amount of work in each step.

### **Space Complexity**
- \(O(1)\): Only three variables (`prev2`, `prev1`, `current`) are used, making the space usage constant.

---

This is the most optimal solution for the given problem.

## Que 2.

top k elements which has higher frequency in array
111223
k=2
give best solution in java with approach n algo

To find the **top k elements with the highest frequency** in an array, you can use the following approach and algorithm:

---

### **Approach**
1. **Count Frequencies:** Use a `HashMap` to count the frequency of each element in the array.
2. **Use a Min-Heap:** Use a priority queue (min-heap) to keep track of the top `k` elements with the highest frequencies.
    - If the size of the heap exceeds `k`, remove the smallest frequency element.
3. **Extract Top k Elements:** After processing the array, the heap will contain the `k` elements with the highest frequencies.

---

### **Algorithm**
1. Traverse the array and populate a `HashMap` to count occurrences of each element.
2. Use a `PriorityQueue` (min-heap) to store elements based on their frequencies:
   - Add elements from the frequency map to the heap.
   - Maintain the heap size as `k` by removing the element with the lowest frequency when the heap size exceeds `k`.
3. Extract elements from the heap to get the result.

---

### **Code Implementation**

```java
import java.util.*;

public class TopKFrequentElements {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        // Step 1: Count frequencies
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a min-heap to track the top k elements
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the element with the lowest frequency
            }
        }

        // Step 3: Extract elements from the heap
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        // Reverse the list to show highest frequency elements first (optional)
        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println("Top " + k + " frequent elements: " + topKFrequent(nums, k));
    }
}
```

---

### **Explanation of the Code**
1. **Frequency Map:** `{1=3, 2=2, 3=1}`.
2. **Min-Heap Operations:**
   - Add `{1=3}`, `{2=2}`, `{3=1}` to the heap.
   - If the heap size exceeds `k`, remove the smallest frequency element.
   - Final heap contains `{1=3, 2=2}` for `k=2`.
3. **Result:** Extract keys from the heap: `[1, 2]`.

---

### **Time Complexity**
- **Frequency Count:** \(O(n)\), where \(n\) is the size of the array.
- **Heap Operations:** \(O(m \log k)\), where \(m\) is the number of unique elements (size of the frequency map) and \(k\) is the heap size.
- Total: \(O(n + m \log k)\).

### **Space Complexity**
- \(O(m + k)\): For the frequency map and heap storage.

This solution is efficient and works well for large arrays.
