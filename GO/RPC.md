### **🔹 What are RPC Methods in gRPC?**  

**RPC (Remote Procedure Call) methods** are functions exposed by a **gRPC service** that allow clients to call them remotely over the network. These methods are defined in a **`.proto` file** and run on the server, but they can be invoked from a client as if they were local functions.

---

## **🔹 Types of RPC Methods in gRPC**
There are **four types** of RPC methods in gRPC based on how data is sent and received.

### **1️⃣ Unary RPC (Request-Response)**
- **Client sends** one request.
- **Server responds** with one response.
- Similar to a normal function call.
  
**Example:**
```proto
service SearchService {
    rpc Search (SearchRequest) returns (SearchResponse);
}
```
✅ **Client Request**:
```sh
grpcurl -plaintext -d '{"query": "hotels"}' 127.0.0.1:8073 searchProto.SearchService/Search
```
✅ **Response (JSON)**:
```json
{
    "results": ["Hotel A", "Hotel B"]
}
```

---

### **2️⃣ Server Streaming RPC**
- **Client sends** one request.
- **Server streams** multiple responses.

**Example:**
```proto
service HotelService {
    rpc GetHotelUpdates (HotelRequest) returns (stream HotelUpdate);
}
```
✅ **Client Calls**:
```sh
grpcurl -plaintext -d '{"hotel_id": 123}' 127.0.0.1:8073 HotelService/GetHotelUpdates
```
✅ **Response** (Multiple lines):
```json
{"status": "Booked"}
{"status": "Available"}
{"status": "Under Maintenance"}
```

---

### **3️⃣ Client Streaming RPC**
- **Client streams** multiple requests.
- **Server responds** once after receiving all.

**Example:**
```proto
service ReviewService {
    rpc UploadReviews (stream Review) returns (UploadStatus);
}
```
✅ **Client Sends Multiple Requests**:
```sh
grpcurl -plaintext -d '{"text": "Great hotel!"}' 127.0.0.1:8073 reviewProto.ReviewService/UploadReviews
grpcurl -plaintext -d '{"text": "Clean rooms"}' 127.0.0.1:8073 reviewProto.ReviewService/UploadReviews
```
✅ **Server Responds Once**:
```json
{"status": "Success"}
```

---

### **4️⃣ Bidirectional Streaming RPC**
- **Client and server send** multiple messages at the same time.

**Example:**
```proto
service ChatService {
    rpc Chat (stream ChatMessage) returns (stream ChatMessage);
}
```
✅ **Both Sides Stream Data Simultaneously**.

---

## **🔹 How to Find Available RPC Methods?**
Since you already ran:
```sh
grpcurl -plaintext 127.0.0.1:8073 list
```
You now know your available services.

To see **methods inside a specific service**, run:
```sh
grpcurl -plaintext 127.0.0.1:8073 describe searchProto.SearchService
```

This will show **all RPC methods** inside `searchProto.SearchService` with their request/response structure.

---
