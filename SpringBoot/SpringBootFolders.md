# Spring Boot Folders

In a Spring Boot Java project, there are several additional folders that can be used to further organize your codebase. Here's an expanded list of commonly used folders:

1. **Controller**

* Purpose: Manages HTTP requests and directs them to the appropriate service layer.
* Contents: REST controllers, MVC controllers.
* Example: UserController.java

```java
package com.example.projectname.controller;

import com.example.projectname.dto.UserDto;
import com.example.projectname.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}
```

2. **Service**

* Purpose: Contains business logic.
* Contents: Service classes, annotated with @Service.
* Example: UserService.java

```java
package com.example.projectname.service;

import com.example.projectname.dto.UserDto;
import com.example.projectname.model.User;
import com.example.projectname.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public UserDto createUser(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getEmail());
        user = userRepository.save(user);
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
```

3. **Repository (or dao)**

* Purpose: Manages data access and interactions with the database.
* Contents: Repository interfaces extending JpaRepository or CrudRepository, DAO classes.
* Example: UserRepository.java

```java
package com.example.projectname.repository;

import com.example.projectname.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
```

4. **Model (or entity)**

* Purpose: Represents the data model of the application.
* Contents: Entity classes mapped to database tables.
* Example: User.java

```java
package com.example.projectname.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // Constructors, getters, and setters

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

5. **DTO**

* Purpose: Transfers data between different layers of the application.
* Contents: Data Transfer Object classes.
* Example: UserDto.java

```java
package com.example.projectname.dto;

public class UserDto {

    private Long id;
    private String name;
    private String email;

    // Constructors, getters, and setters

    public UserDto() {}

    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

6. **Exception**

* Purpose: Manages custom exceptions and global exception handling.
* Contents: Custom exception classes, exception handler classes (@ControllerAdvice).
* Example: UserNotFoundException.java

```java
package com.example.projectname.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
```


7. **Config**

* Purpose: Holds configuration classes for various application settings.
* Contents: Security configurations, bean configurations, web configurations.
* Example: SecurityConfig.java

```java
package com.example.projectname.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/public/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll()
            .and()
            .logout().permitAll();
    }
}
```

8. **Interceptor**

* Purpose: Intercepts HTTP requests for cross-cutting concerns like logging, authentication.
* Contents: Interceptor classes implementing HandlerInterceptor.
* Example: RequestLoggingInterceptor.java

```java
package com.example.projectname.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Incoming request data: " + request.getRequestURI());
        return true;
    }
}
```

9. **Aspect**

* Purpose: Manages cross-cutting concerns using Aspect-Oriented Programming (AOP).
* Contents: Aspect classes annotated with @Aspect.
* Example: LoggingAspect.java

```java
package com.example.projectname.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.projectname.service.*.*(..))")
    public void logBeforeMethod() {
        System.out.println("Method execution started.");
    }
}
```

10. **Util**

* Purpose: Provides utility methods and helper functions.
* Contents: Utility classes with static methods.
* Example: DateUtils.java

```java
package com.example.projectname.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }
}
```

11. **Enums**

* Purpose: Holds enumerations used throughout the application.
* Contents: Enum classes defining a fixed set of constants.
* Example: UserRole.java

```java
package com.example.projectname.enums;

public enum UserRole {
    ADMIN,
    USER,
    GUEST
}
```

12. **Constants**

* Purpose: Stores constant values used across the application.
* Contents: Classes with public static final fields.
* Example: AppConstants.java

```java
package com.example.projectname.constants;

public class AppConstants {

    public static final String DEFAULT_USER_ROLE = "USER";
    public static final int MAX_LOGIN_ATTEMPTS = 5;
}
```

13. **Factory**

* Purpose: Encapsulates object creation logic.
* Contents: Factory classes that create and return instances of objects, often using the Factory Method pattern.
* Example: UserFactory.java

```java
package com.example.projectname.factory;

import com.example.projectname.model.User;

public class UserFactory {

    public static User createUser(String name, String email) {
        return new User(name, email);
    }
}
```

14. **Mapper**

* Purpose: Converts between different object types, typically DTOs and entities.
* Contents: Mapper classes or interfaces, often using libraries like MapStruct.
* Example: UserMapper.java

```java
package com.example.projectname.mapper;

import com.example.projectname.dto.UserDto;
import com.example.projectname.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public static User toEntity(UserDto userDto) {
        return new User(userDto.getName(), userDto.getEmail());
    }
}
```

15. **Validator**

* Purpose: Custom validation logic for specific scenarios.
* Contents: Classes implementing validation logic, often using @Validator or implementing ConstraintValidator.
* Example: EmailValidator.java

```java
package com.example.projectname.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && email.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
    }
}
```

16. **Converter**

* Purpose: Converts data between different formats or types.
* Contents: Converter classes implementing Converter<S, T> or custom conversion logic.
* Example: StringToDateConverter.java

```java
package com.example.projectname.converter;

import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public Date convert(String source) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }
}
```

17. **Properties**

* Purpose: Stores application-specific properties and configuration settings.
* Contents: Classes annotated with @ConfigurationProperties.
* Example: DatabaseProperties.java

```java
package com.example.projectname.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "database")
public class DatabaseProperties {

    private String url;
    private String username;
    private String password;

    // Getters and Setters
}
```

18. **Handler**

* Purpose: Handles specific tasks, often related to event-driven programming or exception handling.
* Contents: Handler classes for various events or processes.
* Example: AuthenticationFailureHandler.java

```java
package com.example.projectname.handler;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    protected void onAuthenticationFailure(HttpServletRequest request,
                                           HttpServletResponse response,
                                           AuthenticationException exception) throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);
        System.out.println("Authentication failed: " + exception.getMessage());
    }
}
```

19. **Filter**

* Purpose: Applies filters to incoming requests, useful for pre-processing.
* Contents: Filter classes implementing javax.servlet.Filter.
* Example: CorsFilter.java

```java
package com.example.projectname.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
```

20. **Scheduler**

* Purpose: Manages scheduled tasks and cron jobs.
* Contents: Classes annotated with @Scheduled to run tasks at fixed intervals.
* Example: ReportScheduler.java

```java
package com.example.projectname.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportScheduler {

    @Scheduled(cron = "0 0 12 * * ?")
    public void generateDailyReport() {
        System.out.println("Generating daily report at noon...");
    }
}
```

21. **Event**

* Purpose: Manages events within the application for decoupled communication.
* Contents: Event classes and listeners.
* Example: UserCreatedEvent.java

```java
package com.example.projectname.event;

import com.example.projectname.model.User;
import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {

    private final User user;

    public UserCreatedEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
```

22. **Observer**

* Purpose: Implements the Observer pattern for event-driven systems.
* Contents: Observer interfaces and classes.
* Example: UserObserver.java

```java
package com.example.projectname.observer;

import java.util.ArrayList;
import java.util.List;

public class UserObserver {

    private final List<Runnable> listeners = new ArrayList<>();

    public void registerListener(Runnable listener) {
        listeners.add(listener);
    }

    public void notifyAllListeners() {
        listeners.forEach(Runnable::run);
    }
}
```

23. **Template**

* Purpose: Provides pre-defined templates, often for communication (like emails).
* Contents: Template classes or files.
* Example: EmailTemplate.java

```java
package com.example.projectname.template;

public class EmailTemplate {

    public static String getWelcomeEmailTemplate(String username) {
        return "Dear " + username + ",\n\nWelcome to our service!\n\nBest Regards,\nTeam";
    }
}
```

24. **Task**

* Purpose: Manages asynchronous tasks.
* Contents: Task classes, often using @Async or Runnable.
* Example: DataProcessingTask.java

```java
package com.example.projectname.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DataProcessingTask {

    @Async
    public void processLargeDataSet() {
        // Simulate processing
        System.out.println("Processing large data set asynchronously...");
    }
}
```

25. **Listener**

* Purpose: Handles events in an event-driven architecture.
* Contents: Classes annotated with @EventListener.
* Example: UserEventListener.java

```java
package com.example.projectname.listener;

import com.example.projectname.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        System.out.println("User created: " + event.getUser().getName());
    }
}
```

26. **Adapter**

* Purpose: Adapts interfaces for compatibility between different components.
* Contents: Adapter classes implementing adapter patterns.
* Example: UserAdapter.java

```java
package com.example.projectname.adapter;

import com.example.projectname.dto.UserDto;
import com.example.projectname.model.User;

public class UserAdapter {

    public static UserDto adaptToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
```

27. **Factory**

* Purpose: Manages object creation, often with complex construction logic.
* Contents: Factory classes implementing creational patterns.
* Example: ConnectionFactory.java

Example Folder Structure:

```java
package com.example.projectname.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

```arduino

src/
 ├── main/
 │   ├── java/
 │   │   └── com/example/projectname/
 │   │       ├── controller/
 │   │       ├── service/
 │   │       ├── repository/
 │   │       ├── model/
 │   │       ├── dto/
 │   │       ├── config/
 │   │       ├── interceptor/
 │   │       ├── aspect/
 │   │       ├── util/
 │   │       ├── enums/
 │   │       ├── constants/
 │   │       ├── factory/
 │   │       ├── mapper/
 │   │       ├── validator/
 │   │       ├── converter/
 │   │       ├── properties/
 │   │       ├── handler/
 │   │       ├── filter/
 │   │       ├── scheduler/
 │   │       ├── event/
 │   │       ├── observer/
 │   │       ├── template/
 │   │       ├── task/
 │   │       ├── listener/
 │   │       ├── adapter/
 │   │       └── factory/
 │   └── resources/
 │       ├── static/
 │       ├── templates/
 │       └── application.properties
 └── test/
     ├── java/
     │   └── com/example/projectname/
     └── resources/

```

This structure is adaptable based on project requirements and helps maintain a clean, organized, and scalable codebase.