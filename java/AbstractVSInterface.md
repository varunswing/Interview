Abstract Class vs Interface in Java
Both abstract classes and interfaces are used to achieve abstraction in Java, but they have distinct characteristics and use cases. Below is a detailed comparison between the two.

1. Purpose
Abstract Class:

Used to share common behavior among subclasses while allowing some methods to have a default implementation.
Can have both abstract (unimplemented) and concrete (implemented) methods.
Interface:

Used to define a contract for classes to implement. It specifies what methods a class must implement, but not how they should be implemented.
All methods in an interface are abstract by default (until Java 8, after which default methods can be included).

2. Method Definition
Abstract Class:

Can have a mix of abstract methods (methods without implementation) and concrete methods (methods with implementation).
```java
abstract class Animal {
    abstract void makeSound();  // Abstract method
    
    public void sleep() {       // Concrete method
        System.out.println("Sleeping...");
    }
}
```

Interface:

In earlier versions of Java, all methods in an interface were implicitly abstract (no implementation). From Java 8 onwards, interfaces can have default methods (with implementation) and static methods.
```java
interface Animal {
    void makeSound(); // Abstract method
    
    default void sleep() { // Default method with implementation
        System.out.println("Sleeping...");
    }
}
```

3. Multiple Inheritance
Abstract Class:

A class can only extend one abstract class. Java doesn't support multiple inheritance with classes.
```java
abstract class Vehicle {
    // Common functionality for vehicles
}

class Car extends Vehicle {
    // Car-specific functionality
}
```

Interface:

A class can implement multiple interfaces, allowing for more flexible design.
```java
interface Animal {
    void makeSound();
}

interface Runnable {
    void run();
}

class Dog implements Animal, Runnable {
    public void makeSound() {
        System.out.println("Bark");
    }
    
    public void run() {
        System.out.println("Dog is running");
    }
}
```

4. Fields
Abstract Class:

Can have instance variables (fields) with any access modifier (private, protected, or public).
```java
abstract class Vehicle {
    protected String model;
    
    abstract void drive();
    
    public String getModel() {
        return model;
    }
}
```

Interface:

Can only have constants (fields declared as public static final). Variables in an interface are implicitly public, static, and final.
```java
interface Car {
    int MAX_SPEED = 180;  // Implicitly public static final
    void drive();
}
```

5. Constructors
Abstract Class:

Can have constructors, which can be used to initialize state or perform setup for the abstract class.
```java
abstract class Animal {
    String name;
    
    Animal(String name) {
        this.name = name;
    }
}
```

Interface:

Cannot have constructors. Interfaces cannot be used to instantiate objects, so they do not have constructors.
6. Access Modifiers
Abstract Class:

Can have methods with any access modifier (public, protected, private).
Interface:

All methods are public by default (before Java 9), but Java 9 introduced the concept of private methods in interfaces.
7. When to Use
Abstract Class:

Use an abstract class when:
You want to provide partial implementation or common behavior to subclasses.
You want to define shared fields or state that can be inherited by subclasses.
You need constructors or non-public methods.
Interface:

Use an interface when:
You want to define a contract that classes must follow.
You want to achieve multiple inheritance.
You are defining pure abstraction without implementation details.
8. Java 8 and Beyond Changes
Abstract Class:
Java 8 did not introduce any specific changes for abstract classes, but they continue to serve the same purpose with a mix of concrete and abstract methods.
Interface:
Java 8 introduced default methods and static methods in interfaces, allowing some level of implementation.
Java 9 introduced private methods in interfaces, allowing code reusability within the interface itself.
```java
interface MyInterface {
    private void helperMethod() {
        // private logic to be reused
    }
    
    default void doSomething() {
        helperMethod();
        System.out.println("Doing something...");
    }
}
```

Example of Abstract Class vs Interface
Abstract Class Example:
```java
abstract class Animal {
    abstract void makeSound();  // Abstract method
    
    public void sleep() {       // Concrete method
        System.out.println("Sleeping...");
    }
}

class Dog extends Animal {
    public void makeSound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    public void makeSound() {
        System.out.println("Meow");
    }
}
```

Interface Example:
```java
interface Animal {
    void makeSound();  // Abstract method
    
    default void sleep() {
        System.out.println("Sleeping...");
    }
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Bark");
    }
}

class Cat implements Animal {
    public void makeSound() {
        System.out.println("Meow");
    }
}
```

Key Differences

Feature	Abstract Class	Interface
Methods	Can have both abstract and concrete methods	Only abstract methods (Java 8+ allows default methods)
Fields	Can have instance variables	Can only have public static final constants
Multiple Inheritance	Only one abstract class can be extended	Multiple interfaces can be implemented
Constructors	Can have constructors	Cannot have constructors
Use Case	Partial implementation, shared state, common behavior	Contract for what a class must do, multiple inheritance
