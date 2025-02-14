Lambda Expressions

Functional Interfaces
Method References
Default Methods in Interfaces
Stream API
Optional Class
java.time Package (JSR-310)
Nashorn JavaScript Engine
Base64 Encoding and Decoding
Parallel Array Sorting
Type Annotations
Repeating Annotations
Improved Type Inference
Method Parameter Reflection
New JavaFX Features
Collectors Utility Class
Enhanced java.util.concurrent package
Static Methods in Interfaces
Performance Improvements


## 1. Lambda Expressions
Lambda expressions provide a clear and concise way to represent one method interface using an expression. They enable you to treat functionality as a method argument or to create a concise function.

```java
List<String> list = Arrays.asList("apple", "banana", "cherry");
list.forEach(item -> System.out.println(item));
```

## 2. Stream API
The Stream API allows you to process sequences of elements, such as collections, in a functional style. It supports operations like filtering, mapping, and reducing.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
int sum = numbers.stream()
                 .filter(n -> n % 2 == 0)
                 .reduce(0, Integer::sum);
System.out.println("Sum of even numbers: " + sum);
```

## 3. Optional Class
The Optional class is a container that may or may not contain a non-null value. It is used to avoid NullPointerException and to express the possibility of absence of a value explicitly.

```java
Optional<String> optional = Optional.of("Hello");
optional.ifPresent(System.out::println);
```

## ## 4. java.time Package (JSR-310)
The java.time package provides a comprehensive API for date and time manipulation, including classes like LocalDate, LocalTime, LocalDateTime, and ZonedDateTime.

```java
LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(1990, Month.JANUARY, 1);
Period age = Period.between(birthday, today);
System.out.println("Age: " + age.getYears());
```

## 5. Base64 Encoding and Decoding
Java 8 provides a new utility class for Base64 encoding and decoding.

```java
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        String originalInput = "test input";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println("Encoded String: " + encodedString);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        System.out.println("Decoded String: " + decodedString);
    }
}
```

## 6. Functional Interfaces
Functional interfaces are interfaces with a single abstract method, and they can be implemented using lambda expressions. Java 8 includes several built-in functional interfaces in the java.util.function package.

```java
@FunctionalInterface
interface MyFunctionalInterface {
    void myMethod();
}
MyFunctionalInterface instance = () -> System.out.println("Lambda expression");
instance.myMethod();
```

## 7. Method References
Method references provide a way to refer to methods without invoking them. They are compact and easy-to-read lambda expressions for methods.

```java
List<String> list = Arrays.asList("apple", "banana", "cherry");
list.forEach(System.out::println);
```

## 8. New Date and Time API
The new date and time API is thread-safe and more comprehensive compared to the old java.util.Date and java.util.Calendar classes.

```java
LocalDateTime now = LocalDateTime.now();
System.out.println("Current Date and Time: " + now);
```

## 9. Parallel Array Sorting
Java 8 introduced a new method for parallel sorting of arrays.

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {5, 3, 1, 4, 2};
        Arrays.parallelSort(array);
        System.out.println(Arrays.toString(array));
    }
}
```

## 10. Default Methods in Interfaces
Interfaces can now have default methods, which provide default implementations. This helps in extending interfaces without breaking existing implementations.

```java
interface MyInterface {
    default void myMethod() {
        System.out.println("Default implementation");
    }
}
```

## 11. Collectors class
The Collectors class in the Stream API provides various methods to collect stream elements into collections like lists, sets, or maps.

```java
List<String> list = Arrays.asList("apple", "banana", "cherry");
List<String> filteredList = list.stream()
                                .filter(s -> s.startsWith("a"))
                                .collect(Collectors.toList());
System.out.println(filteredList);
```

## 12. Nashorn JavaScript Engine
Java 8 includes a new JavaScript engine called Nashorn, which allows you to run JavaScript code directly on the Java Virtual Machine (JVM).

```java
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        engine.eval("print('Hello from JavaScript')");
    }
}
```

These features collectively make Java 8 a significant release that encourages more readable, maintainable, and efficient code through functional programming and improved API design.