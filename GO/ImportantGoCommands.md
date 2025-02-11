## **🔹 Essential Go Commands Explained**
These `go` commands are used for **building, managing modules, cleaning caches, and configuring Go environments**. Let's go through them in detail.

---

## **1️⃣ `go build -v`**
### **📌 Purpose**: Compiles a Go program and prints the names of packages being compiled.

### **🔹 Syntax**
```sh
go build -v
```

### **🔹 Example**
```sh
$ go build -v
```
**Output**
```
runtime
fmt
mypackage
```
- **Compiles** the Go program in the current directory.
- **Verbose mode (`-v`)** shows each package being compiled.

### **📌 Variations**
| Command | Description |
|---------|-------------|
| `go build` | Compiles the program silently (without showing packages). |
| `go build -o myapp` | Builds the program and outputs a binary named `myapp`. |

---

## **2️⃣ `go mod vendor -v`**
### **📌 Purpose**: Creates a `vendor/` directory with all module dependencies.

### **🔹 Syntax**
```sh
go mod vendor -v
```

### **🔹 Example**
```sh
$ go mod vendor -v
```
**Output**
```
github.com/pkg/errors
golang.org/x/sys
```
- **Moves all dependencies** to a `vendor/` folder inside the project.
- Ensures **offline builds** without fetching from the internet.

### **📌 Why Use It?**
- Useful when **deploying** code in environments without internet access.
- Avoids **dependency version mismatches**.

### **📌 Check if `vendor` is used**
```sh
go env | grep GOFLAGS
```
- If `-mod=vendor` appears, Go is using the `vendor/` directory.

---

## **3️⃣ `go env`**
### **📌 Purpose**: Shows Go environment variables.

### **🔹 Syntax**
```sh
go env
```

### **🔹 Example**
```sh
$ go env
```
**Output (Sample)**
```
GOARCH="amd64"
GOBIN=""
GOCACHE="/Users/user/Library/Caches/go-build"
GOOS="linux"
GOPATH="/Users/user/go"
GOROOT="/usr/local/go"
GOMOD="/Users/user/project/go.mod"
```

### **📌 Useful Environment Variables**
| Variable | Description |
|----------|------------|
| `GOOS` | Target operating system (e.g., `linux`, `windows`, `darwin`). |
| `GOARCH` | Target architecture (`amd64`, `arm64`, etc.). |
| `GOPATH` | Go workspace directory. |
| `GOROOT` | Installation path of Go. |
| `GOCACHE` | Cache directory for compiled Go files. |

### **📌 Setting Environment Variables**
```sh
export GOOS=linux
export GOARCH=amd64
go build
```
- This **cross-compiles** a Go program for Linux on an AMD64 architecture.

---

## **4️⃣ `go clean -cache -modcache`**
### **📌 Purpose**: Removes the **compiled cache** and **module cache**.

### **🔹 Syntax**
```sh
go clean -cache -modcache
```

### **📌 What It Does**
- **`-cache`** → Deletes **compiled object files**.
- **`-modcache`** → Deletes **cached Go modules** (from `$GOPATH/pkg/mod/`).

### **🔹 Example**
```sh
$ go clean -cache -modcache
```
- Useful when you want a **fresh build** or **clear outdated dependencies**.

---

## **5️⃣ `go clean -cache`**
### **📌 Purpose**: Removes **only the compiled object cache**.

### **🔹 Syntax**
```sh
go clean -cache
```

### **📌 When to Use It?**
- If you encounter **build issues**, try clearing the cache first.
- If your **build is slow**, clearing the cache can help.

---

## **6️⃣ `go mod tidy`**
### **📌 Purpose**: Removes **unused dependencies** and **adds missing ones**.

### **🔹 Syntax**
```sh
go mod tidy
```

### **📌 Example**
Before running:
```sh
go list -m all
```
```
example.com/project
github.com/pkg/errors v0.9.1
github.com/stretchr/testify v1.7.0
```
After deleting `github.com/stretchr/testify` from the code:
```sh
go mod tidy
```
Now running `go list -m all`:
```
example.com/project
github.com/pkg/errors v0.9.1
```
- **`go mod tidy` removes unused dependencies** from `go.mod` and `go.sum`.
- Helps keep the project **clean and minimal**.

---

## **7️⃣ `go mod init`**
### **📌 Purpose**: Initializes a new Go module by creating a `go.mod` file.

### **🔹 Syntax**
```sh
go mod init <module-name>
```

### **🔹 Example**
```sh
go mod init example.com/mypackage
```
**Creates a `go.mod` file**
```
module example.com/mypackage

go 1.21
```
- Required for **managing dependencies** in modern Go projects.
- **Equivalent to `npm init` in Node.js**.

---

## **🔹 Other Useful `go` Commands**
| Command | Description |
|---------|------------|
| `go get <package>` | Downloads and installs a Go package. |
| `go list -m all` | Lists all installed Go modules. |
| `go mod edit -replace old=new` | Replaces a dependency with a local version. |
| `go test ./...` | Runs all tests in the project. |
| `go fmt ./...` | Formats Go code according to Go style. |
| `go vet ./...` | Reports potential issues in Go code. |

---

## **🚀 Summary**
| Command | Purpose |
|---------|---------|
| `go build -v` | Compiles Go programs and shows package names. |
| `go mod vendor -v` | Moves dependencies to a `vendor/` folder. |
| `go env` | Displays Go environment variables. |
| `go clean -cache -modcache` | Clears both **compiled** and **module** caches. |
| `go clean -cache` | Clears only the **compiled cache**. |
| `go mod tidy` | Removes unused dependencies. |
| `go mod init <module>` | Initializes a **new Go module**. |
