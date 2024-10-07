## UpCasting & DownCasting In Java

```java
class Parent {
    void walk() {
        System.out.println("Parent walk");
    }

    void run() {
        System.out.println("Parent run");
        walk();
    }
}


class Child extends Parent {
    @Override
    void walk() {
        System.out.println("Child walk");
    }

    @Override
    void run() {
        System.out.println("Child run");
        super.run();
        walk();
    }
}

public class Nnn {
    public static void main(String[] args) {
        Parent p = new Child();
        p.run();
    }
}
```

Output
```
Child run
Parent run
Child walk
Child walk
```

**Polymorphism:** In Java, when you create an object like Parent p = new Child();, the reference is of type Parent, but the actual object is of type Child. When p.run() is called, it uses the run() method of the Child class because of dynamic method dispatch (runtime polymorphism).

**Super Keyword:** In Child.run(), super.run() calls the run() method from the Parent class. Inside the Parent's run() method, the walk() method is called. However, since the walk() method is overridden in the Child class, the overridden version in Child will be executed.

Explanation:

> The first line Child run comes from p.run() calling Child's run().
> The second line Parent run comes from super.run() in Child's run() method.
> The third line Child walk is called from Parent's run() method, but since walk() is overridden in Child, the Child's walk() method is invoked.
> The fourth line Child walk is called directly in Child's run() method.

```java
class Parent {
    void walk() {
        System.out.println("Parent walk");
    }

    void run() {
        System.out.println("Parent run");
        walk();
    }
}


class Child extends Parent {
    @Override
    void walk() {
        System.out.println("Child walk");
    }

    @Override
    void run() {
        System.out.println("Child run");
        super.run();
        walk();
    }
}

public class Main {
    public static void main(String[] args) {
        Child p = new Child();
        p.run();
    }
}
```
```
Child run
Parent run
Child walk
Child walk
```

Here's a breakdown of what happens:

> p.run() calls Child's run() method.
> Child's run() method prints "Child run".
> super.run() calls Parent's run() method.
> Parent's run() method prints "Parent run" and calls walk().
> Since walk() is overridden in Child, "Child walk" is printed (due to polymorphism).
> Finally, Child's run() method calls its own walk(), printing "Child walk" again.


```java
class Parent {
    void walk() {
        System.out.println("Parent walk");
    }

    void run() {
        System.out.println("Parent run");
        walk();
    }
}


class Child extends Parent {
    @Override
    void walk() {
        System.out.println("Child walk");
    }

    @Override
    void run() {
        System.out.println("Child run");
        super.run();
        walk();
    }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Parent();
        p.run();
    }
}
```

> The code you provided works correctly, but the behavior is slightly different compared to the previous version where you created an instance of the Child class. Here, you are creating an instance of the Parent class. Since the object is of type Parent, there is no polymorphism or method overriding involved.

### Explanation:

**Object Creation:**
You are creating an object p of type Parent with Parent p = new Parent();.
Since the object is an instance of Parent, when you call p.run(), it will execute the run() method defined in the Parent class.

**No Polymorphism:**
Unlike the previous case where the object was of type Child, here the object is strictly a Parent, so no method from the Child class is involved.

**Method Execution:**
The run() method in Parent will be executed.
Inside Parent's run() method, it calls the walk() method from the Parent class (because no overriding is involved).

```java
class Parent {
    void walk() {
        System.out.println("Parent walk");
    }

    void run() {
        System.out.println("Parent run");
        walk();
    }
}


class Child extends Parent {
    @Override
    void walk() {
        System.out.println("Child walk");
    }

    @Override
    void run() {
        System.out.println("Child run");
        super.run();
        walk();
    }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Child();
        p = new Parent();
        p.run();
    }
}
```

### Detailed Walkthrough:

> The Parent reference p initially points to a Child object. At this point, if you were to call p.run(), it would invoke the overridden run() method in the Child class, followed by the walk() method in the Child class.

> However, before any method is called, p is reassigned to a new Parent object. Therefore, when you call p.run(), it uses the run() method of the Parent class.

>Inside the Parent's run() method, it calls the walk() method of the Parent class (since p is now strictly a Parent object), and so both Parent run and Parent walk are printed.