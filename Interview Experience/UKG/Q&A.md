# Java SpringBoot Questions

## Que 1. 4 pillars of OOPs in java

Here are the 4 pillars of Object-Oriented Programming (OOP) in Java:

1. Encapsulation
Definition: Encapsulation is the concept of bundling data and methods that operate on that data within a single unit, making it harder for other parts of the program to access or modify the data directly.
Key aspects:
Data hiding
Abstraction
Access modifiers (public, private, protected)
Example (Java):
Java
public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
2. Abstraction
Definition: Abstraction is the practice of showing only the necessary information to the outside world while hiding the implementation details.
Key aspects:
Interfaces
Abstract classes
Simplifying complex systems
Example (Java):
Java
public interface Shape {
    double area();
}

public abstract class AbstractShape implements Shape {
    // Common implementation
}

public class Circle extends AbstractShape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}
3. Inheritance
Definition: Inheritance is the mechanism by which one class can inherit the properties and behavior of another class.
Key aspects:
Parent-child relationships
Code reuse
Polymorphism
Example (Java):
Java
public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public void sound() {
        System.out.println("Generic animal sound");
    }
}

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println("Woof!");
    }
}
4. Polymorphism
Definition: Polymorphism is the ability of an object to take on multiple forms, depending on the context.
Key aspects:
Method overriding
Method overloading
Operator overloading
Example (Java):
Java
public class Rectangle {
    private double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }
}

public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }
}

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(4, 5);
        Circle circle = new Circle(3);

        System.out.println(calculateArea(rectangle));  // Output: 20.0
        System.out.println(calculateArea(circle));     // Output: 28.274333882308138
    }

    public static double calculateArea(Shape shape) {
        return shape.area();
    }
}

interface Shape {
    double area();
}
These four pillars provide the foundation for object-oriented programming in Java and enable developers to create robust, maintainable, and scalable software systems.

## Que 2. can we override private method of parent class

No, private methods of a parent class cannot be overridden in a child class. This is because private methods are not accessible outside the class they are defined in, including in any subclass. Private methods are restricted to the scope of the class itself, meaning the child class does not inherit or have access to them.

However, you can define a method with the same name in the child class, but this would be a completely new method, not an overridden one. This concept is known as method hiding, not overriding.

Example:

```java
class Parent {
    private void display() {
        System.out.println("Parent display");
    }
}

class Child extends Parent {
    private void display() {
        System.out.println("Child display");
    }
}

public class Test {
    public static void main(String[] args) {
        Child obj = new Child();
        // obj.display(); // This won't work because 'display' is private in both classes
    }
}
```
In this case, both the parent and child classes have their own private display() methods, but they are not related by inheritance, so the child's display() method does not override the parent's method.


## Que 3. parent class public scope and child class private scope is it possible

No, it is not possible to override a method in the child class with a more restrictive access modifier than the parent class method.

In Java (and most OOP languages), when you override a method from a parent class in a child class, the access level of the overridden method must be the same or more permissive. You cannot reduce the visibility of a method during inheritance.

If the parent class method is public, the overridden method in the child class must also be public.
If the parent class method is protected, the overridden method in the child class can be protected or public, but not private.
This ensures that the child class does not break the contract of the parent class by making a method less accessible.

Example:

```java
class Parent {
    public void show() {
        System.out.println("Parent show");
    }
}

class Child extends Parent {
    // Error: attempting to assign weaker access privileges; was public
    private void show() {
        System.out.println("Child show");
    }
}
```

In this example, trying to override the public method show() in the parent class with a private method in the child class will result in a compile-time error.

Rule:
You can increase visibility (protected → public), but you cannot decrease it (public → private).

## Que 4. what happens when we create instance of child class, child is extending parent class what happens in background


Here's a step-by-step explanation of what happens in the background when you create an instance of a child class that extends a parent class:
Step 1: Class Loading

When you create an instance of the child class, the Java Virtual Machine (JVM) loads the child class and its parent class into memory.

Step 2: Parent Class Initialization

Before initializing the child class, the JVM initializes the parent class:
Memory allocation for parent class variables
Execution of parent class static initializers (if any)
Execution of parent class constructors (in the order of inheritance)

Step 3: Child Class Initialization

After the parent class is initialized, the JVM initializes the child class:
Memory allocation for child class variables
Execution of child class static initializers (if any)
Execution of child class constructors (in the order of inheritance)
Constructor Execution Order
When you create an instance of the child class, the constructors are executed in the following order:
1. Parent class's no-arg constructor ( implicit or explicit)
2. Parent class's instance initialization blocks (if any)
3. Child class's no-arg constructor (implicit or explicit)
4. Child class's instance initialization blocks (if any)
Example

```Java
public class Parent {
    { System.out.println("Parent instance init"); }
    public Parent() {
        System.out.println("Parent constructor");
    }
}

public class Child extends Parent {
    { System.out.println("Child instance init"); }
    public Child() {
        System.out.println("Child constructor");
    }
}

public class Main {
    public static void main(String[] args) {
        Child child = new Child();
    }
}
```
Output

Parent instance init
Parent constructor
Child instance init
Child constructor

Memory Layout

When you create an instance of the child class, the JVM allocates memory for:

Parent class variables
Child class variables
Method table (contains pointers to methods)

The child class instance has access to:

Parent class variables (inherited)
Child class variables
Parent class methods (inherited)
Child class methods (overridden or new)

Conclusion

Creating an instance of a child class involves:
1. Class loading
2. Parent class initialization
3. Child class initialization
4. Constructor execution in the order of inheritance
5. Memory allocation for variables and method tables

This process ensures that the child class instance has access to the necessary variables and methods from both the parent and child classes.


## Que 5. when we create child object, parent is also created or not


When you create a child object, a parent object is also created, but not explicitly.
Inheritance and Object Creation
In Java, when you create a child object, the child class's constructor is called. However, before the child class's constructor is executed, the parent class's constructor is called implicitly.
Implicit Parent Class Construction
The Java compiler automatically inserts a call to the parent class's no-arg constructor (if it exists) at the beginning of the child class's constructor. This ensures that the parent class is properly initialized before the child class is initialized.
Example

```Java
public class Parent {
    public Parent() {
        System.out.println("Parent constructor");
    }
}

public class Child extends Parent {
    public Child() {
        System.out.println("Child constructor");
    }
}

public class Main {
    public static void main(String[] args) {
        Child child = new Child();
    }
}
```

Output
Parent constructor
Child constructor
Memory Allocation
When you create a child object, memory is allocated for:
Parent class variables (inherited)
Child class variables
Method table (contains pointers to methods)
The child object has a single memory block that includes both parent and child class variables.
Is-a Relationship
The child object "is-a" parent object, meaning it has all the properties and behavior of the parent class, plus additional properties and behavior specific to the child class.
Proof of Parent Object Creation
To demonstrate that a parent object is created, consider the following example:

```Java
public class Parent {
    public Parent() {
        System.out.println("Parent constructor");
        printMessage();
    }

    public void printMessage() {
        System.out.println("Parent message");
    }
}

public class Child extends Parent {
    public Child() {
        System.out.println("Child constructor");
    }

    @Override
    public void printMessage() {
        System.out.println("Child message");
    }
}

public class Main {
    public static void main(String[] args) {
        Child child = new Child();
    }
}
```

Output
Parent constructor
Child message
Child constructor
In this example, the printMessage() method is called from the parent class constructor, but the child class's overridden method is executed. This proves that the parent object is created and initialized before the child object.
Conclusion
When you create a child object:
The parent class constructor is called implicitly.
Memory is allocated for parent and child class variables.
The child object has access to parent class variables and methods.
The parent object is created implicitly as part of the child object creation process.

## Que 6. upcasting and down casting

In Java (and many object-oriented programming languages), upcasting and downcasting are related to typecasting when dealing with class inheritance and polymorphism.

1. Upcasting:
Upcasting refers to casting a child object to a parent class reference.
This is safe and automatic because the child class object is guaranteed to have all the properties and behaviors of the parent class (due to inheritance).
Upcasting is often used to take advantage of polymorphism, where a parent class reference can refer to any of its child objects.
Example:

```java
class Parent {
    void show() {
        System.out.println("Parent show");
    }
}

class Child extends Parent {
    void show() {
        System.out.println("Child show");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Child(); // Upcasting
        p.show();  // Output: Child show (due to method overriding)
    }
}
```

In the example, a Child object is upcasted to a Parent reference. Even though the reference is of type Parent, the overridden show() method of the Child class is called. This is because of runtime polymorphism (dynamic method dispatch).
2. Downcasting:
Downcasting is the process of casting a parent class reference back to a child class reference.
This is not safe and requires an explicit cast because not all parent objects are instances of the child class. If the object is not actually of the child class, a ClassCastException will be thrown at runtime.
You should check the object type using the instanceof operator before performing a downcast to avoid this error.
Example:

```java
class Parent {
    void show() {
        System.out.println("Parent show");
    }
}

class Child extends Parent {
    void childSpecificMethod() {
        System.out.println("Child specific method");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Child();  // Upcasting (safe)
        
        // Downcasting (explicit)
        if (p instanceof Child) {
            Child c = (Child) p;  // Safe Downcasting
            c.childSpecificMethod();  // Output: Child specific method
        }
    }
}
```

In this example, p is a Parent reference pointing to a Child object (via upcasting). Before downcasting it back to a Child, we check if p is an instance of Child using the instanceof operator to avoid ClassCastException. After downcasting, we can access the child-specific methods like childSpecificMethod().
Key Differences:
Feature	Upcasting	Downcasting
Direction	From child to parent	From parent to child
Safety	Safe and automatic	Not safe, requires explicit cast
Use case	Enables polymorphism, limits access to child-specific methods	Used to access child-specific methods after upcasting
Type checking	Not needed	Must check with instanceof to avoid exceptions
Polymorphism via Upcasting:
One of the main reasons to use upcasting is to enable polymorphism, where you can treat objects of different classes uniformly through a parent class reference.

Example Scenario:
```java
class Animal {
    void sound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("Meow");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a;
        a = new Dog();  // Upcasting
        a.sound();  // Output: Bark
        
        a = new Cat();  // Upcasting
        a.sound();  // Output: Meow
    }
}
```
The method sound() behaves differently based on the actual object type (i.e., Dog or Cat) even though the reference is of type Animal (parent class). This is possible because of upcasting and method overriding.
When to Use:
Upcasting is useful when you want to treat multiple child classes as instances of the parent class for polymorphic behavior.
Downcasting is useful when you need to access child-specific functionality that isn't available in the parent class, but it should be done carefully.

## Que 7. object of child class in parent class variable what is it

When you assign an object of the child class to a parent class variable, it's called upcasting. This is a form of type casting in which a subclass (child class) object is treated as if it is an instance of its superclass (parent class). Upcasting happens implicitly in Java because a child class is always considered to be a more specific instance of the parent class.

Characteristics of this scenario:
The reference is of the parent type, but the actual object is of the child type.
You can only access the methods and fields defined in the parent class using the parent reference, even though the actual object is of the child class.
If the child class has overridden any methods of the parent class, the overridden method in the child class will be called (due to runtime polymorphism).
Example:
```java
class Parent {
    void display() {
        System.out.println("Parent display");
    }
}

class Child extends Parent {
    @Override
    void display() {
        System.out.println("Child display");
    }

    void childMethod() {
        System.out.println("Child specific method");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent obj = new Child();  // Upcasting: Child object assigned to Parent reference
        obj.display();  // Calls the Child's overridden display() method (runtime polymorphism)
        
        // obj.childMethod();  // Compile-time error: Parent reference cannot access Child-specific method
    }
}
```

Output:
arduino
Child display
Explanation:
Parent obj = new Child();:

This is upcasting, where a Child object is assigned to a Parent reference (obj).
The obj can only access methods that are available in the Parent class, even though the actual object is of the Child class.
obj.display();:

Even though the reference obj is of type Parent, at runtime, the overridden method display() from the Child class is called. This behavior is due to runtime polymorphism (dynamic method dispatch).
Child-specific methods or fields (like childMethod()) cannot be accessed using the Parent reference directly. If you try to access childMethod(), you'll get a compile-time error because the Parent class doesn't have that method. To access child-specific methods, you'd need to downcast the parent reference back to the child type.

Accessing Child-Specific Methods (Downcasting):
If you want to access the Child-specific methods after upcasting, you can use downcasting:

```java
if (obj instanceof Child) {
    Child childObj = (Child) obj;  // Downcasting
    childObj.childMethod();  // Now you can access child-specific methods
}
```

Summary:
Upcasting is when a child object is treated as a parent object, allowing you to leverage polymorphism.
You can access only the methods and fields available in the parent class through the parent reference.
Overridden methods in the child class will still be invoked.
To access child-specific methods or fields, downcasting is necessary.

Common use cases:
Collections (e.g., ArrayList<Animal>)
Method parameters (e.g., processAnimal(Animal animal))
Return types (e.g., Animal getAnimal())

## Que 8. u kept method upcasted in parent class, is that method accessible from child class

Yes, when a method in the parent class is inherited by the child class (through upcasting or simply by inheritance), that method is accessible from the child class, provided it has sufficient access level (i.e., it is public or protected and not private).

Key points:
Public and protected methods in the parent class are accessible in the child class, whether you're calling them from the child object or through an upcasted parent reference.
If the child class overrides the parent method, the child class's version of the method will be called, even if you're using a parent reference (this is due to runtime polymorphism).
Private methods in the parent class are not inherited and cannot be accessed directly from the child class.
Example:
Parent Method Accessible in Child Class (via inheritance):
```java
class Parent {
    public void parentMethod() {
        System.out.println("Parent method");
    }
}

class Child extends Parent {
    public void childMethod() {
        System.out.println("Child method");
    }
}

public class Main {
    public static void main(String[] args) {
        Child childObj = new Child();  // Creating Child class object
        childObj.parentMethod();  // Accessing parent method from child object
        childObj.childMethod();   // Accessing child method
    }
}
```

Output:
sql
Parent method
Child method
Explanation:
The method parentMethod() is defined in the parent class Parent and is inherited by the child class Child.
The child object childObj can directly access the parentMethod() because it's a public method and is inherited by the child class.
Upcasting Scenario:
If you upcast the child object to a parent reference, you can still access the parent class methods, and if the method is overridden in the child class, the overridden version will be called.

```java
class Parent {
    public void parentMethod() {
        System.out.println("Parent method");
    }
}

class Child extends Parent {
    @Override
    public void parentMethod() {
        System.out.println("Overridden Child method");
    }

    public void childMethod() {
        System.out.println("Child specific method");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent parentRef = new Child();  // Upcasting Child object to Parent reference
        parentRef.parentMethod();  // Calls Child's overridden method due to runtime polymorphism
        
        // parentRef.childMethod();  // Compile-time error: Cannot access child-specific method
    }
}
```

Output:
sql
Overridden Child method
Explanation:
Parent parentRef = new Child(); is upcasting, meaning the Child object is referenced by a Parent type variable.
The parentMethod() is called through the parentRef reference, but the overridden version in the child class is invoked (this is due to runtime polymorphism).
The childMethod() specific to the Child class cannot be accessed using the parentRef because upcasting limits access to only the methods available in the parent class.
Important Points:
Overridden methods are always called from the actual object (child class) at runtime, even if accessed through a parent class reference.
Parent methods are accessible from the child class if they are public or protected (and are not hidden by private).
Upcasting restricts access to methods defined only in the parent class (unless you downcast to access child-specific methods).

## Que 9. method defined in child class is it accessible in this case?

No, in the case of upcasting, a method that is defined only in the child class is not accessible through the parent class reference. When you upcast a child object to a parent class reference, you are essentially limiting the access to the parent class methods and fields only.

The reason for this is that the parent class reference can only see the methods and fields defined in the parent class. If the method exists only in the child class, the parent reference won't know about it unless you downcast the reference back to the child class.

Example:
```java
class Parent {
    public void parentMethod() {
        System.out.println("Parent method");
    }
}

class Child extends Parent {
    public void childMethod() {
        System.out.println("Child-specific method");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent parentRef = new Child();  // Upcasting: Child object as Parent reference
        parentRef.parentMethod();  // This works (method from Parent class)
        
        // parentRef.childMethod();  // Compile-time error: Parent reference can't see childMethod
    }
}
```

Explanation:
Parent parentRef = new Child(); — This is upcasting. The reference parentRef is of type Parent, even though it points to a Child object.
The parentRef.parentMethod() works because parentMethod() is defined in the Parent class.
The line parentRef.childMethod(); would result in a compile-time error because the Parent reference doesn't know about the childMethod() which is defined only in the Child class.
How to Access Child-Specific Methods?
To access the child-specific method (e.g., childMethod()), you would need to downcast the parent reference back to the child type.

Example with Downcasting:
```java
class Parent {
    public void parentMethod() {
        System.out.println("Parent method");
    }
}

class Child extends Parent {
    public void childMethod() {
        System.out.println("Child-specific method");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent parentRef = new Child();  // Upcasting
        parentRef.parentMethod();  // Works, calls Parent method

        // Downcasting to access child-specific method
        if (parentRef instanceof Child) {
            Child childRef = (Child) parentRef;  // Downcasting
            childRef.childMethod();  // Now we can access childMethod
        }
    }
}
```
Output:
sql
Parent method
Child-specific method
Explanation:
The first method call, parentRef.parentMethod(), works because parentMethod() is part of the Parent class.
The downcasting (Child) parentRef converts the parentRef back to a Child reference, allowing access to the child-specific method childMethod().
Summary:
Upcasting restricts access to only parent class methods.
To access child-specific methods, you need to perform downcasting.
Downcasting should be done cautiously and only after verifying the object type with the instanceof operator to avoid runtime errors (ClassCastException).

## Que 10. different types of inner classes

In Java, there are several types of inner classes (or nested classes) that allow you to define one class within another. Each type of inner class serves different purposes and has different rules for how it can access the members of the outer class.

1. Member Inner Class (Non-static inner class)
A member inner class is a non-static class that is defined inside another class. It has access to the instance variables and methods of the outer class, even private members.
It requires an instance of the outer class to be created before creating an instance of the inner class.
Example:
```java
class Outer {
    private String message = "Hello from Outer class";

    class Inner {
        void display() {
            System.out.println(message);  // Can access outer class private members
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();  // Creating instance of Inner class
        inner.display();  // Output: Hello from Outer class
    }
}
```

2. Static Nested Class
A static nested class is a static class defined inside another class.
It does not have access to the instance members of the outer class directly (it can only access the outer class's static members).
You don't need an instance of the outer class to create an instance of the static nested class.
Example:
```java
class Outer {
    static String staticMessage = "Hello from Static Nested Class";

    static class StaticNested {
        void display() {
            System.out.println(staticMessage);  // Can access static members of outer class
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Outer.StaticNested nested = new Outer.StaticNested();  // No outer class instance needed
        nested.display();  // Output: Hello from Static Nested Class
    }
}
```

3. Local Inner Class
A local inner class is defined within a method or a block of code inside the outer class.
It has access to the outer class's members and the local variables or parameters of the method, but only if those variables or parameters are final or effectively final (i.e., their values do not change after initialization).
Example:
```java
class Outer {
    void display() {
        final String localMessage = "Hello from Local Inner Class";

        class LocalInner {
            void print() {
                System.out.println(localMessage);  // Can access local variables (final/effectively final)
            }
        }

        LocalInner inner = new LocalInner();
        inner.print();
    }
}

public class Main {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.display();  // Output: Hello from Local Inner Class
    }
}
4. Anonymous Inner Class
An anonymous inner class is a class without a name and is used to create a one-time-use class, typically to implement an interface or extend a class. It's commonly used in situations where you need to provide a simple implementation of an interface or a class on the fly.
You can create it as a part of method arguments or anywhere you need a class for short-term use.
Example (Using an interface):
```java
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        // Creating an anonymous inner class implementing the Greeting interface
        Greeting greeting = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello from Anonymous Inner Class");
            }
        };
        
        greeting.sayHello();  // Output: Hello from Anonymous Inner Class
    }
}
```
Example (Extending a class):
```java
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating an anonymous inner class extending the Animal class
        Animal animal = new Animal() {
            @Override
            void sound() {
                System.out.println("Dog barks");
            }
        };

        animal.sound();  // Output: Dog barks
    }
}
```

[!Alt](images/InnerClass.png)

Each type of inner class has its own use case, depending on the needs of the application, such as handling single-use functionality (anonymous inner classes), or organizing logic in a nested structure (static or member inner classes).

## Que 11. difference between list and set

In Java, List and Set are two distinct types of collections in the java.util package. They are used to store groups of objects, but they have different characteristics and use cases. Here's a detailed comparison between them:

1. Order of Elements
List:

Ordered Collection: Elements in a List are stored in a specific order, and this order is maintained.
Index-Based Access: You can access elements using an index (e.g., list.get(index)).
Set:

Unordered Collection: Elements in a Set are not stored in any specific order. The order may not be consistent.
No Index-Based Access: You cannot access elements by index. Instead, you typically use iterators or enhanced for-loops to traverse the set.
2. Duplicate Elements
List:

Allows Duplicates: A List can contain duplicate elements. The same element can appear more than once in the list.
Set:

No Duplicates: A Set does not allow duplicate elements. If you try to add a duplicate, the Set will not change, and the existing element will remain.
3. Implementation Classes
List:

Common implementations include:
ArrayList: Provides dynamic array capabilities and is faster for random access.
LinkedList: Implements a doubly-linked list, which is more efficient for insertions and deletions.
Vector (less commonly used): Synchronized, so it is thread-safe but slower than ArrayList.
Set:

Common implementations include:
HashSet: Backed by a hash table. It provides constant-time performance for basic operations but does not guarantee the order of elements.
LinkedHashSet: Maintains insertion order by using a linked list. Provides predictable iteration order.
TreeSet: Implements a NavigableSet, which is a sorted set. It uses a Red-Black tree and provides ordered traversal of elements.
4. Performance
List:

Access: ArrayList provides O(1) time complexity for accessing elements by index, while LinkedList has O(n) time complexity.
Insertion/Deletion: LinkedList is generally more efficient for insertion and deletion operations compared to ArrayList.
Set:

HashSet: Generally provides O(1) time complexity for basic operations like add, remove, and contains.
TreeSet: Provides O(log n) time complexity for operations due to its underlying tree structure.
LinkedHashSet: Offers O(1) time complexity for basic operations but maintains insertion order, so its performance might be slightly impacted.
5. Use Cases
List:

Use List when you need an ordered collection with possible duplicate elements and require index-based access.
Set:

Use Set when you need a collection that ensures no duplicates and order is not a concern (unless using LinkedHashSet or TreeSet).
Example Code:
List Example:
```java
import java.util.*;

public class ListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Apple");  // Duplicate allowed
        System.out.println(list);  // Output: [Apple, Banana, Apple]
        System.out.println(list.get(1));  // Output: Banana
    }
}
```
Set Example:
```java
import java.util.*;

public class SetExample {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple");  // Duplicate not allowed
        System.out.println(set);  // Output: [Apple, Banana] (order may vary)
    }
}
```
[!ALT](images/ListVsSet.png)
Choose between List and Set based on your specific requirements related to order, duplicates, and access patterns.

## Que 12. insertion will be maintained in set?

[!ALT](images/InsertionOrderInSet.png)

## Que 13. how set maintain uniqueness of elements

In Java, a Set maintains the uniqueness of its elements primarily through the use of hashing. The mechanism for ensuring uniqueness can vary slightly between different implementations of the Set interface (HashSet, LinkedHashSet, TreeSet), but here’s a general overview of how it works:

1. HashSet
Hashing Mechanism:

HashSet uses a hash table to store its elements.
When an element is added to a HashSet, its hash code is computed using the hashCode() method of the element.
The hash code is used to determine the bucket in which the element will be placed. Each bucket can hold multiple elements in case of hash collisions.
Uniqueness Check:

Before adding an element, HashSet checks whether an element with the same hash code and equal value already exists in the bucket.
This check is performed using the equals() method. If the element already exists (based on equals()), the new element is not added.
If the element is not present, it is added to the bucket.
Example:
```java
import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple");  // Duplicate, will not be added

        System.out.println(set);  // Output: [Apple, Banana]
    }
}
2. LinkedHashSet
Hashing and Linked List:

LinkedHashSet combines the hash table and a linked list.
It uses the hash table for fast lookups and the linked list to maintain insertion order.
The hashing mechanism and uniqueness check are similar to HashSet.
Uniqueness Check:

The uniqueness is maintained using the same method as HashSet—by checking hash codes and using the equals() method.
Example:
```java
import java.util.LinkedHashSet;

public class LinkedHashSetExample {
    public static void main(String[] args) {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple");  // Duplicate, will not be added

        System.out.println(set);  // Output: [Apple, Banana]
    }
}
3. TreeSet
Tree Structure:

TreeSet uses a Red-Black tree (a self-balancing binary search tree) to store elements in a sorted order.
It requires that elements implement the Comparable interface or a Comparator must be provided.
Uniqueness Check:

Uniqueness is maintained by comparing elements using the compareTo() method (if elements are Comparable) or using a provided Comparator.
When adding an element, TreeSet checks if an element that is considered equal (based on comparison) already exists in the tree.
If an equal element is found, the new element is not added.
Example:
```java
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<String> set = new TreeSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple");  // Duplicate, will not be added

        System.out.println(set);  // Output: [Apple, Banana]
    }
}
```

### Comparison Summary

| Feature              | HashSet            | LinkedHashSet        | TreeSet               |
|----------------------|--------------------|----------------------|-----------------------|
| **Ordering**         | Unordered          | Insertion order      | Sorted order          |
| **Data Structure**   | Hash table         | Hash table + Linked list | Red-Black tree      |
| **Performance**      | O(1)               | O(1)                 | O(log n)              |
| **Null Values**      | Allows one `null`  | Allows one `null`    | Does not allow `null` |
| **Use Case**         | Fast access        | Ordered insertion    | Sorted elements       |

---

### Summary

- **`HashSet`**: Best for fast lookups and when order doesn’t matter.
- **`LinkedHashSet`**: Ideal when you need insertion order preservation with `HashSet` performance.
- **`TreeSet`**: Suitable for scenarios where elements need to be sorted, but with a performance trade-off.

## Que 14. HashMap

A HashMap in Java is a part of the java.util package and is one of the most commonly used data structures for storing key-value pairs. It implements the Map interface and uses hashing to store the data. Here's a detailed breakdown:

## Key Features of HashMap:
**Stores Key-Value Pairs:** Each entry in a HashMap consists of a key and a value.
**Unique Keys:** Keys must be unique within a HashMap. If you try to insert a duplicate key, the new value will overwrite the previous value.
Allows Null Values and Keys: It allows one null key and multiple null values.
**No Ordering:** HashMap does not guarantee any specific order of the elements, either in the way they are inserted or accessed.
**Performance:** Its time complexity for put() and get() operations is O(1) in the average case, making it efficient for most scenarios.
**Working of HashMap:**
Internally, HashMap uses an array of linked lists (buckets) to store data. When a key-value pair is inserted, the key's hash code is calculated, and this hash code determines the bucket in which the key-value pair is stored.
If two keys have the same hash code (hash collision), their values are stored in the same bucket, but separate using linked lists or in Java 8 and beyond, balanced trees (to optimize performance in case of too many collisions).
**Important Methods:**
**put(K key, V value):** Inserts a key-value pair into the HashMap. If the key already exists, the value is updated.
**get(Object key):** Retrieves the value associated with the specified key. Returns null if the key is not found.
**remove(Object key):** Removes the key-value pair for the specified key.
**containsKey(Object key):** Checks if a particular key is present in the HashMap.
**containsValue(Object value):** Checks if a particular value is present in the HashMap.
**size():** Returns the number of key-value pairs in the HashMap.
**isEmpty():** Checks if the HashMap is empty.

## Key Points:
Thread-Safety: HashMap is not thread-safe. For concurrent access, you should use ConcurrentHashMap or wrap it using Collections.synchronizedMap().
Load Factor and Capacity: The HashMap dynamically resizes itself based on a load factor and initial capacity. The default load factor is 0.75, which means when the HashMap is 75% full, it resizes to prevent collisions and maintain performance.


## Que 15. in key, can I store it as null

Key Points:
Overwriting Behavior: If you store a null key more than once, the latest value will overwrite the previous one.
Special Treatment: Since null has no hash code, HashMap handles it separately by placing it in a predefined bucket (index 0).
Performance:
Since null keys are stored in a specific bucket, the performance for operations involving null keys will be similar to any other key, with a constant time complexity of O(1) for put(), get(), and remove() operations, as long as there are not too many collisions.

## Que 16. collision? how to handle collision


In a `HashMap`, **collisions** occur when two or more keys produce the same hash code, resulting in them being assigned to the same bucket. Because `HashMap` uses an array of buckets to store key-value pairs, a collision means multiple entries are placed in the same bucket.

### How Collisions Are Handled in `HashMap`:
There are two primary ways to handle collisions in a `HashMap`:
1. **Chaining (Linked List)**: The most common method. When multiple key-value pairs are mapped to the same bucket, they are stored in a linked list.
2. **Tree-Based Resolution (TreeMap)**: Starting with Java 8, when the number of collisions in a single bucket exceeds a certain threshold (8 by default), the linked list is replaced with a balanced binary search tree (usually a Red-Black Tree). This improves the lookup time from O(n) to O(log n) for that bucket.

### Detailed Collision Handling Process:

#### 1. **Chaining (Linked List) Method:**
- When multiple keys have the same hash, their entries are stored in the same bucket as a linked list of `Entry` objects. Each `Entry` object contains a key, value, hash, and a reference to the next `Entry`.
- **Insertion Process**: If a collision occurs, the new key-value pair is added at the end of the linked list in that bucket.
- **Lookup Process**: To retrieve a value, the `HashMap` will first calculate the hash and determine the bucket, then iterate through the linked list to find the correct key.

#### Example:
```java
HashMap<String, Integer> map = new HashMap<>();

// Adding elements
map.put("apple", 10);
map.put("banana", 20);
map.put("orange", 30);

// Let's assume "apple" and "orange" result in the same hash value (collision)
```
If the hash codes of `"apple"` and `"orange"` result in the same bucket, both entries are stored in a linked list. During a `get()` operation, the list is traversed to find the correct key.

#### 2. **Tree-Based Resolution (Balanced Trees) in Java 8 and Later:**
- If the length of the linked list in a bucket grows beyond a certain threshold (default: 8), Java 8 replaces the linked list with a balanced tree.
- **Why Trees?** Balanced trees (such as Red-Black Trees) have a search time complexity of O(log n), which is much faster than O(n) for a linked list when dealing with many collisions.
- **Thresholds**: Once the number of entries in a bucket exceeds 8, the list is converted into a tree. If the number of entries falls below 6 (after removals), the tree is converted back into a linked list.

#### Example of Tree-Based Resolution:
- Imagine we add enough elements with hash collisions in a single bucket that exceed the threshold of 8 entries. Internally, Java converts the bucket from a linked list to a tree, speeding up search and retrieval times.

### Custom Collision Resolution Strategies:
- **Double Hashing**: A technique where a second hash function is used if a collision occurs. Not used in `HashMap`, but an alternative strategy in other data structures.
- **Linear Probing**: Another collision resolution technique where, after a collision, the algorithm searches for the next available slot in the array. This method is not used in `HashMap` but is found in some other hash table implementations.

### How Java's HashMap Balances Performance:
1. **Default Load Factor**: `HashMap` uses a default load factor of 0.75. This means that once the map is 75% full, it automatically resizes (doubles its capacity) to reduce the likelihood of collisions.
2. **Bucket Array Resizing**: When the number of entries exceeds the capacity × load factor, the bucket array is resized to prevent further collisions.

### Conclusion:
- **In Java 7 and earlier**, `HashMap` handled collisions only using **chaining** (linked list).
- **In Java 8 and later**, `HashMap` still uses **chaining**, but it optimizes performance by converting linked lists to balanced trees when too many collisions occur.

### Illustration of the Linked List to Tree Conversion:
1. **Before Java 8**: Suppose multiple keys hash to the same bucket; they are stored in a linked list.
2. **In Java 8+**: After reaching the threshold of 8 entries, the linked list transforms into a balanced tree for faster lookups.

Would you like to dive deeper into any specific implementation or explore real-life implications of hash collisions in systems?



## Que 17. handling collision before java 8 or after


### **Collision Handling Before Java 8 (Java 7 and Earlier):**

In Java 7 and earlier versions, `HashMap` handled collisions using **separate chaining** with **linked lists**. Here's a detailed explanation:

1. **Chaining with Linked Lists**: 
   - When a collision occurred (i.e., two keys were hashed to the same bucket), the entries were stored in a linked list at that bucket. Each element of the linked list contained a key-value pair (and its hash code).
   - When inserting a new key-value pair, if the hash led to a bucket that already contained elements, the new pair would be appended to the end of the linked list.
   - **Search/Access**: When retrieving a value, the `HashMap` would traverse the linked list, comparing keys using the `equals()` method to find the correct one. In the worst case (if many collisions occurred), this could degrade performance to **O(n)**, where **n** is the number of elements in that bucket.

2. **Resizing**: 
   - When the `HashMap` exceeded its load factor (default: 0.75), the array of buckets would be resized (typically doubled in size), and all entries would be **rehashed** into the new, larger array.
   - This resizing operation was **costly** in terms of performance but reduced the likelihood of collisions as the number of available buckets increased.

3. **Rehashing During Resizing**:
   - Java 7’s `HashMap` used the **head insertion** technique for adding nodes during resizing. This meant that when entries were rehashed and placed in the new bucket array, they were added to the front of the linked list.
   - **Concurrency Issues**: This could lead to subtle bugs in multithreaded environments, where during a rehash, if two threads modified the `HashMap` concurrently, it could cause an **infinite loop**. Hence, `HashMap` in Java 7 was **not thread-safe** for concurrent modifications.

### **Collision Handling After Java 8:**

Starting with Java 8, significant improvements were introduced to `HashMap` collision handling to enhance performance, especially in cases of high collisions:

1. **Chaining with Linked Lists (Same as Java 7)**:
   - Initially, collisions are still handled by using linked lists in each bucket. This behavior is the same as in earlier versions, where multiple entries with the same hash are stored in a linked list at that bucket.

2. **Tree-Based Collision Handling (New in Java 8)**:
   - **Threshold for Linked List to Tree Conversion**: When the number of entries in a bucket exceeds a threshold (default: 8), Java 8 converts the linked list in that bucket into a **balanced tree** (specifically, a Red-Black Tree).
   - **Benefits of Red-Black Trees**: In a tree, search, insertion, and deletion operations have a time complexity of **O(log n)**, where **n** is the number of entries in the tree. This is significantly faster than the **O(n)** time complexity of a linked list, especially when the bucket has a large number of entries.
   - **Threshold for Tree to Linked List Conversion**: If entries are removed and the number of nodes in the tree falls below a certain threshold (default: 6), the tree is converted back into a linked list to save memory and reduce complexity when few entries are present.

3. **Improved Resizing Mechanism**:
   - **Tail Insertion**: In Java 8, the resizing mechanism changed from head insertion to **tail insertion**, which resolved the infinite loop problem present in multithreaded environments in Java 7.
   - During resizing, entries were now rehashed into the new array, maintaining the order of the linked list and avoiding the concurrency bugs that plagued Java 7.

4. **Hash Function Improvement**: 
   - Java 8 introduced improvements to the hash function to better distribute the hash values across the bucket array. This reduced the probability of collisions, even when poorly distributed hash codes were used by certain keys.

### **Summary of Key Differences Between Java 7 and Java 8:**

| Feature/Version              | **Java 7 (and earlier)**                        | **Java 8 (and later)**                                      |
|------------------------------|-------------------------------------------------|------------------------------------------------------------|
| **Collision Handling**        | Chaining with linked lists                     | Chaining with linked lists, converted to Red-Black Trees after 8 entries |
| **Time Complexity**           | O(n) in the worst case for collisions           | O(log n) in the worst case for collisions (after tree conversion) |
| **Rehashing on Resize**       | Head insertion during rehashing (can cause loops) | Tail insertion during rehashing (prevents infinite loops)    |
| **Thread Safety**             | Not thread-safe; prone to concurrency issues    | Still not thread-safe, but reduced risk of concurrency-related issues |
| **Thresholds for Treeing**    | Not applicable                                 | Converts list to tree after 8 nodes, reverts to list below 6 nodes |

### Example of Linked List vs. Tree in Java 8:
```java
HashMap<String, Integer> map = new HashMap<>();

// Adding entries that cause collisions
map.put("key1", 1);
map.put("key2", 2);
// Assume these keys result in the same hash code, so they end up in the same bucket

// If more entries result in the same hash and bucket size exceeds 8,
// HashMap will convert the bucket's linked list into a balanced Red-Black Tree for efficiency
```

### Key Takeaways:
- **Before Java 8**, all collisions were handled by **linked lists**, leading to potential performance degradation in high-collision scenarios.
- **After Java 8**, `HashMap` still uses **linked lists** for collisions but optimizes heavily by switching to **balanced trees** when collisions become excessive, thus improving performance from **O(n)** to **O(log n)** in such cases.

If you have any further questions or would like to explore specific aspects in more depth, feel free to ask!



## Que 18. multithreading in java


Multithreading in Java is a core concept that allows concurrent execution of two or more threads for maximum utilization of CPU. Java provides built-in support for multithreading through the `Thread` class and the `Runnable` interface.

### Key Concepts in Multithreading:

1. **Thread**:
   - A thread is a lightweight process. It is the smallest unit of a program that can execute concurrently with other parts of the program.
   - Java allows the creation and management of threads via the `Thread` class and the `Runnable` interface.

2. **Multithreading**:
   - Multithreading refers to the concurrent execution of multiple threads, which allows better CPU utilization and improved application performance.

### Creating a Thread in Java:
There are two main ways to create a thread in Java:
1. **Extending the `Thread` Class**:
   - You can create a class that extends `Thread` and overrides its `run()` method.
   
2. **Implementing the `Runnable` Interface**:
   - Alternatively, you can create a class that implements the `Runnable` interface and provides the implementation for the `run()` method.

### Example 1: Extending the `Thread` Class:
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running...");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();  // Starts a new thread and calls the run method
    }
}
```

### Example 2: Implementing the `Runnable` Interface:
```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread is running...");
    }
}

public class Main {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();  // Starts a new thread and calls the run method
    }
}
```

### Key Methods of the `Thread` Class:
- **start()**: Starts the thread and invokes the `run()` method.
- **run()**: This is the entry point for the thread. When the thread is started, this method is executed.
- **sleep(long millis)**: Causes the thread to pause for a specified duration (in milliseconds).
- **join()**: Waits for the thread to die. If one thread calls `join()` on another thread, it will wait until the target thread finishes execution.
- **yield()**: Pauses the currently executing thread to allow other threads of the same priority to execute.
- **interrupt()**: Interrupts a thread, causing it to stop its execution.

### Thread Life Cycle:
A thread in Java can exist in one of the following states:
1. **New**: A thread that has been created but not yet started.
2. **Runnable**: A thread that is ready to run and is waiting for CPU time.
3. **Blocked/Waiting**: A thread that is blocked or waiting for some resource (like I/O or a lock) or another thread to complete its task.
4. **Timed Waiting**: A thread that is waiting for a specific time interval (e.g., `sleep()`).
5. **Terminated**: A thread that has completed execution or has been terminated.

### Multithreading Concepts:

1. **Concurrency**:
   - Concurrency refers to the ability of multiple threads to run simultaneously, which can be achieved either by running on multiple cores (parallelism) or by time-slicing on a single core.
   - Java provides built-in support for concurrent programming, making it easy to develop scalable applications.

2. **Synchronization**:
   - **Issue**: When multiple threads access shared resources concurrently, it can lead to inconsistent data or corrupted states (known as race conditions).
   - **Solution**: Java provides synchronization mechanisms to control thread access to shared resources.
     - **Synchronized Block/Method**: Allows only one thread at a time to execute a block of code or method.
     - **Lock Objects**: More sophisticated synchronization control via `Lock` and `ReentrantLock` classes from the `java.util.concurrent.locks` package.

### Example of Synchronization:
```java
class Counter {
    private int count = 0;

    // Synchronized method to ensure only one thread modifies count at a time
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // Creating two threads that will try to increment the counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // Wait for both threads to finish
        t1.join();
        t2.join();

        // Display the final count
        System.out.println("Count: " + counter.getCount());  // Expected: 2000
    }
}
```

### Inter-thread Communication:
- **wait(), notify(), and notifyAll()**: These methods allow threads to communicate with each other while using shared resources. These methods are used within synchronized blocks or methods.

### Example of `wait()` and `notify()`:
```java
class SharedResource {
    private int value;
    private boolean available = false;

    public synchronized void produce(int value) {
        while (available) {
            try {
                wait();  // Wait for the consumer to consume the value
            } catch (InterruptedException e) { }
        }
        this.value = value;
        available = true;
        System.out.println("Produced: " + value);
        notify();  // Notify the consumer
    }

    public synchronized void consume() {
        while (!available) {
            try {
                wait();  // Wait for the producer to produce a value
            } catch (InterruptedException e) { }
        }
        System.out.println("Consumed: " + value);
        available = false;
        notify();  // Notify the producer
    }
}

public class Main {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.produce(i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.consume();
            }
        });

        producer.start();
        consumer.start();
    }
}
```

### Advanced Multithreading Concepts:
1. **Executor Framework**: Java provides the `ExecutorService` interface in the `java.util.concurrent` package, which allows better management of thread pools and task execution.
   - **ThreadPool**: Manages a pool of worker threads. Instead of creating new threads every time, tasks are submitted to a pool of reusable threads.
   
2. **Fork/Join Framework**: For parallelism, Java provides the `ForkJoinPool` to break tasks into smaller subtasks and process them in parallel.
   
3. **Concurrency Utilities**:
   - Java also provides other concurrency utilities like `CountDownLatch`, `CyclicBarrier`, `Semaphore`, and `ConcurrentHashMap` for more sophisticated concurrent programming.

### Example of `ExecutorService`:
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            final int taskID = i;
            executor.submit(() -> {
                System.out.println("Task " + taskID + " is running");
            });
        }

        // Shutdown the executor
        executor.shutdown();
    }
}
```

### Conclusion:
Multithreading is a powerful concept in Java that allows for better CPU utilization, improved performance, and responsive applications. However, it requires careful synchronization and coordination to avoid issues like race conditions and deadlocks.

If you'd like to dive deeper into any specific multithreading topic, feel free to ask!


## Que 19. in executor what is thread pulling


In Java's `Executor` framework, **thread pooling** refers to the reuse of a fixed number of threads to execute multiple tasks, instead of creating new threads for every task. This helps manage resources efficiently and improves the performance of multithreaded applications.

### What is Thread Pooling?
Thread pooling is a technique where a pool (or collection) of threads is created beforehand and kept ready to execute tasks as they come. Once a thread completes its task, it becomes available to execute another task, rather than being destroyed.

The `Executor` framework in Java provides built-in thread pooling mechanisms through the `ExecutorService` interface and the `Executors` utility class.

### Key Benefits of Thread Pooling:
1. **Improved Performance**: Avoids the overhead of creating a new thread for every task. Creating and destroying threads are expensive operations in terms of time and system resources.
2. **Resource Management**: Limits the number of concurrent threads, preventing the system from being overwhelmed by too many threads running simultaneously.
3. **Task Queueing**: When all threads in the pool are busy, incoming tasks are placed in a queue and are executed once a thread becomes available.

### How Thread Pooling Works:
- When you submit a task to a thread pool, the task is placed in a **queue** if no threads are available at that moment.
- As soon as a thread in the pool becomes available, it pulls the task from the queue and starts executing it.
- After the task is completed, the thread does not terminate. Instead, it returns to the pool, waiting for the next task.
- This reuse of threads reduces the cost associated with thread creation and destruction.

### Example of Thread Pooling using `ExecutorService`:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // Submit 5 tasks to the thread pool
        for (int i = 1; i <= 5; i++) {
            final int taskID = i;
            threadPool.submit(() -> {
                System.out.println("Task " + taskID + " is being executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);  // Simulate a task taking some time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Shutdown the thread pool
        threadPool.shutdown();
    }
}
```

### Explanation:
- **`ExecutorService`**: This is the main interface for managing and controlling the execution of asynchronous tasks using thread pools.
- **`Executors.newFixedThreadPool(3)`**: Creates a thread pool with 3 reusable threads.
- **`submit()`**: Submits tasks to the thread pool. If all threads are busy, the tasks will be queued.
- **`shutdown()`**: Initiates an orderly shutdown where previously submitted tasks are executed but no new tasks are accepted.

### Types of Thread Pools:
1. **Fixed Thread Pool**: A thread pool with a fixed number of threads. If all threads are busy, tasks are queued until a thread becomes available.
   ```java
   ExecutorService fixedPool = Executors.newFixedThreadPool(4);
   ```

2. **Cached Thread Pool**: A thread pool that creates new threads as needed but will reuse previously created threads when available. It is ideal for executing many short-lived tasks.
   ```java
   ExecutorService cachedPool = Executors.newCachedThreadPool();
   ```

3. **Single Thread Executor**: A thread pool with only one thread. All tasks are executed sequentially.
   ```java
   ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
   ```

4. **Scheduled Thread Pool**: A thread pool designed for scheduling tasks to execute after a certain delay or repeatedly at fixed intervals.
   ```java
   ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
   ```

### Benefits of Using Thread Pools:
- **Prevents Thread Overhead**: Thread pools reduce the overhead of creating new threads for each task, allowing tasks to start executing faster.
- **Controls the Number of Active Threads**: Helps manage the number of concurrent threads in the application, preventing thread explosion.
- **Task Queueing**: When no threads are available, tasks are queued, ensuring they will be executed once a thread is free.
- **Improved Resource Management**: Limits system resource consumption (like CPU and memory) by controlling the number of threads.

### When to Use Thread Pools:
- When you have multiple tasks that can be executed concurrently, and you want to avoid the overhead of creating and destroying threads repeatedly.
- When you want to limit the number of concurrent threads to prevent overwhelming the system.
- When tasks are short-lived, making thread creation and destruction for every task inefficient.

### Conclusion:
Thread pooling is a powerful technique in Java multithreading to improve performance, manage resources, and provide better control over concurrent task execution. The `ExecutorService` interface and the `Executors` utility class make it easy to implement thread pooling and manage tasks efficiently in multithreaded applications.



## Que 20. 10 threads created, all have been consumed, what will happen



If you have created a thread pool with 10 threads and all 10 threads are currently executing tasks (i.e., they are busy), and new tasks are submitted to the thread pool, the behavior will depend on the type of thread pool and its task queueing mechanism.

Here’s what will typically happen:

Scenario 1: Fixed Thread Pool (Executors.newFixedThreadPool(10))
Fixed Thread Pool means that the thread pool has exactly 10 threads available for executing tasks.
Current State: All 10 threads are busy executing tasks.
What Happens:

Any new tasks submitted to the thread pool will be placed in the internal task queue.
These tasks will remain in the queue until one of the 10 busy threads completes its current task and becomes available to pick up the next task from the queue.
The tasks will be executed in the order they were submitted (FIFO) as soon as threads become available.
The tasks are guaranteed to be executed eventually, but the waiting time depends on when threads finish their current tasks.

Example:
java
Copy code
ExecutorService threadPool = Executors.newFixedThreadPool(10);

for (int i = 1; i <= 15; i++) {
    final int taskID = i;
    threadPool.submit(() -> {
        System.out.println("Task " + taskID + " is being executed by " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);  // Simulating long task execution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });
}
In this case, the first 10 tasks will be picked up immediately by the 10 threads, and the remaining 5 tasks will be placed in the queue and will be executed when any of the 10 threads finishes its current task.

Scenario 2: Cached Thread Pool (Executors.newCachedThreadPool())
Cached Thread Pool does not limit the number of threads. It creates new threads as needed and reuses existing threads when they are available.
Current State: All 10 threads are busy, but since the pool is dynamically scalable, more threads can be created.
What Happens:

If all 10 threads are busy and new tasks are submitted, the thread pool will create new threads to handle these tasks.
There is no upper limit on the number of threads, so theoretically, the thread pool can keep creating threads until the system resources are exhausted.
Scenario 3: Single Thread Executor (Executors.newSingleThreadExecutor())
Single Thread Executor only has one thread.
Current State: The single thread is busy executing one task.
What Happens:

Any new tasks will be placed in the task queue and will be executed one after the other by the single thread once it becomes available.
Scenario 4: Scheduled Thread Pool (Executors.newScheduledThreadPool())
Scheduled Thread Pool allows you to schedule tasks to run after a delay or periodically.
Current State: The 10 threads are busy executing scheduled tasks.
What Happens:

New tasks will be placed in the queue and executed based on their schedule or when threads become available.
Blocking Queue Behavior:
In a Fixed Thread Pool, tasks are placed into a blocking queue (like LinkedBlockingQueue) when all threads are busy.
If the task queue becomes full (depending on the configuration of the thread pool), the pool can reject new tasks using a rejection policy. Java provides several predefined rejection policies, such as:
AbortPolicy: Throws a RejectedExecutionException when the task cannot be executed.
CallerRunsPolicy: Executes the task in the calling thread if the pool is overwhelmed.
DiscardPolicy: Silently discards the task.
DiscardOldestPolicy: Discards the oldest unexecuted task in the queue and adds the new task to the queue.
Conclusion:
When all threads in a thread pool are busy, new tasks are placed in the task queue until threads become available. The tasks will be executed sequentially as threads complete their work. In cached thread pools, additional threads are created to handle the load. However, in a fixed thread pool, the number of threads is constant, so tasks must wait until a thread finishes its task.


## Que 21. use of await and notify method in thread



The `wait()` and `notify()` methods in Java are used for **inter-thread communication**, allowing threads to communicate with each other while coordinating their execution, particularly when working with shared resources. They are part of the `Object` class and must be used within a **synchronized** context.

### Purpose of `wait()` and `notify()`:
- **`wait()`**: Causes the current thread to wait until another thread invokes `notify()` or `notifyAll()` on the same object.
- **`notify()`**: Wakes up one of the threads that are waiting on the object's monitor (the object that called `wait()`).
- **`notifyAll()`**: Wakes up all the threads that are waiting on the object's monitor.

These methods are primarily used to implement **cooperation between threads** where one thread needs to wait until some condition is met or until another thread signals that a certain operation is complete.

### Example Use Case:
Imagine a producer-consumer scenario where one thread (producer) generates data and another thread (consumer) consumes it. The producer should stop when there’s no space available (buffer full), and the consumer should stop when there’s nothing to consume (buffer empty).

Here's an example using `wait()` and `notify()`:

### Producer-Consumer Example:

```java
class SharedResource {
    private int data;
    private boolean available = false;

    // Producer method
    public synchronized void produce(int value) {
        while (available) {
            try {
                wait();  // Wait until the consumer consumes the data
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data = value;
        available = true;
        System.out.println("Produced: " + value);
        notify();  // Notify the consumer that new data is available
    }

    // Consumer method
    public synchronized void consume() {
        while (!available) {
            try {
                wait();  // Wait until the producer produces data
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Consumed: " + data);
        available = false;
        notify();  // Notify the producer that data has been consumed
    }
}

public class Main {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.produce(i);
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.consume();
            }
        });

        // Start both threads
        producer.start();
        consumer.start();
    }
}
```

### Explanation:
1. **`produce()`**:
   - The producer thread tries to produce a value.
   - If the `available` flag is `true`, it means that the consumer hasn't consumed the previous value yet. The producer will call `wait()` and release the lock on the object, allowing the consumer to proceed.
   - Once the consumer has consumed the data and notified the producer, the producer proceeds to produce a new value.

2. **`consume()`**:
   - The consumer thread tries to consume a value.
   - If the `available` flag is `false`, it means that the producer hasn't produced a value yet. The consumer will call `wait()` and release the lock, allowing the producer to proceed.
   - Once the producer has produced data and notified the consumer, the consumer proceeds to consume the value.

3. **Synchronization**:
   - `wait()` and `notify()` must be called within synchronized blocks or methods because they rely on the **monitor** of the object (the lock) to ensure proper coordination between threads.
   - When `wait()` is called, the current thread releases the lock and waits until it is notified. After being notified, the thread reacquires the lock and continues execution.

4. **`notify()` vs `notifyAll()`**:
   - `notify()`: Wakes up **one** waiting thread. The specific thread chosen to be woken up depends on the JVM implementation.
   - `notifyAll()`: Wakes up **all** threads waiting on the object's monitor. This is useful when you have multiple threads waiting for a condition to change, and you want all of them to be reconsidered for execution.

### Key Points to Remember:
- `wait()`, `notify()`, and `notifyAll()` are methods of the `Object` class, meaning they can be called on any object.
- `wait()` causes the current thread to release the lock and wait until `notify()` or `notifyAll()` is called.
- `notify()` wakes up one waiting thread, while `notifyAll()` wakes up all waiting threads.
- These methods must be called within synchronized code blocks or methods to ensure proper access to the shared resource.
- `wait()` should always be called inside a loop that checks the condition on which the thread is waiting. This prevents issues caused by spurious wakeups (a thread being awakened without any `notify()` or `notifyAll()` being called).

### Spurious Wakeup Issue:
Java threads can wake up from the `wait()` method without being notified. This is called a **spurious wakeup**. That's why `wait()` is typically used in a loop:

```java
while (!condition) {
    wait();
}
```

This ensures that once the thread wakes up, it rechecks the condition and goes back to waiting if necessary.

### Conclusion:
- `wait()` and `notify()` provide mechanisms for inter-thread communication and cooperation.
- They are especially useful in scenarios where one thread needs to wait for another thread to signal that a particular condition is met or a task is complete.
- Proper usage requires careful synchronization and handling of shared resources to avoid issues like deadlock or data corruption.



## Que 22. custom exceptions



**Custom exceptions** in Java are user-defined exceptions that allow you to create more meaningful, domain-specific error handling in your applications. Java provides several built-in exceptions like `NullPointerException`, `ArrayIndexOutOfBoundsException`, etc., but in some cases, they may not fully represent the errors occurring in your application. This is where custom exceptions are useful.

### Why Create Custom Exceptions?

Custom exceptions:
- Provide more meaningful and specific error messages.
- Make your code more readable by clearly stating the problem through exception names.
- Enhance debugging by categorizing exceptions based on the specific problem domain (e.g., validation issues, security breaches, etc.).
- Allow you to handle exceptions in a controlled way specific to your application's needs.

### Steps to Create a Custom Exception:

1. **Extend the Exception Class**: You can either extend `Exception` (checked exception) or `RuntimeException` (unchecked exception).
2. **Create Constructors**: Add constructors to pass custom messages and/or cause of the exception.
3. **Optional: Override Methods**: You can override methods like `toString()` or `getMessage()` to provide custom behavior.

### Example of a Custom Checked Exception:

Checked exceptions need to be either caught or declared to be thrown using the `throws` keyword.

```java
// Custom checked exception
class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}

public class UserService {
    public void validateUser(String userName) throws InvalidUserException {
        if (userName == null || userName.isEmpty()) {
            throw new InvalidUserException("User name cannot be null or empty");
        }
        // Validation logic here...
    }

    public static void main(String[] args) {
        UserService service = new UserService();
        try {
            service.validateUser("");  // This will throw the custom exception
        } catch (InvalidUserException e) {
            System.out.println("Caught custom exception: " + e.getMessage());
        }
    }
}
```

### Example of a Custom Unchecked Exception:

Unchecked exceptions do not need to be explicitly declared in method signatures or caught.

```java
// Custom unchecked exception
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class AgeValidator {
    public void validateAge(int age) {
        if (age < 18) {
            throw new InvalidAgeException("Age must be at least 18");
        }
        // Validation logic here...
    }

    public static void main(String[] args) {
        AgeValidator validator = new AgeValidator();
        try {
            validator.validateAge(16);  // This will throw the custom exception
        } catch (InvalidAgeException e) {
            System.out.println("Caught custom unchecked exception: " + e.getMessage());
        }
    }
}
```

### Key Points:
1. **Checked vs. Unchecked Exceptions**:
   - **Checked exceptions**: Extend `Exception`. These must be caught or declared to be thrown.
   - **Unchecked exceptions**: Extend `RuntimeException`. These do not need to be caught or declared to be thrown.

2. **Custom Constructors**: You can create multiple constructors in your custom exception to pass messages or causes.

```java
public class MyCustomException extends Exception {
    public MyCustomException() {
        super();
    }

    public MyCustomException(String message) {
        super(message);
    }

    public MyCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

3. **Custom Logic**: You can override methods like `toString()` to provide more detailed information about your custom exception.

### Example with Custom Logic:

```java
class InvalidTransactionException extends Exception {
    private int transactionId;

    public InvalidTransactionException(String message, int transactionId) {
        super(message);
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "InvalidTransactionException: " + getMessage() + " for Transaction ID: " + transactionId;
    }
}

public class TransactionService {
    public void processTransaction(int transactionId) throws InvalidTransactionException {
        if (transactionId <= 0) {
            throw new InvalidTransactionException("Transaction ID must be positive", transactionId);
        }
        // Transaction processing logic here...
    }

    public static void main(String[] args) {
        TransactionService service = new TransactionService();
        try {
            service.processTransaction(-100);  // This will throw the custom exception
        } catch (InvalidTransactionException e) {
            System.out.println(e);
        }
    }
}
```

### Conclusion:
- Custom exceptions improve the clarity and quality of error handling in your application by giving more context-specific information.
- Choose **checked exceptions** for recoverable conditions and **unchecked exceptions** for programming errors or conditions that the program should not try to recover from.
- Always provide meaningful messages and custom logic where necessary to make exception handling as informative as possible.



## Que 23. controller advise in spring boot



In Spring Boot, **@ControllerAdvice** is a powerful annotation used for global exception handling, model enhancement, and data binding across multiple controllers. It allows you to define a class that can handle exceptions and bind models for multiple controllers without repeating the same logic in each controller.

### Key Features of `@ControllerAdvice`:

1. **Global Exception Handling**: You can handle exceptions thrown by any controller method in a centralized way.
2. **Global Model Attributes**: You can add common attributes to the model for all controller methods.
3. **Global Data Binding**: You can configure binding rules for all controllers.

### Usage of `@ControllerAdvice`:

Here’s a breakdown of how to use `@ControllerAdvice` for various purposes:

#### 1. Global Exception Handling

You can create a global exception handler using `@ExceptionHandler` within a class annotated with `@ControllerAdvice`. This way, you can catch exceptions thrown by any controller and handle them accordingly.

**Example**:

```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<String> handleInvalidUserException(InvalidUserException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)  // Catch-all for other exceptions
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

In this example:
- The `handleInvalidUserException` method handles `InvalidUserException` thrown from any controller and returns a `400 Bad Request` response.
- The `handleGeneralException` method serves as a catch-all for other exceptions.

#### 2. Adding Global Model Attributes

You can add common attributes to the model using the `@ModelAttribute` annotation.

**Example**:

```java
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("appName")
    public String appName() {
        return "My Application";
    }
}
```

In this example, the `appName` attribute will be available in all model views across different controllers.

#### 3. Global Data Binding Configuration

You can also customize the data binding process globally using `@InitBinder`.

**Example**:

```java
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalDataBinding {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Add custom editors or validation logic here
        // For example, to trim strings
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
```

### Summary of Benefits:
- **Centralized Exception Handling**: Reduces code duplication by consolidating exception handling logic in one place.
- **Common Model Attributes**: Ensures that common data is available across different controllers, promoting DRY principles.
- **Flexible Data Binding**: Allows customization of the binding process for all controllers.

### Conclusion:
Using `@ControllerAdvice` in Spring Boot enhances the modularity and maintainability of your application by reducing redundancy in exception handling and model management. This feature is particularly useful in large applications where consistency and code organization are essential.



## Que 24. diff b/w checked and unchecked exceptions, which class need to be extended?


In Java, exceptions are divided into two main categories: **checked exceptions** and **unchecked exceptions**. Here’s a breakdown of their differences and the classes you need to extend for each type:

### 1. Checked Exceptions

- **Definition**: Checked exceptions are exceptions that must be either caught or declared in the method signature using the `throws` keyword. These exceptions are checked at compile-time.
  
- **Use Cases**: They are used when a method might fail due to external circumstances that the calling code can reasonably expect to handle (e.g., file not found, network issues).

- **Inheritance**: Checked exceptions must extend the `Exception` class (but not `RuntimeException`). This means that any exception class that you create as a checked exception should directly or indirectly extend `Exception`.

#### Example:
```java
// Custom checked exception
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

// Method declaration with checked exception
public void processInput(String input) throws InvalidInputException {
    if (input == null || input.isEmpty()) {
        throw new InvalidInputException("Input cannot be null or empty");
    }
}
```

### 2. Unchecked Exceptions

- **Definition**: Unchecked exceptions are exceptions that do not need to be caught or declared. These exceptions are not checked at compile-time and occur at runtime.

- **Use Cases**: They usually indicate programming errors that could have been avoided (e.g., accessing an out-of-bounds array index, null pointer access).

- **Inheritance**: Unchecked exceptions must extend the `RuntimeException` class, which is a subclass of `Exception`. You can also extend `Error`, but generally, you should not create custom exceptions extending `Error`.

#### Example:
```java
// Custom unchecked exception
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Method using unchecked exception
public void validateAge(int age) {
    if (age < 0) {
        throw new InvalidAgeException("Age cannot be negative");
    }
}
```

### Summary of Differences:

| Feature                        | Checked Exceptions                               | Unchecked Exceptions                          |
|--------------------------------|-------------------------------------------------|----------------------------------------------|
| **Checked at**                 | Compile-time                                    | Runtime                                      |
| **Need to be handled**         | Yes, must be caught or declared                 | No, not required to be caught                |
| **Extends**                    | `Exception` (not `RuntimeException`)            | `RuntimeException`                           |
| **Use Cases**                  | Recoverable situations (I/O errors, etc.)      | Programming errors (NullPointerException, etc.) |

### Conclusion:
- **Checked exceptions** should be used when you want to force the caller to handle potential error conditions that are recoverable.
- **Unchecked exceptions** should be used for programming errors that are not expected to be handled at runtime. They provide flexibility in how exceptions are managed within your code.


## Que 25. throws keyword use

In Java, the `throws` keyword is used in method declarations to specify that a method can throw certain exceptions. It indicates to the caller of the method that it must handle the exceptions, either by catching them in a `try-catch` block or by declaring them further up the call stack using `throws`.

### Key Points about `throws`:

1. **Checked vs. Unchecked Exceptions**:
   - `throws` is primarily used with **checked exceptions**. These are exceptions that are not subclasses of `RuntimeException` and must be either caught or declared.
   - **Unchecked exceptions** (subclasses of `RuntimeException`) do not require the use of `throws` because they are not checked at compile time.

2. **Syntax**:
   The syntax for using `throws` in a method declaration is as follows:
   ```java
   returnType methodName(parameters) throws ExceptionType1, ExceptionType2 {
       // method implementation
   }
   ```
   Here, `ExceptionType1`, `ExceptionType2`, etc., are the exceptions that the method can throw.

3. **Multiple Exceptions**:
   A method can declare multiple exceptions by separating them with commas.

4. **Propagation**:
   When a method declares an exception using `throws`, it propagates the exception to the caller, which then must handle it appropriately.

### Example of Using `throws`:

Here’s a practical example demonstrating the use of the `throws` keyword:

```java
// Custom checked exception
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class Calculator {

    // Method that can throw an exception
    public int divide(int a, int b) throws InvalidInputException {
        if (b == 0) {
            throw new InvalidInputException("Division by zero is not allowed.");
        }
        return a / b;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        try {
            int result = calculator.divide(10, 0);  // This will throw the custom exception
            System.out.println("Result: " + result);
        } catch (InvalidInputException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
```

### Explanation of the Example:

1. **Custom Exception**: We create a custom checked exception, `InvalidInputException`, which extends `Exception`.
2. **Method Declaration**: The `divide` method declares that it can throw an `InvalidInputException` using the `throws` keyword.
3. **Throwing an Exception**: Inside the `divide` method, we check if the divisor `b` is zero. If it is, we throw an `InvalidInputException`.
4. **Handling the Exception**: In the `main` method, we call the `divide` method within a `try` block. If the exception is thrown, it is caught in the corresponding `catch` block, where we handle it appropriately.

### When to Use `throws`:

- Use `throws` when you want to propagate an exception from a method without handling it inside the method.
- It helps in keeping your method implementation clean and allows the caller to decide how to handle the exception.
- It is also useful for methods that may have a variety of error conditions that can be handled differently by different callers.

### Conclusion:

The `throws` keyword is an important aspect of Java's exception handling mechanism, particularly for checked exceptions. It allows developers to signal potential error conditions to method callers, promoting a clear and structured approach to error handling in Java applications.


## Que 26. SpringBoot, its web or standalone


Spring Boot is a framework that is primarily used for building **web applications**, but it can also be used to create **standalone applications**. Here’s a breakdown of its capabilities:

### Spring Boot as a Web Application Framework

1. **Embedded Servers**: Spring Boot allows you to run web applications directly with embedded servers like Tomcat, Jetty, or Undertow. This means you can package your application as a JAR file and run it without needing to deploy it to a separate web server.

2. **RESTful APIs**: Spring Boot makes it easy to create RESTful web services using the Spring MVC framework. You can define controllers, request mappings, and response formats quickly and efficiently.

3. **Spring MVC Integration**: It integrates seamlessly with Spring MVC, providing a comprehensive set of tools for developing web applications, including support for views (Thymeleaf, JSP, etc.), form handling, and data binding.

4. **Dependency Management**: Spring Boot uses **Spring Boot Starter** dependencies to simplify the configuration of web applications. For example, including `spring-boot-starter-web` adds all necessary dependencies for building web applications.

5. **Microservices Architecture**: Spring Boot is widely used in building microservices, which are independently deployable services that can communicate with each other over HTTP.

### Spring Boot as a Standalone Application Framework

1. **Standalone Applications**: You can also build standalone applications with Spring Boot. These applications may not necessarily have a web interface or may serve as background services (e.g., batch jobs, data processing tasks).

2. **Spring Boot CLI**: The Spring Boot Command Line Interface (CLI) allows you to run Spring Boot applications directly from the command line without packaging them into a JAR file.

3. **Easy Configuration**: Spring Boot provides a simplified configuration mechanism through application properties or YAML files, making it easy to manage application settings for standalone applications.

4. **Auto-Configuration**: The auto-configuration feature in Spring Boot can automatically configure your application based on the dependencies you include. This is useful for both web and standalone applications.

### Conclusion

- **Web Application**: Spring Boot is a powerful framework for developing web applications, providing tools and libraries to build RESTful APIs and web interfaces efficiently.
- **Standalone Application**: It can also be used to develop standalone applications, leveraging its configuration management and dependency management features.

In summary, Spring Boot is versatile and can be used for both web and standalone applications, making it a popular choice for developers building modern applications.


## Que 27. ioc container


In Spring (and Spring Boot), the **IoC (Inversion of Control) Container** is the core concept that manages the life cycle and dependencies of the objects (or beans). IoC is a design principle that reverses the control flow in a program by allowing the framework (Spring) to manage the creation, configuration, and dependency resolution of objects, rather than the programmer manually doing so.

### Key Concepts of IoC Container:

1. **Inversion of Control (IoC)**:  
   IoC refers to the shift in control where the creation and management of objects are handled by the framework, rather than the application code. Instead of the objects controlling their dependencies, the framework (IoC container) injects the required dependencies into the objects.

2. **Dependency Injection (DI)**:  
   IoC is achieved in Spring through **Dependency Injection**. This is where objects (dependencies) are provided to a class through constructor injection, setter injection, or field injection.

### Types of IoC Containers in Spring:

Spring provides two main types of IoC containers:

1. **BeanFactory**:  
   - It is the basic container in Spring.  
   - It lazily initializes beans, meaning beans are created only when they are requested.  
   - BeanFactory is used in scenarios where lightweight container usage is required with minimal overhead.

2. **ApplicationContext**:  
   - It is a more feature-rich container than `BeanFactory`.
   - It eagerly initializes beans by default at the startup of the application.
   - It provides several additional features such as event propagation, declarative mechanisms to create a bean, and more.
   - Common implementations of `ApplicationContext` are:
     - `ClassPathXmlApplicationContext`: Loads context definition from an XML file located in the classpath.
     - `FileSystemXmlApplicationContext`: Loads context definition from an XML file located in the filesystem.
     - `AnnotationConfigApplicationContext`: Loads context using Java annotations.

### Working of IoC Container:

The IoC container in Spring performs the following tasks:

- **Creation**: It creates objects (beans) as defined in the configuration (either XML or annotations).
- **Wiring**: It injects dependencies into the beans using constructor injection, setter injection, or field injection.
- **Lifecycle Management**: It manages the entire life cycle of beans, from instantiation to destruction.
- **Scope**: It manages the scope of the beans (singleton, prototype, etc.).

### How the IoC Container Works:

1. **Bean Definition**: You define your beans in a Spring configuration file (XML, annotations, or Java-based configurations).
2. **IoC Container Reads Configurations**: Spring's IoC container reads these configurations and creates the necessary objects.
3. **Dependency Injection**: The container automatically injects the required dependencies into these beans based on their configuration.
4. **Lifecycle Management**: Spring manages the lifecycle of beans, which includes instantiating the beans, injecting dependencies, and, if necessary, destroying them when the application shuts down.

### Example of IoC in Spring with Annotations:

Here’s how IoC works in Spring Boot using annotations:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// A simple service class (dependent object)
@Component
class Service {
    public void serve() {
        System.out.println("Service is running...");
    }
}

// The dependent class that requires the service
@Component
public class Client {
    private Service service;

    // Constructor-based dependency injection
    @Autowired
    public Client(Service service) {
        this.service = service;
    }

    public void doWork() {
        service.serve();
    }
}

// Main application class
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootIoCExample {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBootIoCExample.class, args);
        
        // Retrieve the client bean and call its method
        Client client = context.getBean(Client.class);
        client.doWork();
    }
}
```

### Explanation:

1. **`@Component` Annotation**: Marks a class as a Spring-managed bean. The IoC container will create and manage instances of the class.
2. **`@Autowired` Annotation**: Tells the IoC container to inject the `Service` dependency into the `Client` class.
3. **ApplicationContext**: The Spring Boot IoC container (`ApplicationContext`) creates the beans (`Client` and `Service`) and injects the dependencies at runtime.

### Summary:

- **IoC** in Spring enables the inversion of control, meaning that the framework controls the creation and lifecycle of objects (beans) instead of the application code.
- Spring’s IoC container uses **dependency injection (DI)** to inject dependencies into objects.
- The two main types of IoC containers are `BeanFactory` and `ApplicationContext`, with `ApplicationContext` being the more feature-rich option.
- The use of IoC makes Spring applications more modular, easier to maintain, and testable.



## Que 28. how does IOC understand the dependencies req


The Spring IoC (Inversion of Control) container understands the dependencies required by a bean through **Dependency Injection (DI)**, which is configured using metadata such as annotations, XML configurations, or Java-based configurations. Here’s how Spring understands and resolves the dependencies:

### 1. **Annotations-Based Configuration** (Most Common in Spring Boot)

Spring Boot primarily uses annotations to wire dependencies. Here’s how Spring understands the dependencies:

- **`@Autowired`**: This annotation tells Spring that a particular field, constructor, or setter method requires dependency injection. The IoC container then checks its registry of beans to find the appropriate bean to inject. If it finds one, it injects it automatically.
  
- **`@ComponentScan`**: This annotation tells the IoC container where to search for `@Component`, `@Service`, `@Repository`, and other annotated beans. When the container starts, it scans these packages and registers the beans. These beans are made available for dependency injection.

- **Constructor Injection**: Spring can inject dependencies through a constructor. When the constructor has parameters, Spring matches the required types to the available beans and injects them.

- **Setter Injection**: The IoC container can inject dependencies through setter methods, similarly matching the method parameters with available beans.

### Example:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ServiceA {
    public void serve() {
        System.out.println("ServiceA is serving...");
    }
}

@Component
class ServiceB {
    private final ServiceA serviceA;

    // Constructor-based dependency injection
    @Autowired
    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public void perform() {
        serviceA.serve();
    }
}
```

In this example, `ServiceB` depends on `ServiceA`. Spring identifies this dependency via the `@Autowired` annotation and injects an instance of `ServiceA` when creating `ServiceB`.

### 2. **XML-Based Configuration** (Legacy Approach)

In older Spring configurations, dependencies were specified using XML. The IoC container reads the XML file and maps dependencies.

### Example XML Configuration:

```xml
<beans>
    <bean id="serviceA" class="com.example.ServiceA"/>
    <bean id="serviceB" class="com.example.ServiceB">
        <constructor-arg ref="serviceA"/>
    </bean>
</beans>
```

In this configuration, the IoC container reads the XML, understands that `serviceB` requires `serviceA` through constructor injection, and wires them together.

### 3. **Java-Based Configuration** (Modern Approach)

Java-based configuration using `@Configuration` and `@Bean` annotations can also define and wire dependencies.

### Example Java-Based Configuration:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public ServiceA serviceA() {
        return new ServiceA();
    }

    @Bean
    public ServiceB serviceB(ServiceA serviceA) {
        return new ServiceB(serviceA);
    }
}
```

Here, Spring calls the `serviceB` method and automatically passes the `serviceA` bean as a parameter, because the method signature indicates that `ServiceB` requires a `ServiceA` bean.

### 4. **How IoC Resolves Dependencies**:

- **Bean Creation**: During the startup of the application, the IoC container creates beans either eagerly (by default) or lazily (on demand).
  
- **Bean Definition**: The container checks for bean definitions either in the annotations (`@Component`, `@Service`, etc.), XML configuration, or Java configuration. It also determines the scope (e.g., singleton, prototype) of the beans.
  
- **Dependency Matching**:
  - **By Type**: The IoC container checks the types of dependencies declared in the constructor or method and matches them with available beans of that type.
  - **By Name** (Optional): The IoC container can match beans by name if specified, or using `@Qualifier` annotations to resolve ambiguities when multiple beans of the same type exist.

- **Injection**: Once the container resolves the dependencies, it injects them into the dependent objects using constructor injection, setter injection, or field injection.

### 5. **Handling Ambiguities**:
When there are multiple candidates of the same type, Spring may face ambiguity in resolving dependencies. In such cases:

- **`@Primary`**: You can mark one bean as primary, indicating that it should be preferred when resolving a dependency.

- **`@Qualifier`**: This annotation helps specify which particular bean should be injected when multiple beans of the same type are available.

### Example of Ambiguity Resolution:

```java
@Component
class ServiceA {
    public void serve() {
        System.out.println("ServiceA is serving...");
    }
}

@Component
@Primary  // This bean will be injected by default when ServiceA is required
class ServiceAImpl1 extends ServiceA {
}

@Component
class ServiceAImpl2 extends ServiceA {
}

@Component
class ServiceB {
    private final ServiceA serviceA;

    @Autowired
    public ServiceB(@Qualifier("serviceAImpl2") ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public void perform() {
        serviceA.serve();
    }
}
```

In this example, `@Primary` marks `ServiceAImpl1` as the default choice, but in `ServiceB`, we explicitly use `@Qualifier("serviceAImpl2")` to inject `ServiceAImpl2`.

### Summary:

- **IoC Container** identifies dependencies via annotations (`@Autowired`, `@Component`), XML, or Java configurations.
- **Dependency Injection** is achieved by matching the required types and injecting them either through constructors, setters, or fields.
- The IoC container manages the bean lifecycle and resolves dependencies during bean creation.
- Ambiguities are resolved using annotations like `@Primary` or `@Qualifier` when multiple beans of the same type exist.

Spring’s IoC container thus allows developers to focus on business logic, while it handles the creation and management of dependencies, making applications more modular and easier to maintain.



## Que 29. deafult Bean scope


In Spring, the **default bean scope** is **`singleton`**. This means that by default, Spring IoC container creates and manages only one instance (or object) of the bean per Spring IoC container. All requests for that bean will return the same instance throughout the lifecycle of the application.

### Explanation of the Default Scope (`singleton`):

- **Singleton Scope**: In the singleton scope, Spring ensures that only one instance of the bean is created and shared across the entire application context. Every time the bean is requested (via dependency injection, direct lookup, etc.), the same instance is returned.

- **Behavior**: If a bean is defined with singleton scope, Spring creates the bean instance during the initialization of the application context (eager initialization) and keeps it for the entire runtime. If lazy initialization is used, the bean will be created when it is first requested.

### Example:

```java
@Component
public class MyService {
    // This is a singleton-scoped bean by default
}
```

Here, `MyService` is a singleton-scoped bean by default. Even if you inject `MyService` into multiple classes, Spring will always provide the same instance.

### Other Scopes Available in Spring:

Apart from `singleton`, Spring provides several other scopes:

1. **`prototype`**: A new instance of the bean is created every time it is requested.
2. **`request`** (Web Applications): A new instance is created for each HTTP request.
3. **`session`** (Web Applications): A new instance is created for each HTTP session.
4. **`application`** (Web Applications): A new instance is created per ServletContext.
5. **`websocket`** (WebSocket Applications): A new instance is created for each WebSocket session.

### Setting a Different Scope:

If you want to change the scope of a bean, you can use the `@Scope` annotation:

```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MyService {
    // This bean will have a prototype scope, meaning a new instance will be created each time it is requested
}
```

In this case, every time `MyService` is requested, a new instance will be created.

### Summary:

- **Default Scope**: `singleton` (One instance per Spring IoC container).
- **Other Scopes**: `prototype`, `request`, `session`, `application`, and `websocket` are some of the other scopes supported by Spring.

For most use cases, the `singleton` scope is sufficient, but in scenarios where you need a new instance for each request or specific context, you can configure a different scope.


## Que 30. what is singleton?



**Singleton** is a design pattern that ensures a class has only one instance and provides a global point of access to that instance.

### Key Features of Singleton:
1. **Single Instance**: Only one instance of the class is created, ensuring that no matter how many times the class is requested, the same instance is returned.
2. **Global Access**: The single instance is globally accessible throughout the application.

### Singleton in Java:

To implement a singleton in Java, you typically follow these steps:

1. **Private Constructor**: Ensure that the constructor is private so that no other class can instantiate the singleton class directly.
2. **Static Instance**: Create a static instance of the class that is globally accessible.
3. **Static Method**: Provide a static method (often named `getInstance()`) that returns the single instance of the class.

### Example of Singleton Pattern in Java:

```java
public class Singleton {
    // Private static variable that stores the single instance
    private static Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {}

    // Public static method to provide access to the instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Example method in singleton class
    public void showMessage() {
        System.out.println("Hello, I am a Singleton!");
    }
}
```

### Usage:

```java
public class Main {
    public static void main(String[] args) {
        // Get the only instance of Singleton
        Singleton singleton = Singleton.getInstance();

        // Call a method on the instance
        singleton.showMessage();
    }
}
```

In this example:
- `Singleton.getInstance()` will always return the same instance, and the instance is lazily initialized (i.e., created when it's needed for the first time).

### Singleton in Spring Framework:

In Spring, the **singleton scope** for beans is similar to the singleton pattern but is managed by the Spring container. The Spring container creates a single instance of a bean with the `singleton` scope and reuses that instance across the entire context.

```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

Here, `myService()` returns the same `MyService` instance across the entire Spring application by default because the bean is in singleton scope.

### Key Differences:
- **Java Singleton**: You explicitly control the instance in your code using the singleton pattern.
- **Spring Singleton Bean**: Spring controls the creation and management of the singleton bean instance within its container.

### Conclusion:
A singleton ensures that only one instance of a class exists, providing a single point of access. In Java, this is a pattern that you implement in code, while in Spring, the framework manages it for you by default for beans.



## Que 31. what's qualifier? use of qualifier



In Spring Framework, the `@Qualifier` annotation is used to resolve the ambiguity that arises when there are multiple beans of the same type, and you need to specify exactly which bean should be injected into a particular dependency.

### Why is `@Qualifier` Needed?

By default, Spring's dependency injection resolves beans by type. However, if more than one bean of the same type is available in the Spring container, Spring doesn't know which one to inject, and you get an error like this:

```
NoUniqueBeanDefinitionException: expected single matching bean but found 2
```

In such scenarios, `@Qualifier` helps specify which exact bean to use when injecting dependencies.

### Example without `@Qualifier` (Multiple Beans Problem):
Consider that you have two beans of the same type `Car`.

```java
@Component
public class BMW implements Car {
    // BMW specific logic
}

@Component
public class Audi implements Car {
    // Audi specific logic
}

@Component
public class CarService {
    private Car car;

    @Autowired
    public CarService(Car car) {
        this.car = car;
    }
}
```
In this scenario, Spring will be confused because both `BMW` and `Audi` implement `Car`, and Spring will not know which one to inject into `CarService`.

### Resolving Ambiguity with `@Qualifier`:

By using `@Qualifier`, you can tell Spring explicitly which bean to inject:

```java
@Component
public class CarService {
    private Car car;

    @Autowired
    public CarService(@Qualifier("audi") Car car) {
        this.car = car;
    }
}
```

Here, the `@Qualifier("audi")` tells Spring to inject the `Audi` bean. The name provided in `@Qualifier` must match the name of the bean (by default, the bean's name is the class name in camel case, i.e., `audi` for the `Audi` class).

### Full Example with `@Qualifier`:

#### Car Interface:
```java
public interface Car {
    void drive();
}
```

#### BMW Class:
```java
@Component
public class BMW implements Car {
    @Override
    public void drive() {
        System.out.println("Driving BMW");
    }
}
```

#### Audi Class:
```java
@Component
public class Audi implements Car {
    @Override
    public void drive() {
        System.out.println("Driving Audi");
    }
}
```

#### CarService Class with `@Qualifier`:
```java
@Component
public class CarService {
    private Car car;

    @Autowired
    public CarService(@Qualifier("audi") Car car) {
        this.car = car;
    }

    public void performService() {
        car.drive();
    }
}
```

#### Main Application:
```java
@SpringBootApplication
public class CarApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CarApplication.class, args);
        CarService carService = context.getBean(CarService.class);
        carService.performService();  // Output: Driving Audi
    }
}
```

In this example:
- The `@Qualifier("audi")` ensures that the `Audi` bean is injected into `CarService`, even though there are two beans (`BMW` and `Audi`) implementing `Car`.

### When to Use `@Qualifier`:
1. **Multiple Implementations**: When you have more than one bean of the same type and want to specify which one should be injected.
2. **Custom Annotations**: You can also create custom qualifiers by defining your own annotations that act like a `@Qualifier` to inject beans.
3. **Method-Level Injection**: You can use `@Qualifier` on setter methods or on fields to resolve ambiguities during injection.

### Summary:

- `@Qualifier` is used in Spring to differentiate between multiple beans of the same type.
- It works with `@Autowired` to specify which exact bean should be injected when there are multiple candidates.
- It ensures that Spring injects the correct bean into the appropriate class, preventing `NoUniqueBeanDefinitionException`.

### Conclusion:
The `@Qualifier` annotation provides a mechanism to specify which exact bean should be used in a situation where multiple beans of the same type exist, ensuring more precise and error-free dependency injection in Spring applications.



## Que 32. u need to create api to download file what is mediaType

In the context of creating an API to download a file, `MediaType` represents the content type of the file being downloaded. It informs the client about the type of data that the server is returning, so the client can handle it appropriately (e.g., opening it with the right program or saving it as the correct file type).

In Spring Boot, the `MediaType` class is used to specify the media (MIME) type of the response, such as `application/pdf`, `application/json`, `image/png`, etc.

### Example: File Download API using Spring Boot

Here’s an example of how to create an API for downloading a file with the correct `MediaType`.

```java
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileDownloadController {

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get("path/to/your/files/" + fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                // Determine the file's content type
                String contentType = "application/octet-stream"; // default if unknown
                if (fileName.endsWith(".pdf")) {
                    contentType = MediaType.APPLICATION_PDF_VALUE;
                } else if (fileName.endsWith(".png")) {
                    contentType = MediaType.IMAGE_PNG_VALUE;
                } // Add other file types as needed

                // Set the headers and return the file as a response
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
```

### Explanation:
- **`MediaType.parseMediaType()`**: This is used to specify the MIME type of the file, so the client knows how to handle it.
- **`HttpHeaders.CONTENT_DISPOSITION`**: This header is used to indicate that the content should be downloaded as an attachment.
- **File Content-Type**: You can specify `MediaType` based on the file type. Some common `MediaType` values are:
  - `MediaType.APPLICATION_PDF_VALUE` (`application/pdf`)
  - `MediaType.IMAGE_PNG_VALUE` (`image/png`)
  - `MediaType.APPLICATION_OCTET_STREAM_VALUE` (generic for binary data, `application/octet-stream`).

### Common Media Types:
- **`application/json`**: For JSON data.
- **`application/pdf`**: For PDF files.
- **`image/jpeg`**: For JPEG images.
- **`image/png`**: For PNG images.
- **`text/plain`**: For plain text files.
- **`application/octet-stream`**: For generic binary files.

### Conclusion:
In a file download API, `MediaType` specifies the type of file being returned (e.g., PDF, image, text), ensuring the client can handle the file properly upon download. Using `MediaType` allows the server to provide the correct content type based on the file being served.


## Que 33. diff bw @RestController and @component


Both `@RestController` and `@Component` are annotations used in Spring Framework, but they serve different purposes. Here’s a breakdown of their differences:

### 1. **Purpose**:
   - **`@RestController`**: This annotation is a specialized version of `@Controller` used specifically for RESTful web services. It combines the functionality of `@Controller` and `@ResponseBody`. A class annotated with `@RestController` will have its methods return data directly as JSON or XML (or other formats), rather than returning a view.
   - **`@Component`**: This is a generic stereotype annotation used to mark a class as a Spring-managed bean. It is a general-purpose annotation that indicates the class is a component to be automatically detected by Spring during classpath scanning.

### 2. **Use Case**:
   - **`@RestController`**: Used for defining REST API endpoints that return data (e.g., JSON or XML) instead of views. It is typically used in web applications where you want to expose resources via HTTP endpoints.
   - **`@Component`**: Used to define any general-purpose bean in the Spring container. It’s more commonly used for service, repository, or helper classes that don't have a specialized role like controllers.

### 3. **Returned Data**:
   - **`@RestController`**: Methods inside a class annotated with `@RestController` will return data directly to the client. The response body is automatically serialized into JSON, XML, or another specified format.
   - **`@Component`**: There is no automatic serialization to the response body because `@Component` is not tied to web requests. Classes annotated with `@Component` are typically used for business logic or other application services, not for handling HTTP requests directly.

### 4. **Example**:

#### `@RestController` Example:
```java
@RestController
public class MyRestController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
```
- In this case, the `sayHello()` method will return the string `"Hello, World!"` as a response body, typically in JSON format. The annotation indicates that this class is a REST controller.

#### `@Component` Example:
```java
@Component
public class MyComponent {

    public String performService() {
        return "Service logic executed!";
    }
}
```
- Here, `MyComponent` is just a bean that can be injected and used elsewhere in the application. It performs some logic, but it doesn't handle HTTP requests or return responses.

### 5. **Specialization**:
   - **`@RestController`**: A specialized component meant for handling web requests and responding with data. It is designed specifically for RESTful web services.
   - **`@Component`**: A more generic annotation that can be used for various types of beans that do not necessarily interact with web requests (e.g., services, repositories, utilities).

### 6. **Relationship**:
   - **`@RestController`**: Technically, `@RestController` is also a `@Component`. Spring treats `@RestController` as a `@Component`, meaning it can be picked up during component scanning. However, it has the additional functionality of handling HTTP requests and returning data.
   - **`@Component`**: It is the most general stereotype annotation for a Spring-managed component and serves as the parent to other specialized annotations like `@Service`, `@Repository`, and `@Controller` (and by extension, `@RestController`).

### Conclusion:
- **`@RestController`** is used for building RESTful web services and is a specialized component for handling HTTP requests, returning responses in formats like JSON or XML.
- **`@Component`** is a general-purpose annotation to register a class as a Spring bean. It is more suitable for non-web components like services, repositories, or utility classes.


## Que 34. Database JPA


Java Persistence API (JPA) is a specification in Java that defines how to manage relational data in applications using Java objects. JPA is part of the Jakarta EE specification but can be used independently in Java SE and in conjunction with Spring Boot. JPA provides a way to map Java objects (entities) to database tables using annotations or XML configurations. JPA simplifies database operations and abstracts the complexity of interacting directly with SQL.

Here’s a detailed overview of JPA and its usage in a database context:

### 1. **Entities and Entity Annotations**
   - **Entities**: In JPA, an entity represents a table in a database, and each instance of an entity corresponds to a row in the table.
   - **Annotations**: JPA uses annotations to map Java objects to database tables.

#### Example of an Entity:
```java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String email;
    
    // Getters and Setters
}
```
- `@Entity`: Marks the class as a JPA entity, meaning it will be mapped to a database table.
- `@Id`: Specifies the primary key field.
- `@GeneratedValue`: Specifies how the primary key should be generated, in this case, auto-incremented by the database.

### 2. **Repositories**
   - Repositories are responsible for interacting with the database. In Spring Data JPA, you can create a repository by extending the `JpaRepository` interface.

#### Example of a Repository:
```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be added here
    User findByUsername(String username);
}
```

- `JpaRepository`: Provides CRUD operations and additional methods for pagination and sorting.
- Custom query methods like `findByUsername` can be automatically implemented by Spring Data JPA based on method naming conventions.

### 3. **Persistence Context and EntityManager**
   - The **EntityManager** is responsible for the lifecycle of entities, such as persisting, removing, finding, and merging entities in the database. The persistence context is like a cache that manages entities’ state.

#### Example of EntityManager usage:
```java
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;
    
    public void saveUser(User user) {
        entityManager.persist(user);  // Persist the user entity into the database
    }
    
    public User getUser(Long id) {
        return entityManager.find(User.class, id);  // Find a user by ID
    }
}
```

### 4. **Transactions**
   - In JPA, database operations are wrapped in transactions to ensure atomicity and consistency. In Spring, transactions can be managed declaratively using the `@Transactional` annotation.

#### Example of Transactions:
```java
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Transactional
    public void updateUser(Long id, String newEmail) {
        User user = entityManager.find(User.class, id);
        user.setEmail(newEmail);
        // Transaction is committed automatically when the method completes
    }
}
```
- `@Transactional`: Manages transactions. If an exception is thrown, the transaction is rolled back.

### 5. **JPQL (Java Persistence Query Language)**
   - JPA provides a query language called **JPQL**, which is similar to SQL but operates on Java objects instead of tables.

#### Example of JPQL Query:
```java
import javax.persistence.Query;

public List<User> getUsersByEmailDomain(String domain) {
    String jpql = "SELECT u FROM User u WHERE u.email LIKE :domain";
    Query query = entityManager.createQuery(jpql);
    query.setParameter("domain", "%" + domain);
    return query.getResultList();
}
```

### 6. **Relationships Between Entities**
   - JPA supports relationships between entities such as `OneToOne`, `OneToMany`, `ManyToOne`, and `ManyToMany`.

#### Example of a `OneToMany` Relationship:
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    // Getters and Setters
}

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String product;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
}
```
- `@OneToMany`: Specifies a one-to-many relationship (e.g., one user can have many orders).
- `@ManyToOne`: Specifies a many-to-one relationship (e.g., many orders belong to one user).

### 7. **Advantages of JPA**
   - **Object-Relational Mapping (ORM)**: JPA abstracts the complexity of SQL and allows developers to work with Java objects.
   - **Database Independence**: JPA can work with different databases without changing the code (only the database driver and dialect change).
   - **Automatic Query Generation**: JPA can generate queries based on method names in repositories, reducing boilerplate code.

### 8. **Spring Boot with JPA**
   - Spring Boot simplifies JPA integration with built-in support and configuration. With Spring Boot, you just need to include the required dependencies and configure your database connection.

#### Example of Spring Boot JPA Dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

#### Example of Spring Boot Configuration:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### 9. **Common JPA Annotations**
- `@Entity`: Marks a class as a JPA entity.
- `@Table`: Specifies the table name in the database.
- `@Id`: Denotes the primary key.
- `@GeneratedValue`: Specifies the strategy for primary key generation.
- `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`: Define relationships between entities.
- `@JoinColumn`: Specifies the foreign key column.
- `@Column`: Maps a field to a specific column in a database table.

### Summary
JPA is a powerful abstraction layer over databases, simplifying the interaction with relational data by allowing developers to work directly with Java objects. It reduces boilerplate code and provides a standardized way to manage database entities, relationships, and transactions. When combined with Spring Boot, JPA becomes even easier to use, offering quick configuration and seamless integration for building scalable and maintainable applications.


## Que 35. use of @Repository


The `@Repository` annotation in Spring is used to indicate that the class is a Data Access Object (DAO) or a mechanism for encapsulating the access to a database. It is a specialization of the `@Component` annotation, meaning that classes annotated with `@Repository` are automatically detected by Spring’s classpath scanning and are registered as Spring beans.

Here's a detailed explanation of the `@Repository` annotation and its use:

### Purpose of `@Repository`
- **Encapsulation of Database Logic**: The `@Repository` annotation is used to define a repository that handles CRUD (Create, Read, Update, Delete) operations for a particular domain object (or entity). It encapsulates the interaction with the database, making the code more modular and easier to manage.
  
- **Exception Translation**: One of the key roles of `@Repository` is to enable automatic exception translation. When a `@Repository`-annotated class interacts with the database and an exception occurs (like `SQLException`), Spring translates these low-level database exceptions into Spring’s `DataAccessException` hierarchy. This allows the application to handle database-related exceptions in a more consistent and manageable way.

### Example Usage of `@Repository`

```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query method
    User findByUsername(String username);
}
```

- **Repository Definition**: The `UserRepository` interface extends `JpaRepository` (which is part of Spring Data JPA), and the `@Repository` annotation indicates that this interface is a repository that will interact with the `User` entity.
  
- **Custom Query Methods**: You can define custom query methods like `findByUsername(String username)` that Spring Data JPA will automatically implement based on method naming conventions.

### Benefits of Using `@Repository`

1. **Automatic Bean Registration**: Classes or interfaces annotated with `@Repository` are automatically picked up by Spring's component scanning and registered as beans in the application context. This eliminates the need to manually declare them in configuration files.

2. **Exception Translation**: As mentioned earlier, the `@Repository` annotation enables exception translation. For example, SQL exceptions are translated into Spring’s `DataAccessException`, making it easier to handle exceptions consistently in the service layer. This is particularly useful when working with JDBC or any lower-level database APIs.

   Example:
   ```java
   try {
       userRepository.save(user);
   } catch (DataAccessException e) {
       // Handle Spring's DataAccessException
   }
   ```

3. **Logical Layering**: The `@Repository` annotation helps define a clear separation between layers in your application. By marking a class as a repository, you make the architecture more understandable by clearly defining that the class is responsible for data persistence.

4. **Cleaner Code**: `@Repository` allows you to encapsulate all the database interaction code in one place, keeping the business logic in the service layer clean and focused on domain logic rather than database operations.

### When to Use `@Repository`

You should use `@Repository` in any class that is responsible for database access. This includes classes that handle CRUD operations, queries, or any other form of data manipulation. Typically, this annotation is used with DAO or repository interfaces that interact with entities and perform data access tasks.

For example:
- In JPA, it is used with interfaces that extend `JpaRepository` or `CrudRepository`.
- In traditional JDBC-based applications, it can be used with classes that contain the database access logic using `JdbcTemplate`.

### Comparison to Other Stereotype Annotations

- **`@Component`**: A generic stereotype annotation for any Spring-managed component. It is a general-purpose annotation.
- **`@Service`**: A specialization of `@Component` used to indicate service layer components. It is typically used for business logic.
- **`@Repository`**: A specialization of `@Component` used to indicate data access components (DAOs or repositories).

### Conclusion

The `@Repository` annotation in Spring is crucial for data access logic. It not only makes classes eligible for Spring’s component scanning but also provides database exception translation, improving the handling of database-related exceptions. It encourages a clean separation between the persistence layer and the business logic, leading to a more maintainable and organized application.


## Que 36. is it possible to connect two different kind of database in spring


Yes, it is possible to connect to two or more different types of databases in a Spring Boot application. Spring Boot provides excellent support for managing multiple datasources, allowing you to work with different databases (e.g., MySQL, PostgreSQL, MongoDB, etc.) within the same application. This is useful for cases where you may need to integrate with different data sources or work with heterogeneous databases.

Here’s how you can achieve this:

### 1. **Define Multiple DataSource Beans**

To connect to multiple databases, you need to define multiple `DataSource` beans in your configuration. You would typically use `@Primary` to mark one `DataSource` as the default. 

### Example: Connecting to Two Relational Databases (MySQL and PostgreSQL)

Here’s an example of how to configure two different relational databases in Spring Boot:

### Step 1: Add Dependencies to `pom.xml`
Make sure you have the necessary dependencies in your `pom.xml` for the databases you want to connect to, e.g., MySQL and PostgreSQL:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>

<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```

### Step 2: Configure Multiple DataSources

You need to define multiple `DataSource` beans in your configuration class. One `DataSource` will be the primary, and the others will be secondary or specific to certain operations.

```java
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/mydb")
                .username("mysqluser")
                .password("mysqlpass")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean(name = "postgresqlDataSource")
    public DataSource postgresqlDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/myotherdb")
                .username("postgresuser")
                .password("postgrespass")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
```

### Step 3: Configure EntityManagers and TransactionManagers

You’ll need to configure separate `EntityManagerFactory` and `TransactionManager` beans for each `DataSource` if you're working with JPA.

#### MySQL Configuration:

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.mysql.repository", 
        entityManagerFactoryRef = "mysqlEntityManagerFactory", 
        transactionManagerRef = "mysqlTransactionManager"
)
public class MysqlConfig {

    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource mysqlDataSource;

    @Bean(name = "mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(mysqlDataSource)
                .packages("com.example.mysql.model")  // Entities package
                .persistenceUnit("mysqlPU")
                .build();
    }

    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(
            @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory mysqlEntityManagerFactory) {
        return new JpaTransactionManager(mysqlEntityManagerFactory);
    }
}
```

#### PostgreSQL Configuration:

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.postgresql.repository", 
        entityManagerFactoryRef = "postgresqlEntityManagerFactory", 
        transactionManagerRef = "postgresqlTransactionManager"
)
public class PostgresqlConfig {

    @Autowired
    @Qualifier("postgresqlDataSource")
    private DataSource postgresqlDataSource;

    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(postgresqlDataSource)
                .packages("com.example.postgresql.model")  // Entities package
                .persistenceUnit("postgresqlPU")
                .build();
    }

    @Bean(name = "postgresqlTransactionManager")
    public PlatformTransactionManager postgresqlTransactionManager(
            @Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory postgresqlEntityManagerFactory) {
        return new JpaTransactionManager(postgresqlEntityManagerFactory);
    }
}
```

### Step 4: Use the Appropriate Repository

In your application, you will use the appropriate repositories that are linked to the correct databases.

- Repositories for MySQL:
  ```java
  @Autowired
  private UserRepository userRepository;  // MySQL repository
  ```

- Repositories for PostgreSQL:
  ```java
  @Autowired
  private OrderRepository orderRepository;  // PostgreSQL repository
  ```

### 2. **Handling Multiple Databases (Relational + NoSQL)**

You can also connect to a combination of relational databases (e.g., MySQL) and NoSQL databases (e.g., MongoDB) in the same Spring Boot application.

#### Example of Connecting to MongoDB and MySQL

1. Add dependencies for MongoDB in `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

2. Configure MongoDB `MongoTemplate`:

```java
@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        return new MongoTemplate(mongoClient, "myMongoDb");
    }
}
```

Now you can use repositories for both MySQL (JPA) and MongoDB (Spring Data MongoDB) in the same application.

### Summary

Spring Boot makes it easy to connect to multiple databases within the same application. Whether you're connecting to two different relational databases or mixing relational with NoSQL, you can define multiple `DataSource` beans, configure entity managers and transaction managers for each data source, and manage your repositories accordingly.

This capability allows for flexible and scalable designs, enabling an application to interact with various databases as required.



## Que 37. Stream APIs


The Stream API, introduced in Java 8, is a powerful tool for processing sequences of elements in a functional style. It provides a high-level abstraction for performing operations like filtering, mapping, reducing, and collecting on data collections such as Lists, Sets, and Maps.

### Key Concepts of Stream API

1. **Stream**: A sequence of elements that supports various operations to process data. Streams can be created from collections, arrays, or I/O channels.
2. **Intermediate Operations**: Operations that transform the stream into another stream. These are lazy, meaning they are only executed when a terminal operation is invoked.
   - Examples: `filter()`, `map()`, `sorted()`, `distinct()`
3. **Terminal Operations**: Operations that produce a result or a side-effect. They trigger the processing of the stream pipeline.
   - Examples: `collect()`, `forEach()`, `reduce()`, `count()`
4. **Pipelining**: Streams support pipelining, which allows multiple operations to be chained together.
5. **Internal Iteration**: Unlike external iteration (e.g., `for` loop), Streams manage the iteration for you.

### Example of Stream API

Here’s a simple example that demonstrates the basic usage of Stream API:

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Edward");

        // Convert all names to uppercase and filter names starting with "A"
        List<String> result = names.stream()
                .map(String::toUpperCase)          // Intermediate operation
                .filter(name -> name.startsWith("A"))  // Intermediate operation
                .collect(Collectors.toList());     // Terminal operation

        System.out.println(result);  // Output: [ALICE]
    }
}
```

### Intermediate Operations

These are operations that return a new stream, allowing further operations to be applied.

- **`map()`**: Transforms each element of the stream.
  ```java
  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
  List<Integer> squaredNumbers = numbers.stream()
                                         .map(n -> n * n)
                                         .collect(Collectors.toList());
  ```
- **`filter()`**: Filters elements based on a condition (predicate).
  ```java
  List<String> filteredNames = names.stream()
                                    .filter(name -> name.length() > 3)
                                    .collect(Collectors.toList());
  ```
- **`sorted()`**: Sorts the stream’s elements.
  ```java
  List<String> sortedNames = names.stream()
                                  .sorted()
                                  .collect(Collectors.toList());
  ```
- **`distinct()`**: Removes duplicates from the stream.
  ```java
  List<Integer> distinctNumbers = numbers.stream()
                                         .distinct()
                                         .collect(Collectors.toList());
  ```

### Terminal Operations

These are operations that return a result or cause a side effect. Once a terminal operation is applied, the stream is considered consumed and cannot be reused.

- **`collect()`**: Collects the result of the stream into a collection or other data structure.
  ```java
  List<String> list = stream.collect(Collectors.toList());
  ```
- **`forEach()`**: Performs an action for each element of the stream.
  ```java
  names.stream().forEach(System.out::println);
  ```
- **`reduce()`**: Reduces the elements of the stream to a single value using a binary operator.
  ```java
  int sum = numbers.stream().reduce(0, (a, b) -> a + b);
  ```
- **`count()`**: Counts the number of elements in the stream.
  ```java
  long count = names.stream().count();
  ```

### Lazy Evaluation

Stream operations are lazy, meaning intermediate operations are not executed until a terminal operation is invoked. This allows for optimized execution and better performance.

For example, even if you chain multiple intermediate operations, they won’t execute until the terminal operation is called.

### Stream Creation

You can create streams from various sources:

- **From Collections**: 
  ```java
  List<String> list = Arrays.asList("a", "b", "c");
  Stream<String> stream = list.stream();
  ```
- **From Arrays**:
  ```java
  int[] array = {1, 2, 3, 4, 5};
  IntStream stream = Arrays.stream(array);
  ```
- **From Values**:
  ```java
  Stream<String> stream = Stream.of("a", "b", "c");
  ```
- **From Files**:
  ```java
  Stream<String> lines = Files.lines(Paths.get("file.txt"));
  ```

### Parallel Streams

Streams can also be executed in parallel to take advantage of multi-core processors. This can be achieved by converting the stream into a parallel stream using `parallelStream()`:

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> squares = numbers.parallelStream()
                               .map(n -> n * n)
                               .collect(Collectors.toList());
```

Parallel streams automatically divide the data into chunks and process them in parallel threads, potentially speeding up the processing of large datasets.

### Summary

The Stream API in Java is a powerful and flexible tool for data processing. It enables a more functional and declarative approach to handling collections, making code more readable and easier to maintain. Whether you’re filtering, mapping, sorting, or reducing, the Stream API provides a clean and efficient way to work with data in Java.

## Que 38. functional interface


A **functional interface** in Java is an interface that contains exactly one abstract method. These interfaces can have multiple default or static methods, but they must have only one abstract method to qualify as a functional interface. They are used primarily to implement lambda expressions and method references, making them a crucial part of functional programming in Java.

### Key Characteristics of Functional Interfaces

1. **Single Abstract Method**: A functional interface must have only one abstract method. This is the method that will be implemented by the lambda expression or method reference.
2. **Default and Static Methods**: Functional interfaces can have any number of default or static methods. These methods can provide common functionality that can be shared across implementations.
3. **@FunctionalInterface Annotation**: While not mandatory, it’s a good practice to annotate functional interfaces with `@FunctionalInterface`. This annotation helps to clearly indicate the intent and also provides compile-time checking to ensure that the interface meets the criteria of a functional interface.

### Examples of Functional Interfaces

1. **Using Built-in Functional Interfaces**

Java 8 introduced several built-in functional interfaces in the `java.util.function` package, such as:

- **`Predicate<T>`**: Represents a boolean-valued function of one argument.
  ```java
  Predicate<String> isEmpty = str -> str.isEmpty();
  boolean result = isEmpty.test("");  // Returns true
  ```

- **`Function<T, R>`**: Represents a function that accepts one argument and produces a result.
  ```java
  Function<String, Integer> stringLength = str -> str.length();
  int length = stringLength.apply("Hello");  // Returns 5
  ```

- **`Consumer<T>`**: Represents an operation that accepts a single input argument and returns no result.
  ```java
  Consumer<String> print = str -> System.out.println(str);
  print.accept("Hello, World!");  // Prints: Hello, World!
  ```

- **`Supplier<T>`**: Represents a supplier of results (no input).
  ```java
  Supplier<Double> randomValue = () -> Math.random();
  double value = randomValue.get();  // Returns a random double
  ```

- **`BiFunction<T, U, R>`**: Represents a function that accepts two arguments and produces a result.
  ```java
  BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
  int sum = add.apply(5, 10);  // Returns 15
  ```

2. **Creating a Custom Functional Interface**

Here’s an example of creating a custom functional interface:

```java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);  // Single abstract method
}

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        // Using a lambda expression to implement the functional interface
        Greeting greeting = name -> System.out.println("Hello, " + name + "!");
        
        greeting.sayHello("Alice");  // Prints: Hello, Alice!
    }
}
```

### Advantages of Functional Interfaces

1. **Simplified Syntax**: They allow for a cleaner and more concise way to pass behavior as parameters using lambda expressions.
2. **Improved Code Readability**: Using functional interfaces can make your code more readable and expressive by abstracting implementation details.
3. **Encourages Functional Programming**: They facilitate a functional programming style, making it easier to write and manage code that deals with collections and streams.

### Common Uses

Functional interfaces are widely used in the following scenarios:

- **Lambda Expressions**: They are primarily used to define the implementation of a single method, allowing for cleaner syntax and easier code maintenance.
- **Method References**: Functional interfaces can be used as method references, enabling more concise and readable code.
- **Streams API**: Many operations in the Streams API utilize functional interfaces to process collections, such as filtering, mapping, and reducing.

### Summary

Functional interfaces are a fundamental concept in Java that supports functional programming paradigms. They allow developers to write cleaner, more expressive code using lambda expressions and method references. By defining single abstract methods, functional interfaces enable the abstraction of behavior, making it easier to work with collections and apply functional programming techniques effectively.


## Que 39. lambda expression


A **lambda expression** in Java is a concise way to represent an anonymous function (a function without a name). It allows you to express instances of functional interfaces (interfaces with a single abstract method) in a more compact and readable form. Lambda expressions were introduced in Java 8 and are a key feature of the language, particularly in the context of functional programming and the Streams API.

### Syntax of Lambda Expressions

The basic syntax of a lambda expression is as follows:

```java
(parameters) -> expression
```

or, if there are multiple statements:

```java
(parameters) -> { statements; }
```

### Components of a Lambda Expression

1. **Parameters**: The input parameters for the lambda expression. You can omit the type since it can be inferred by the compiler.
2. **Arrow Operator (`->`)**: Separates the parameter list from the body of the lambda expression.
3. **Body**: The implementation of the lambda expression, which can be a single expression or a block of code.

### Examples of Lambda Expressions

1. **Using a Functional Interface**

Here's an example of using a lambda expression with a functional interface:

```java
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

public class LambdaExample {
    public static void main(String[] args) {
        // Lambda expression for addition
        MathOperation addition = (a, b) -> a + b;

        // Using the lambda expression
        int result = addition.operate(5, 3);  // result is 8
        System.out.println("Addition: " + result);
    }
}
```

2. **Using Lambda Expressions with Collections**

Lambda expressions are often used with collections to perform operations like filtering or transforming data. For example, using a lambda expression to filter a list:

```java
import java.util.Arrays;
import java.util.List;

public class LambdaWithCollections {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Filter names starting with "A"
        names.stream()
             .filter(name -> name.startsWith("A"))  // Lambda expression
             .forEach(System.out::println);         // Method reference
    }
}
```

### Advantages of Lambda Expressions

1. **Conciseness**: They reduce boilerplate code, making it easier to express instances of functional interfaces in a compact form.
2. **Readability**: They enhance code readability by allowing you to express behavior clearly and directly inline where it is used.
3. **Support for Functional Programming**: They enable a functional programming style, allowing for operations on collections and data in a more expressive manner.
4. **Improved API Design**: They facilitate the design of APIs that can accept behavior as parameters.

### Contextual Parameters and Type Inference

1. **Type Inference**: You can omit the parameter types in a lambda expression since they can be inferred from the context. For example:

   ```java
   // No need to specify types
   MathOperation multiplication = (a, b) -> a * b;  // Type inferred
   ```

2. **Single Parameter**: If the lambda expression has a single parameter, you can omit the parentheses:

   ```java
   // Single parameter with parentheses omitted
   UnaryOperator<String> toUpperCase = str -> str.toUpperCase();
   ```

### Lambda Expressions with Method References

Lambda expressions can often be replaced with method references for improved readability. For example:

```java
// Using a lambda expression
names.forEach(name -> System.out.println(name));

// Replaced with a method reference
names.forEach(System.out::println);
```

### Summary

Lambda expressions in Java provide a powerful way to write functional-style code. They enhance the expressiveness and conciseness of your code while allowing you to work effectively with functional interfaces, collections, and the Streams API. By simplifying the implementation of single-method interfaces, lambda expressions enable developers to write cleaner and more maintainable code.


## Que 40. give example of many to many relationship


In a many-to-many relationship, multiple records in one table can relate to multiple records in another table. This is a common scenario in database design, and in Java Spring Boot, it can be implemented using JPA (Java Persistence API).

### Example Scenario

Let’s consider a simple example of a `Student` and `Course` relationship:

- A **Student** can enroll in multiple **Courses**.
- A **Course** can have multiple **Students** enrolled.

To implement this in Java Spring Boot with JPA, you will typically need a join table to manage the many-to-many relationship.

### Step-by-Step Implementation

1. **Entity Classes**:
   - Create the `Student` entity.
   - Create the `Course` entity.
   - Use a join table to manage the many-to-many relationship.

2. **Join Table**: You can specify a join table in the entity relationships.

#### 1. Student Entity

```java
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();

    // Constructors, getters, and setters
    public Student() {
    }

    public Student(String name) {
        this.name = name;
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
```

#### 2. Course Entity

```java
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();

    // Constructors, getters, and setters
    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
```

### 3. Database Schema

The above entities will generate three tables in the database:

1. `students` (id, name)
2. `courses` (id, title)
3. `student_course` (student_id, course_id) - Join table to manage the many-to-many relationship.

### 4. Saving Data

You can create a service class to manage the data persistence, where you can add students to courses and vice versa.

#### Example of Saving Data

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentCourseService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }
}
```

### 5. Example Repositories

Create repositories for `Student` and `Course`.

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

public interface CourseRepository extends JpaRepository<Course, Long> {
}
```

### Summary

In this example, we defined a many-to-many relationship between `Student` and `Course` using JPA in a Spring Boot application. The relationship is managed by a join table called `student_course`, allowing for the association of multiple students to multiple courses. This structure is efficient for querying and managing such relationships in a relational database.


## Que 41. JPA vs Hibernate

Absolutely! Let’s dive deeper into the details of JPA and Hibernate, including their architecture, features, and use cases.

JPA (Java Persistence API)
Overview
Standard Specification: JPA was introduced in Java EE (now Jakarta EE) to standardize ORM in Java. It is part of the Java EE platform.
Interface-Based: It defines interfaces and annotations that serve as a contract for how ORM should be performed. This allows for portability across different implementations.
Key Components
Entities: Java classes that map to database tables. Each instance corresponds to a row in the table.
Entity Manager: The primary interface for interacting with the persistence context. It manages the lifecycle of entities and facilitates CRUD operations.
Persistence Context: A set of entity instances managed by the Entity Manager, serving as a cache for objects.
Transactions: JPA provides a way to manage transactions, usually through the Java Transaction API (JTA).
Query Language: JPA includes JPQL (Java Persistence Query Language), which is similar to SQL but operates on entity objects instead of directly on database tables.
Annotations
Common JPA annotations include:

@Entity: Marks a class as an entity.
@Table: Specifies the database table to which the entity is mapped.
@Id: Defines the primary key of the entity.
@GeneratedValue: Indicates how primary keys should be generated.
@ManyToOne, @OneToMany, @OneToOne, @ManyToMany: Define relationships between entities.
Use Cases
JPA is ideal when you want to:

Write database-agnostic code that can work with various relational databases.
Maintain portability and flexibility in your application.
Hibernate
Overview
ORM Framework: Hibernate is an open-source ORM framework that implements the JPA specification. It was created to simplify database interactions and reduce boilerplate code.
Advanced Features: While Hibernate adheres to JPA, it also includes additional features that enhance its capabilities beyond the standard specification.
Key Features
Session and Session Factory: Hibernate uses the Session interface for managing the connection to the database and the SessionFactory for creating Session instances.
Caching: Hibernate supports first-level (session) and second-level (application-wide) caching, which can significantly improve performance by reducing database access.
Batch Processing: Allows the execution of multiple operations in a single database round-trip, improving efficiency.
Criteria API: An alternative to JPQL, allowing for type-safe dynamic query construction.
Custom Types: Hibernate allows the use of custom types and user-defined types, offering more flexibility in mapping Java types to SQL types.
Hibernate Annotations
In addition to standard JPA annotations, Hibernate provides:

@Type: For specifying custom data types.
@Filter: For applying filtering criteria to queries at the entity level.
@Cascade: To manage cascading operations (like delete or save) across associated entities.
Use Cases
Hibernate is a good choice when you need:

More control over database interactions and optimizations.
Features beyond basic JPA functionality, such as advanced caching, batch processing, or custom mappings.
Integration with existing projects that use Hibernate-specific features.
Comparing JPA and Hibernate
Feature	JPA	Hibernate
Nature	Specification	Implementation of JPA
Portability	Vendor-agnostic	Hibernate-specific features may limit portability
Complexity	Simpler with standard features	More complex with additional features
Query Language	JPQL	JPQL + HQL (Hibernate Query Language)
Caching	Basic support (depends on provider)	Extensive caching capabilities
Customization	Limited to JPA specification	High level of customization
Session Management	Through EntityManager	Session and SessionFactory
Conclusion
In summary, JPA provides a standardized approach for ORM in Java, making it easier to develop database-agnostic applications. Hibernate, as a popular JPA implementation, adds a wealth of features and optimizations, making it suitable for more complex applications that require advanced functionality. When choosing between the two, consider the specific needs of your application, such as the required features, performance optimizations, and level of customization.

## Que 42. When to use which type of dependancy injection

In Spring, dependency injection (DI) is a core principle that allows you to manage the dependencies between different components of your application effectively. There are several ways to inject dependencies in Spring, including constructor injection, setter injection, and field injection. Below are the different methods along with examples:

### 1. Constructor Injection
Constructor injection involves providing the dependencies of a class through its constructor. This method is preferred for required dependencies because it makes the dependency explicit.

**Example:**
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ServiceA {
    public void performAction() {
        System.out.println("ServiceA is performing an action.");
    }
}

@Component
class ServiceB {
    private final ServiceA serviceA;

    @Autowired // Marks the constructor for dependency injection
    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public void execute() {
        serviceA.performAction();
    }
}
```

### 2. Setter Injection
Setter injection involves providing the dependencies through setter methods after the object has been constructed. This method allows for optional dependencies.

**Example:**
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ServiceC {
    public void performAction() {
        System.out.println("ServiceC is performing an action.");
    }
}

@Component
class ServiceD {
    private ServiceC serviceC;

    @Autowired // Marks the setter for dependency injection
    public void setServiceC(ServiceC serviceC) {
        this.serviceC = serviceC;
    }

    public void execute() {
        serviceC.performAction();
    }
}
```

### 3. Field Injection
Field injection directly injects dependencies into the fields of a class. This method is less preferred due to difficulties in testing and managing dependencies, but it is often used for its simplicity.

**Example:**
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ServiceE {
    public void performAction() {
        System.out.println("ServiceE is performing an action.");
    }
}

@Component
class ServiceF {
    @Autowired // Directly injects the dependency
    private ServiceE serviceE;

    public void execute() {
        serviceE.performAction();
    }
}
```

### 4. Interface-based Injection
In some cases, you can use interfaces to define setter methods for injection. This is useful when you want to enforce certain behaviors across different implementations.

**Example:**
```java
interface ServiceG {
    void performAction();
}

@Component
class ServiceGImpl implements ServiceG {
    public void performAction() {
        System.out.println("ServiceGImpl is performing an action.");
    }
}

@Component
class ServiceH {
    private ServiceG serviceG;

    @Autowired
    public void setServiceG(ServiceG serviceG) {
        this.serviceG = serviceG;
    }

    public void execute() {
        serviceG.performAction();
    }
}
```

### 5. Constructor Injection with Qualifiers
When there are multiple beans of the same type, you can use the `@Qualifier` annotation to specify which bean to inject.

**Example:**
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
class ServiceI {
    public void performAction() {
        System.out.println("ServiceI is performing an action.");
    }
}

@Component
class ServiceJ {
    public void performAction() {
        System.out.println("ServiceJ is performing an action.");
    }
}

@Component
class ServiceK {
    private final ServiceI serviceI;

    @Autowired
    public ServiceK(@Qualifier("serviceI") ServiceI serviceI) {
        this.serviceI = serviceI;
    }

    public void execute() {
        serviceI.performAction();
    }
}
```

### 6. Configuration-based Injection
You can also define beans and their dependencies using Java configuration classes. This allows you to create a more complex object graph.

**Example:**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfig {
    @Bean
    public ServiceA serviceA() {
        return new ServiceA();
    }

    @Bean
    public ServiceB serviceB() {
        return new ServiceB(serviceA()); // Injecting dependency
    }
}
```

### 7. Spring Profiles
Using Spring Profiles, you can create different configurations for different environments (e.g., development, production) and inject beans accordingly.

**Example:**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {
    
    @Bean
    @Profile("dev")
    public Service devService() {
        return new DevService();
    }

    @Bean
    @Profile("prod")
    public Service prodService() {
        return new ProdService();
    }
}
```

### Summary
- **Constructor Injection**: Preferred for required dependencies; ensures that the dependency is provided during object creation.
- **Setter Injection**: Allows for optional dependencies; can modify dependencies after object creation.
- **Field Injection**: Simplifies dependency injection but is less preferred due to difficulties in testing.
- **Interface-based Injection**: Enforces a contract for dependency injection.
- **Configuration-based Injection**: Provides a way to define beans and their dependencies using Java configuration.
- **Spring Profiles**: Enables different configurations for different environments, allowing selective injection of beans.

Each method has its pros and cons, and the choice of which to use depends on the specific use case, coding style preferences, and requirements of the application.



## Que 43. Upcasting only have super class methods

This Java code demonstrates the concept of inheritance, method overriding, and polymorphism. Let's analyze the provided code step-by-step to understand its behavior and output.

### Code Explanation

1. **Class Definitions**:
   - **Parent Class**: 
     - Contains three methods: `walk()`, `run()`, and `sleep()`.
     - `run()` method calls `walk()` method. Since `walk()` is not overridden in `Child`, it will execute `Parent`'s `walk()` method unless overridden.
   
   - **Child Class**:
     - Inherits from `Parent`.
     - Overrides the `walk()` and `run()` methods.
     - In the overridden `run()` method, it first prints "Child run", then calls the superclass's `run()` method using `super.run()`, and finally calls its own `walk()` method.

2. **Main Class**:
   - In the `main` method:
     - A `Parent` reference `p` is created and assigned an instance of `Child`.
     - A `Child` reference `c` is cast from `p`.
     - Calls `p.run()`, `c.sit()`, and `p.sleep()`.

### Execution Steps

1. **Call to `p.run()`**:
   - The `run()` method in `Child` is invoked because of dynamic method dispatch (runtime polymorphism).
   - Output: 
     ```
     Child run
     ```
   - Then `super.run()` is called, which invokes `run()` in the `Parent` class:
     - Inside `Parent.run()`, it prints:
     ```
     Parent run
     ```
   - The `Parent.run()` method then calls `walk()`, which is overridden in `Child`, so `Child.walk()` is called:
     ```
     Child walk
     ```

   **Output so far**:
   ```
   Child run
   Parent run
   Child walk
   ```

2. **Call to `c.sit()`**:
   - The `sit()` method from the `Child` class is called.
   - Output: 
     ```
     Child sit
     ```

3. **Call to `p.sleep()`**:
   - This calls the `sleep()` method from the `Parent` class, since there is no override in `Child`.
   - Output:
     ```
     Parent sleep
     ```

### Final Output

Combining all the outputs from the calls made, the final output of the program will be:
```
Child run
Parent run
Child walk
Child sit
Parent sleep
```

### Conclusion

This code snippet effectively illustrates:
- **Inheritance**: The `Child` class inherits from the `Parent` class.
- **Method Overriding**: The `Child` class overrides the `walk()` and `run()` methods.
- **Polymorphism**: The method called depends on the actual object type (the instance of `Child`), not the reference type (`Parent`).
- **Casting**: The `Parent` reference is cast to `Child` to access methods specific to `Child`. 

This example is useful for understanding how Java handles method overriding and dynamic binding in an inheritance hierarchy.
