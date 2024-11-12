Generics in Java enable types (classes and interfaces) to be parameters when defining classes, interfaces, and methods. They provide strong type-checking at compile-time and eliminate the need for casting, making code more robust and readable. Here’s a breakdown of how generics work in Java:

### 1. **Basic Generic Syntax**
   Generics use angle brackets (`<>`) to specify a type parameter. This parameter can be replaced with an actual type when the class or method is instantiated.

   ```java
   public class Box<T> {
       private T content;

       public void setContent(T content) {
           this.content = content;
       }

       public T getContent() {
           return content;
       }
   }
   ```

   - `T` is a type parameter (a placeholder for an actual type).
   - When you create a `Box` object, specify the type: `Box<String> box = new Box<>();`.

### 2. **Benefits of Using Generics**
   - **Type Safety**: Catch errors at compile-time rather than runtime.
   - **Elimination of Casting**: Generics eliminate the need for casting when retrieving an element.
   - **Code Reusability**: Define one generic class or method that can work with any object type.

### 3. **Generic Methods**
   You can define generic methods in both generic and non-generic classes.

   ```java
   public class Utility {
       public static <T> void printArray(T[] array) {
           for (T element : array) {
               System.out.println(element);
           }
       }
   }
   ```

   - Here, `<T>` specifies that `printArray` is a generic method.
   - This method can now be used for any type of array: `Utility.printArray(new String[]{"a", "b"});`.

### 4. **Bounded Type Parameters**
   You can restrict the types that can be used as generics using bounds.

   ```java
   public class NumberBox<T extends Number> {
       private T number;

       public NumberBox(T number) {
           this.number = number;
       }

       public T getNumber() {
           return number;
       }
   }
   ```

   - `<T extends Number>` means `T` can be any subclass of `Number` (e.g., `Integer`, `Double`).

### 5. **Wildcards in Generics**
   Wildcards are used when the exact type is not specified.

   - **Unbounded Wildcard (`<?>`)**: Accepts any type.
     ```java
     public void printList(List<?> list) {
         for (Object obj : list) {
             System.out.println(obj);
         }
     }
     ```

   - **Upper Bounded Wildcard (`<? extends Type>`)**: Accepts a type or any subtype of the specified type.
     ```java
     public void sumList(List<? extends Number> list) {
         double sum = 0;
         for (Number n : list) {
             sum += n.doubleValue();
         }
         System.out.println("Sum: " + sum);
     }
     ```

   - **Lower Bounded Wildcard (`<? super Type>`)**: Accepts a type or any supertype of the specified type.
     ```java
     public void addIntegers(List<? super Integer> list) {
         list.add(10);
         list.add(20);
     }
     ```

### 6. **Type Erasure**
   - Java uses type erasure to replace generic types with their bounds or `Object` at runtime.
   - This means generic information is not available at runtime, and Java does not support primitives in generics (`List<int>` is invalid; use `List<Integer>`).

### 7. **Common Use Cases for Generics**
   - **Collections**: `List<String>`, `Map<Integer, String>`, etc.
   - **Utility classes**: `Optional<T>`, `Comparable<T>`.
   - **Custom generic classes**: Like a pair class `Pair<K, V>` to hold two related values.

### Example: Generic Class, Method, and Wildcards

```java
import java.util.List;

public class Container<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    // Generic method with a bounded type parameter
    public static <U extends Number> void printNumbers(List<U> numbers) {
        for (U num : numbers) {
            System.out.println(num);
        }
    }

    // Method with an unbounded wildcard
    public void printItems(List<?> items) {
        for (Object item : items) {
            System.out.println(item);
        }
    }
}

```

Generics provide flexibility while enforcing type safety, making Java collections and custom classes much more versatile. Let me know if you’d like more examples on specific scenarios!