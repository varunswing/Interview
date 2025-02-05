## **🔹 Methods vs Functions in Go**
In Go, **methods** and **functions** both allow you to define reusable blocks of code, but there are key differences.

---

## **🔹 Functions in Go**
A **function** is an independent block of code that **is not tied to any struct or type**.

### **Example: Function**
```go
package main
import "fmt"

// Function (not associated with any type)
func greet(name string) string {
    return "Hello, " + name
}

func main() {
    fmt.Println(greet("Alice")) // Output: Hello, Alice
}
```
✅ **Key points:**
- Functions are **not associated** with any type.
- They take parameters and **return values independently**.

---

## **🔹 Methods in Go**
A **method** is a function that has a **receiver** (a struct or custom type). It is associated with an instance of a **specific type**.

### **Example: Method**
```go
package main
import "fmt"

// Define a struct
type Person struct {
    name string
}

// Method with receiver (p Person)
func (p Person) greet() string {
    return "Hello, " + p.name
}

func main() {
    person := Person{name: "Alice"}
    fmt.Println(person.greet()) // Output: Hello, Alice
}
```
✅ **Key points:**
- A method is a function with a **receiver** (`p Person`).
- It **belongs** to a specific type (in this case, `Person`).
- It can **access fields** of the struct.

---

## **🔹 Key Differences Between Methods and Functions**
| Feature  | Function | Method |
|----------|---------|--------|
| **Receiver** | ❌ No receiver | ✅ Has a receiver (struct or type) |
| **Scope** | Independent | Associated with a specific type |
| **Usage** | Called directly | Called on an instance of a type |
| **Can modify struct?** | ❌ No access to struct fields | ✅ Can access struct fields |
| **Example Call** | `greet("Alice")` | `person.greet()` |

---

## **🔹 Methods with Pointer Receivers (`*`)**
If you need to **modify** the struct inside a method, use a **pointer receiver**.

### **Example: Method with Pointer Receiver**
```go
package main
import "fmt"

// Define a struct
type Counter struct {
    value int
}

// Method with pointer receiver (*c Counter)
func (c *Counter) increment() {
    c.value++
}

func main() {
    c := Counter{value: 5}
    c.increment() // Modifies the actual struct
    fmt.Println(c.value) // Output: 6
}
```
✅ **Why use a pointer receiver (`*Counter`)**?
- It allows **modification** of the struct inside the method.
- Without `*`, Go **passes struct copies**, so changes wouldn't persist.

---

## **🚀 Final Takeaway**
| Feature  | Function | Method |
|----------|---------|--------|
| ✅ No receiver | ✅ Has a receiver (struct/type) |
| ✅ Independent | ✅ Tied to a type |
| ✅ Can be called anywhere | ✅ Called on a struct instance |
| ❌ Cannot modify struct fields | ✅ Can modify struct (with `*receiver`) |
