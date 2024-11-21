This Java function implements **modular exponentiation** using the **binary exponentiation** (also called **exponentiation by squaring**) technique. The function computes \( x^y \mod p \), which is the result of raising `x` to the power `y` and then taking the result modulo `p`.

### **Explanation of the Function**

```java
public static long power(long x, long y, long p) {
    long res = 1L;  // Initialize result to 1
    x = x % p;  // Handle large x values by reducing them modulo p (since x^y % p is the same as (x % p)^y % p)

    // Loop while y is greater than 0
    while (y > 0) {
        // If y is odd (i.e., least significant bit of y is 1)
        if ((y & 1) == 1) {
            // Multiply the result by x and take modulo p
            res = (res * x) % p;
        }

        // Right shift y (divide y by 2)
        y >>= 1;

        // Square x and take modulo p
        x = (x * x) % p;
    }

    // Return the result
    return res;
}
```

### **Steps and Key Concepts**
1. **Initialization**:
   - `res = 1`: This is the variable that will store the result of the exponentiation.
   - `x = x % p`: We reduce `x` modulo `p` to avoid overflow and reduce the base early.

2. **Loop Through Bits of `y`**:
   - The binary representation of `y` is examined bit by bit.
   - If the least significant bit (LSB) of `y` is `1` (i.e., `y` is odd), we multiply the result by `x` and take the result modulo `p`.
   - We then right shift `y` (`y >>= 1`), which is equivalent to dividing `y` by 2.
   - We also square `x` and take the modulo `p`. This is part of the **exponentiation by squaring** technique, where we square the base and halve the exponent in each iteration.

3. **Binary Exponentiation**:
   - This method efficiently computes the result by reducing the problem size at each step.
   - Instead of performing \( x^y \) directly, which can be computationally expensive, it breaks down the problem using the binary representation of `y`.
   
### **Time Complexity**
- **Time Complexity**: \( O(\log y) \). Since we are halving `y` at each step, the loop runs logarithmically in relation to `y`.
- **Space Complexity**: \( O(1) \). The algorithm uses a constant amount of space, apart from the input/output.

### **Example**

Let's say we want to compute \( 3^13 \mod 7 \).

- `x = 3`, `y = 13`, `p = 7`
- The binary representation of `13` is `1101`, which corresponds to the following steps:

  1. \( 3^1 \mod 7 = 3 \) (since `y` is odd, we multiply result by `x`)
  2. \( 3^2 \mod 7 = 2 \) (square `x`)
  3. \( 3^4 \mod 7 = 4 \) (square `x` again)
  4. \( 3^8 \mod 7 = 2 \) (square `x` again)
  5. The final result is calculated by combining the intermediate results, which gives \( 3^{13} \mod 7 = 5 \).

The function provides an efficient way to calculate large powers modulo a number, which is particularly useful in fields like cryptography.