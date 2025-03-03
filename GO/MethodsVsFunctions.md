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

## Here are different types of function prototypes in Go:

### **Basic Function**
```go
func functionName()
```
(No parameters, no return value)

### **Function with Parameters**
```go
func functionName(param1 int, param2 string)
```
(Takes an `int` and a `string` as input)

### **Function with Return Value**
```go
func functionName() int
```
(Returns an `int`)

### **Function with Multiple Return Values**
```go
func functionName() (int, string, error)
```
(Returns an `int`, a `string`, and an `error`)

### **Function with a Struct Receiver (Method)**
```go
func (s *StructName) functionName(param int) string
```
(Method bound to `StructName`, takes an `int`, returns a `string`)

### **Function with Variadic Parameters**
```go
func functionName(values ...int)
```
(Accepts a variable number of `int` values)

### **Anonymous Function**
```go
func() {
    fmt.Println("Hello, Go!")
}()
```
(Self-executing function with no name)

### **Higher-Order Function (Function as Parameter)**
```go
func functionName(callback func(int) string) 
```
(Takes a function as an argument)

### **Function Returning Another Function**
```go
func functionName() func(int) string
```
(Returns a function that takes an `int` and returns a `string`)

