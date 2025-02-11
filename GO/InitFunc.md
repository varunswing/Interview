## **🔹 `func init()` in Go**
The `init()` function in Go is a **special function** that gets executed **automatically before `main()`**. It is primarily used for **initialization tasks** such as setting up configurations, initializing global variables, or preparing external resources.

---

## **🔹 Key Features of `init()`**
1. **Executed before `main()`**.
2. **Automatically called** (No need to invoke it).
3. **Can be defined multiple times** in the same package.
4. **Has no parameters and no return values**.
5. **Executed in the order of package imports**.

---

## **🔹 Example 1: Basic `init()` Function**
```go
package main
import "fmt"

func init() {
    fmt.Println("Initializing...")
}

func main() {
    fmt.Println("Main function running")
}
```
### **🔹 Output**
```
Initializing...
Main function running
```
- `init()` runs **before `main()`**.

---

## **🔹 Example 2: Multiple `init()` Functions**
If multiple `init()` functions exist in the **same package**, they are executed in **the order they appear**.

```go
package main
import "fmt"

func init() {
    fmt.Println("Init 1 executed")
}

func init() {
    fmt.Println("Init 2 executed")
}

func main() {
    fmt.Println("Main function running")
}
```
### **🔹 Output**
```
Init 1 executed
Init 2 executed
Main function running
```
- Both `init()` functions execute **before `main()`**, in **the order they are written**.

---

## **🔹 Example 3: `init()` in Multiple Files**
If a package contains **multiple files**, each with its own `init()` function, they are executed **in an undefined order**.

### **File: `file1.go`**
```go
package main
import "fmt"

func init() {
    fmt.Println("Init from file1")
}
```

### **File: `file2.go`**
```go
package main
import "fmt"

func init() {
    fmt.Println("Init from file2")
}
```

### **File: `main.go`**
```go
package main
import "fmt"

func main() {
    fmt.Println("Main function running")
}
```

### **🔹 Possible Output**
```
Init from file1
Init from file2
Main function running
```
- `init()` functions from `file1.go` and `file2.go` execute before `main()`.

---

## **🔹 Example 4: `init()` in Different Packages**
When a Go program imports multiple packages, their `init()` functions execute **before `main()`**, following the package import order.

### **Package: `mypackage/util.go`**
```go
package mypackage
import "fmt"

func init() {
    fmt.Println("Initializing mypackage")
}

func Greet() {
    fmt.Println("Hello from mypackage!")
}
```

### **Main Program: `main.go`**
```go
package main
import (
    "fmt"
    "mypackage"
)

func init() {
    fmt.Println("Initializing main package")
}

func main() {
    fmt.Println("Main function running")
    mypackage.Greet()
}
```

### **🔹 Output**
```
Initializing mypackage
Initializing main package
Main function running
Hello from mypackage!
```
- The `init()` in `mypackage` executes **before** `init()` in `main`.

---

## **🔹 When to Use `init()`?**
✅ **Loading configurations** (e.g., reading from environment variables or config files).  
✅ **Opening database connections**.  
✅ **Setting up global variables**.  
✅ **Performing sanity checks before program execution**.  

---

## **🔹 Summary**
| Feature | Description |
|---------|------------|
| **Runs before `main()`** | `init()` functions execute automatically before `main()`. |
| **No parameters/returns** | Cannot accept arguments or return values. |
| **Multiple `init()` in a package** | They run in **the order they appear**. |
| **Runs across multiple files** | If a package has multiple files, `init()` functions execute **in an undefined order**. |
| **Runs across multiple packages** | Imported package `init()` runs before the main package’s `init()`. |

