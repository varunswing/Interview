# How Spring Boot works

Spring Boot is a framework designed to simplify the development of Java-based applications, particularly those running on Spring Framework. It provides a convention-over-configuration approach and helps developers create production-ready applications quickly with minimal setup. Here's how it works:

---

### **1. Core Features of Spring Boot**
- **Auto-Configuration:** Automatically configures the application based on the dependencies present in the classpath.
- **Embedded Servers:** Includes servers like Tomcat, Jetty, or Undertow, eliminating the need for external server setup.
- **Spring Starter Dependencies:** Predefined dependency packages simplify adding libraries for specific functionalities.
- **Production-Ready Features:** Built-in monitoring, metrics, and application health checks.
- **Opinionated Defaults:** Provides sensible defaults for configurations, which can be overridden if needed.

---

### **2. How Spring Boot Works**

#### **a. Dependency Management**
Spring Boot uses **starter dependencies** (e.g., `spring-boot-starter-web`, `spring-boot-starter-data-jpa`) to group commonly used libraries. This avoids manual dependency management and ensures compatibility between them.

#### **b. Application Entry Point**
- A Spring Boot application starts with a **main class** annotated with `@SpringBootApplication`, which includes:
  - `@EnableAutoConfiguration`: Enables automatic configuration based on classpath contents.
  - `@ComponentScan`: Scans the package for components, configurations, and services.
  - `@Configuration`: Marks the class as a configuration source for bean definitions.
  
Example:
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

#### **c. Auto-Configuration**
Spring Boot auto-configuration analyzes:
- **Classpath contents** (e.g., presence of `spring-web` for web applications).
- **Annotations** (e.g., `@Controller`, `@RestController`).
- **Properties** from `application.properties` or `application.yml`.

It creates and registers beans automatically to set up the application context.

#### **d. Embedded Server**
- When running a web application, Spring Boot automatically starts an embedded server (like Tomcat) on a default port (8080). You can customize it in `application.properties`:
  ```properties
  server.port=9090
  ```

#### **e. Application Configuration**
Spring Boot uses the `application.properties` or `application.yml` file for configuration. These files allow you to set up:
- Server properties (port, context path)
- Database settings (URL, username, password)
- Logging levels, etc.

#### **f. Spring Boot Actuator**
The **Actuator** module provides endpoints to monitor and manage applications in production, such as `/actuator/health` and `/actuator/metrics`.

#### **g. Spring Boot CLI**
The CLI lets developers create and run Spring Boot applications using Groovy scripts, accelerating development.

---

### **3. Benefits of Spring Boot**
- Reduces boilerplate code.
- Simplifies dependency management.
- Supports microservices architecture.
- Built-in tools for monitoring and diagnostics.
- Rapid application development and deployment.

---

### **4. Spring Boot Workflow**
1. Add required **starter dependencies**.
2. Define a **main class** annotated with `@SpringBootApplication`.
3. Configure properties in `application.properties` or `application.yml`.
4. Write business logic using Spring components like `@Controller`, `@Service`, etc.
5. Run the application using:
   ```bash
   mvn spring-boot:run
   ```
   Or package it as a JAR and run:
   ```bash
   java -jar application.jar
   ```

Spring Boot’s design philosophy makes it ideal for creating modern, scalable, and production-ready applications.

# How to map objects in Spring Boot

In Spring Boot, **object mapping** is a common requirement for converting between different object types, such as mapping data between entities, DTOs (Data Transfer Objects), and JSON or XML representations. Spring Boot provides several tools and frameworks for object mapping, depending on the use case:

---

### **1. Mapping Between Java Objects**
For mapping between entities (e.g., DTOs and models):

#### **a. Manually Mapping**
- Developers write code to map fields from one object to another.
- Example:
  ```java
  public UserDTO mapToDto(User user) {
      UserDTO dto = new UserDTO();
      dto.setId(user.getId());
      dto.setName(user.getName());
      dto.setEmail(user.getEmail());
      return dto;
  }
  ```

#### **b. Using Libraries for Object Mapping**
1. **MapStruct**:
   - A compile-time, type-safe, and fast mapper for Java objects.
   - Uses annotations to generate mapping implementations.
   - Example:
     ```java
     @Mapper
     public interface UserMapper {
         UserDTO toDto(User user);
         User toEntity(UserDTO dto);
     }
     ```

   - The implementation is generated during build time.

2. **ModelMapper**:
   - A runtime object mapping library.
   - Automatically maps objects based on field names and types.
   - Example:
     ```java
     ModelMapper modelMapper = new ModelMapper();
     UserDTO userDTO = modelMapper.map(user, UserDTO.class);
     ```

3. **Dozer**:
   - Another runtime mapping library with XML or annotation-based configuration.
   - Example:
     ```java
     Mapper mapper = new DozerBeanMapper();
     UserDTO userDTO = mapper.map(user, UserDTO.class);
     ```

---

### **2. Mapping Objects to JSON/XML**
For serializing and deserializing Java objects to JSON or XML, Spring Boot provides built-in support:

#### **a. Jackson (Default for JSON in Spring Boot)**
- Automatically maps Java objects to JSON and vice versa using `ObjectMapper`.
- Used in REST APIs to send/receive JSON.
- Example:
  ```java
  ObjectMapper objectMapper = new ObjectMapper();
  String json = objectMapper.writeValueAsString(user);
  User user = objectMapper.readValue(json, User.class);
  ```

#### **b. Gson**
- An alternative library for JSON mapping.
- Example:
  ```java
  Gson gson = new Gson();
  String json = gson.toJson(user);
  User user = gson.fromJson(json, User.class);
  ```

#### **c. JAXB (For XML Mapping)**
- For mapping Java objects to XML and vice versa.
- Requires annotations like `@XmlRootElement`, `@XmlElement`.

---

### **3. Entity to Database Mapping (ORM)**
For mapping Java objects to database tables, Spring Boot uses **JPA (Java Persistence API)** with Hibernate as the default provider:

- **Entity Class**: Annotated with `@Entity`, `@Table`, and field-specific annotations like `@Id`, `@Column`.
- Example:
  ```java
  @Entity
  @Table(name = "users")
  public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(name = "name")
      private String name;

      @Column(name = "email")
      private String email;
  }
  ```

---

### **4. Using Spring Data**
Spring Data JPA automatically maps database rows to Java objects and vice versa:
- **Repositories** provide query methods to map database results into objects.
- Example:
  ```java
  @Repository
  public interface UserRepository extends JpaRepository<User, Long> {
      Optional<User> findByEmail(String email);
  }
  ```

---

### **5. Combining Mappings in a REST API**
A typical use case in Spring Boot combines these mappings:
1. **Entity** ↔ **DTO** using `MapStruct` or `ModelMapper`.
2. **DTO** ↔ **JSON/XML** using Jackson or JAXB.

---

### **Best Practices**
- Use **MapStruct** for compile-time, fast, and type-safe mappings.
- Prefer **Jackson** for JSON handling, as it's the default in Spring Boot.
- For complex mappings, consider customizing the mapper configuration.
- Use JPA annotations for database-object mappings, and DTOs for separation of concerns in APIs.