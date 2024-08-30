# Design Pattern

## Creational Patterns

### Singleton
* Expanded Definition: The Singleton pattern restricts the instantiation of a class to a single instance. This is useful when exactly one object is needed to coordinate actions across the system.
* Detailed Analogy: Think of a school principal. A school typically has only one principal to manage administrative tasks and decisions. If anyone needs to interact with the principal, they interact with the same individual.
Additional Code Example:
```java
public class Principal {
    private static Principal instance;

    private Principal() {
        // private constructor to prevent instantiation
    }

    public static Principal getInstance() {
        if (instance == null) {
            synchronized (Principal.class) {
                if (instance == null) {
                    instance = new Principal();
                }
            }
        }
        return instance;
    }

    public void manageSchool() {
        System.out.println("Managing the school.");
    }
}
```

### Factory Method
* Expanded Definition: The Factory Method pattern defines an interface for creating an object, but allows subclasses to alter the type of objects that will be created.
* Detailed Analogy: Imagine a bakery that produces different types of bread (e.g., wheat bread, rye bread, sourdough). The bakery's bread production line is the factory method that determines which type of bread to produce based on the ingredients.
Additional Code Example:
```java
abstract class Bread {
    abstract void bake();
}

class WheatBread extends Bread {
    void bake() {
        System.out.println("Baking wheat bread.");
    }
}

class RyeBread extends Bread {
    void bake() {
        System.out.println("Baking rye bread.");
    }
}

abstract class Bakery {
    abstract Bread createBread();

    public void bakeBread() {
        Bread bread = createBread();
        bread.bake();
    }
}

class WheatBakery extends Bakery {
    Bread createBread() {
        return new WheatBread();
    }
}

class RyeBakery extends Bakery {
    Bread createBread() {
        return new RyeBread();
    }
}
```

### Abstract Factory
* Expanded Definition: The Abstract Factory pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes. It involves multiple factory methods within a super factory (i.e., factory of factories).
* Detailed Analogy: Consider a company that manufactures different types of cars (e.g., electric cars and gasoline cars). Each type of car has various parts like engines and tires. The abstract factory is like a car manufacturing plant that creates specific factories for electric car parts and gasoline car parts.
Additional Code Example:
```java
interface Engine {
    void assemble();
}

class ElectricEngine implements Engine {
    public void assemble() {
        System.out.println("Assembling electric engine.");
    }
}

class GasolineEngine implements Engine {
    public void assemble() {
        System.out.println("Assembling gasoline engine.");
    }
}

interface Tire {
    void produce();
}

class ElectricTire implements Tire {
    public void produce() {
        System.out.println("Producing tire for electric car.");
    }
}

class GasolineTire implements Tire {
    public void produce() {
        System.out.println("Producing tire for gasoline car.");
    }
}

interface CarFactory {
    Engine createEngine();
    Tire createTire();
}

class ElectricCarFactory implements CarFactory {
    public Engine createEngine() {
        return new ElectricEngine();
    }

    public Tire createTire() {
        return new ElectricTire();
    }
}

class GasolineCarFactory implements CarFactory {
    public Engine createEngine() {
        return new GasolineEngine();
    }

    public Tire createTire() {
        return new GasolineTire();
    }
}
```

### Builder
* Expanded Definition: The Builder pattern separates the construction of a complex object from its representation. It allows you to create different types and representations of an object using the same building process.
* Detailed Analogy: Imagine ordering a custom sandwich at a deli. You specify each ingredient, such as bread type, meat, cheese, and vegetables. The deli worker (builder) constructs the sandwich step by step according to your specifications.
Additional Code Example:
```java
class Sandwich {
    private String bread;
    private String meat;
    private String cheese;
    private String vegetables;

    public static class Builder {
        private String bread;
        private String meat;
        private String cheese;
        private String vegetables;

        public Builder bread(String bread) {
            this.bread = bread;
            return this;
        }

        public Builder meat(String meat) {
            this.meat = meat;
            return this;
        }

        public Builder cheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public Builder vegetables(String vegetables) {
            this.vegetables = vegetables;
            return this;
        }

        public Sandwich build() {
            Sandwich sandwich = new Sandwich();
            sandwich.bread = this.bread;
            sandwich.meat = this.meat;
            sandwich.cheese = this.cheese;
            sandwich.vegetables = this.vegetables;
            return sandwich;
        }
    }

    @Override
    public String toString() {
        return "Sandwich [bread=" + bread + ", meat=" + meat + ", cheese=" + cheese + ", vegetables=" + vegetables + "]";
    }
}

public class BuilderPatternDemo {
    public static void main(String[] args) {
        Sandwich sandwich = new Sandwich.Builder()
            .bread("Whole Grain")
            .meat("Turkey")
            .cheese("Cheddar")
            .vegetables("Lettuce, Tomato")
            .build();

        System.out.println(sandwich);
    }
}
```

### Prototype
* Expanded Definition: The Prototype pattern creates new objects by copying an existing object, known as the prototype. This is useful for creating new instances of complex objects without the overhead of creating them from scratch.
* Detailed Analogy: Consider a company that designs custom-made suits. Instead of measuring and creating a suit from scratch each time, they have a base prototype suit that they modify according to the customer's measurements and preferences.
Additional Code Example:
```java
class Suit implements Cloneable {
    private String fabric;
    private String size;

    public Suit(String fabric, String size) {
        this.fabric = fabric;
        this.size = size;
    }

    public Suit clone() throws CloneNotSupportedException {
        return (Suit) super.clone();
    }

    @Override
    public String toString() {
        return "Suit [fabric=" + fabric + ", size=" + size + "]";
    }
}

public class PrototypePatternDemo {
    public static void main(String[] args) {
        try {
            Suit prototypeSuit = new Suit("Wool", "Medium");
            Suit customSuit = prototypeSuit.clone();
            customSuit.size = "Large";

            System.out.println(prototypeSuit);
            System.out.println(customSuit);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
```

## Structural Patterns

### Adapter
* Expanded Definition: The Adapter pattern allows incompatible interfaces to work together by converting one interface into another that the client expects. This pattern involves a single class which is responsible to join functionalities of independent or incompatible interfaces.
* Detailed Analogy: Think of a universal charger adapter that allows you to plug your electronic device into different types of electrical sockets around the world.
Additional Code Example:
```java
interface AmericanPlug {
    void plugIn();
}

class AmericanDevice implements AmericanPlug {
    public void plugIn() {
        System.out.println("Plugged in using American plug.");
    }
}

interface EuropeanPlug {
    void connect();
}

class EuropeanAdapter implements EuropeanPlug {
    private AmericanPlug americanPlug;

    public EuropeanAdapter(AmericanPlug americanPlug) {
        this.americanPlug = americanPlug;
    }

    public void connect() {
        americanPlug.plugIn();
    }
}

public class AdapterPatternDemo {
    public static void main(String[] args) {
        AmericanPlug americanDevice = new AmericanDevice();
        EuropeanPlug adapter = new EuropeanAdapter(americanDevice);

        adapter.connect(); // Output: Plugged in using American plug.
    }
}
```

### Bridge
* Expanded Definition: The Bridge pattern decouples an abstraction from its implementation so that the two can vary independently. This pattern involves an interface which acts as a bridge which makes the functionality of concrete classes independent from interface implementer classes.
* Detailed Analogy: Think of a remote control (abstraction) that can operate different types of devices like a TV, sound system, or air conditioner (implementation). The remote control works the same way regardless of the device it operates.
Additional Code Example:
```java
interface Remote {
    void turnOn();
    void turnOff();
}

class TVRemote implements Remote {
    public void turnOn() {
        System.out.println("TV is on.");
    }

    public void turnOff() {
        System.out.println("TV is off.");
    }
}

class AdvancedRemote extends TVRemote {
    public void mute() {
        System.out.println("TV is muted.");
    }
}

abstract class Device {
    protected Remote remote;

    public Device(Remote remote) {
        this.remote = remote;
    }

    abstract void operate();
}

class TV extends Device {
    public TV(Remote remote) {
        super(remote);
    }

    public void operate() {
        System.out.println("Operating TV with remote.");
        remote.turnOn();
        remote.turnOff();
    }
}

public class BridgePatternDemo {
    public static void main(String[] args) {
        Remote tvRemote = new TVRemote();
        Device tv = new TV(tvRemote);

        tv.operate(); // Output: Operating TV with remote.
                      //         TV is on.
                      //         TV is off.
    }
}
```

### Composite
* Expanded Definition: The Composite pattern allows you to compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly.
* Detailed Analogy: Think of a company where there are different levels of employees. A manager can have subordinates who are also managers or regular employees. The company can be represented as a tree where each node is either an individual employee or a composite of multiple employees.
Additional Code Example:
```java
interface Employee {
    void showEmployeeDetails();
}

class Developer implements Employee {
    private String name;
    private long empId;

    public Developer(String name, long empId) {
        this.name = name;
        this.empId = empId;
    }

    public void showEmployeeDetails() {
        System.out.println(empId + " " + name);
    }
}

class Manager implements Employee {
    private String name;
    private long empId;

    public Manager(String name, long empId) {
        this.name = name;
        this.empId = empId;
    }

    public void showEmployeeDetails() {
        System.out.println(empId + " " + name);
    }
}

class CompanyDirectory implements Employee {
    private List<Employee> employeeList = new ArrayList<Employee>();

    public void addEmployee(Employee emp) {
        employeeList.add(emp);
    }

    public void removeEmployee(Employee emp) {
        employeeList.remove(emp);
    }

    public void showEmployeeDetails() {
        for (Employee emp : employeeList) {
            emp.showEmployeeDetails();
        }
    }
}

public class CompositePatternDemo {
    public static void main(String[] args) {
        Developer dev1 = new Developer("John", 100);
        Developer dev2 = new Developer("Jane", 101);
        Manager man1 = new Manager("Tom", 200);

        CompanyDirectory engineeringDirectory = new CompanyDirectory();
        engineeringDirectory.addEmployee(dev1);
        engineeringDirectory.addEmployee(dev2);

        CompanyDirectory companyDirectory = new CompanyDirectory();
        companyDirectory.addEmployee(man1);
        companyDirectory.addEmployee(engineeringDirectory);

        companyDirectory.showEmployeeDetails();
    }
}
```

### Decorator
* Expanded Definition: The Decorator pattern allows behavior to be added to an individual object, either statically or dynamically, without affecting the behavior of other objects from the same class.
* Detailed Analogy: Think of a plain coffee. You can add different ingredients like milk, sugar, or whipped cream to enhance the coffee. Each addition decorates the coffee with additional features.
Additional Code Example:
```java
interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Simple coffee";
    }

    public double getCost() {
        return 5;
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return decoratedCoffee.getDescription() + ", milk";
    }

    public double getCost() {
        return decoratedCoffee.getCost() + 1.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return decoratedCoffee.getDescription() + ", sugar";
    }

    public double getCost() {
        return decoratedCoffee.getCost() + 0.5;
    }
}

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
    }
}
```

### Facade
* Expanded Definition: The Facade pattern provides a unified interface to a set of interfaces in a subsystem, making it easier to use the subsystem. It defines a higher-level interface that makes the subsystem easier to use.
* Detailed Analogy: Think of a car's dashboard. You don't need to understand the complex internal mechanisms of the car to drive it. The dashboard provides a simple interface to control the car.
Additional Code Example:
```java
class Engine {
    public void start() {
        System.out.println("Engine started.");
    }

    public void stop() {
        System.out.println("Engine stopped.");
    }
}

class AirConditioner {
    public void turnOn() {
        System.out.println("Air conditioner turned on.");
    }

    public void turnOff() {
        System.out.println("Air conditioner turned off.");
    }
}

class CarFacade {
    private Engine engine;
    private AirConditioner airConditioner;

    public CarFacade() {
        this.engine = new Engine();
        this.airConditioner = new AirConditioner();
    }

    public void startCar() {
        engine.start();
        airConditioner.turnOn();
    }

    public void stopCar() {
        airConditioner.turnOff();
        engine.stop();
    }
}

public class FacadePatternDemo {
    public static void main(String[] args) {
        CarFacade car = new CarFacade();
        car.startCar(); // Output: Engine started.
                        //         Air conditioner turned on.

        car.stopCar();  // Output: Air conditioner turned off.
                        //         Engine stopped.
    }
}
```

### Flyweight
* Expanded Definition: The Flyweight pattern reduces the cost of creating and manipulating a large number of similar objects. It achieves this by sharing as much data as possible between similar objects; the shared objects are called flyweights.
* Detailed Analogy: Think of text editors where each character on the screen is an object. Instead of creating a new object for each character, the editor uses a flyweight object for each character type, and the position of each character is stored separately.
Additional Code Example:
```java
import java.util.HashMap;

interface Shape {
    void draw();
}

class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void draw() {
        System.out.println("Drawing Circle [color=" + color + ", x=" + x + ", y=" + y + ", radius=" + radius + "]");
    }
}

class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color: " + color);
        }
        return circle;
    }
}

public class FlyweightPatternDemo {
    private static final String colors[] = { "Red", "Green", "Blue", "White", "Black" };

    public static void main(String[] args) {
        for (int i = 0; i < 20; ++i) {
            Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }

    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private static int getRandomX() {
        return (int) (Math.random() * 100);
    }

    private static int getRandomY() {
        return (int) (Math.random() * 100);
    }
}
```

### Proxy
* Expanded Definition: The Proxy pattern provides a surrogate or placeholder for another object to control access to it. It is used to provide controlled access to a resource, creating a level of indirection between the client and the actual object.
* Detailed Analogy: Think of a bank account. When you want to check your balance or perform a transaction, you don’t interact directly with the bank's internal systems. Instead, you interact with a teller or an ATM (proxy) that controls access to your account. The proxy can perform additional tasks such as checking permissions, logging accesses, or even caching results.
Additional Code Example:
```java
interface Internet {
    void connectTo(String serverHost) throws Exception;
}

class RealInternet implements Internet {
    public void connectTo(String serverHost) throws Exception {
        System.out.println("Connecting to " + serverHost);
    }
}

class ProxyInternet implements Internet {
    private RealInternet realInternet;
    private static List<String> bannedSites;

    static {
        bannedSites = new ArrayList<>();
        bannedSites.add("blockedSite1.com");
        bannedSites.add("blockedSite2.com");
        bannedSites.add("blockedSite3.com");
    }

    public ProxyInternet() {
        this.realInternet = new RealInternet();
    }

    public void connectTo(String serverHost) throws Exception {
        if (bannedSites.contains(serverHost.toLowerCase())) {
            throw new Exception("Access Denied. Site " + serverHost + " is banned.");
        }
        realInternet.connectTo(serverHost);
    }
}

public class ProxyPatternDemo {
    public static void main(String[] args) {
        Internet internet = new ProxyInternet();

        try {
            internet.connectTo("google.com");
            internet.connectTo("blockedSite1.com"); // This will throw an exception
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```
In this example:

RealInternet: Represents the real object that performs the actual connection to a server.
ProxyInternet: Acts as a proxy to control access to the real internet connection. It checks if the requested server is in the list of banned sites before allowing the connection.


## Behavioral Patterns

### Chain of Responsibility
* Expanded Definition: The Chain of Responsibility pattern allows a request to be passed through a chain of handlers. Each handler decides either to process the request or to pass it to the next handler in the chain.
* Detailed Analogy: Think of an IT support ticket system where a ticket is passed through different levels of support (Level 1, Level 2, Level 3). Each level decides if it can resolve the issue; if not, it passes the ticket to the next level.
Additional Code Example:
```java
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler handler) {
        this.nextHandler = handler;
    }

    public abstract void handleRequest(int level);
}

class Level1SupportHandler extends SupportHandler {
    public void handleRequest(int level) {
        if (level <= 1) {
            System.out.println("Level 1 support is handling the issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(level);
        } else {
            System.out.println("No suitable handler found.");
        }
    }
}

class Level2SupportHandler extends SupportHandler {
    public void handleRequest(int level) {
        if (level == 2) {
            System.out.println("Level 2 support is handling the issue.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(level);
        } else {
            System.out.println("No suitable handler found.");
        }
    }
}

class Level3SupportHandler extends SupportHandler {
    public void handleRequest(int level) {
        if (level == 3) {
            System.out.println("Level 3 support is handling the issue.");
        } else {
            System.out.println("No suitable handler found.");
        }
    }
}

public class ChainOfResponsibilityPatternDemo {
    public static void main(String[] args) {
        SupportHandler level1Handler = new Level1SupportHandler();
        SupportHandler level2Handler = new Level2SupportHandler();
        SupportHandler level3Handler = new Level3SupportHandler();

        level1Handler.setNextHandler(level2Handler);
        level2Handler.setNextHandler(level3Handler);

        level1Handler.handleRequest(2); // Output: Level 2 support is handling the issue.
        level1Handler.handleRequest(3); // Output: Level 3 support is handling the issue.
        level1Handler.handleRequest(1); // Output: Level 1 support is handling the issue.
        level1Handler.handleRequest(4); // Output: No suitable handler found.
    }
}
```

### Command
* Expanded Definition: The Command pattern encapsulates a request as an object, thereby allowing for parameterization of clients with different requests, queuing of requests, and logging of requests.
* Detailed Analogy: Think of a restaurant ordering system. A customer (client) gives an order (command) to a waiter (invoker), who then processes the order and sends it to the chef (receiver) to prepare the food.
Additional Code Example:
```java
// Receiver
class Light {
    public void turnOn() {
        System.out.println("Light is on");
    }

    public void turnOff() {
        System.out.println("Light is off");
    }
}

// Command
interface Command {
    void execute();
}

class TurnOnLightCommand implements Command {
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOn();
    }
}

class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOff();
    }
}

// Invoker
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

public class CommandPatternDemo {
    public static void main(String[] args) {
        Light light = new Light();
        Command turnOnCommand = new TurnOnLightCommand(light);
        Command turnOffCommand = new TurnOffLightCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(turnOnCommand);
        remote.pressButton(); // Output: Light is on

        remote.setCommand(turnOffCommand);
        remote.pressButton(); // Output: Light is off
    }
}
```

### Interpreter
* Expanded Definition: The Interpreter pattern defines a grammatical representation for a language and provides an interpreter to interpret sentences in the language.
* Detailed Analogy: Think of a language translation system. The interpreter pattern can be used to interpret and execute different language expressions or commands.
Additional Code Example: This pattern often involves complex implementations for specific languages or grammars, and its usage is less common in everyday programming scenarios.
Iterator
* Expanded Definition: The Iterator pattern provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
* Detailed Analogy: Think of a playlist in a music player. The iterator pattern allows you to iterate over each song in the playlist without needing to know about the internal structure of the playlist.
Additional Code Example:
```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface SongIterator {
    Iterator<String> createIterator();
}

class Playlist implements SongIterator {
    private List<String> songs;

    public Playlist() {
        this.songs = new ArrayList<>();
    }

    public void addSong(String song) {
        songs.add(song);
    }

    public Iterator<String> createIterator() {
        return songs.iterator();
    }
}

public class IteratorPatternDemo {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong("Song 1");
        playlist.addSong("Song 2");
        playlist.addSong("Song 3");

        Iterator<String> iterator = playlist.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

### Mediator
* Expanded Definition: The Mediator pattern defines an object that encapsulates how a set of objects interact. It promotes loose coupling by keeping objects from referring to each other explicitly and allows them to communicate through the mediator object.
* Detailed Analogy: Think of an air traffic control system. The mediator coordinates communication between aircraft, ensuring they don't collide and manage landing and takeoff schedules.
Additional Code Example:
```java
import java.util.ArrayList;
import java.util.List;

interface ATCMediator {
    void sendMessage(String message, Aircraft aircraft);
}

class ATC implements ATCMediator {
    private List<Aircraft> aircraftList;

    public ATC() {
        this.aircraftList = new ArrayList<>();
    }

    public void addAircraft(Aircraft aircraft) {
        aircraftList.add(aircraft);
    }

    public void sendMessage(String message, Aircraft aircraft) {
        for (Aircraft a : aircraftList) {
            if (a != aircraft) {
                a.receive(message);
            }
        }
    }
}

abstract class Aircraft {
    protected ATCMediator mediator;

    public Aircraft(ATCMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void send(String message);

    public abstract void receive(String message);
}

class Airbus extends Aircraft {
    public Airbus(ATCMediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.sendMessage(message, this);
    }

    public void receive(String message) {
        System.out.println("Airbus received: " + message);
    }
}

class Boeing extends Aircraft {
    public Boeing(ATCMediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.sendMessage(message, this);
    }

    public void receive(String message) {
        System.out.println("Boeing received: " + message);
    }
}

public class MediatorPatternDemo {
    public static void main(String[] args) {
        ATCMediator mediator = new ATC();
        Aircraft airbus = new Airbus(mediator);
        Aircraft boeing = new Boeing(mediator);

        mediator.addAircraft(airbus);
        mediator.addAircraft(boeing);

        airbus.send("Hello from Airbus!");
        boeing.send("Hello from Boeing!");
    }
}
```

### Observer
* Expanded Definition: The Observer pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
* Detailed Analogy: Think of subscribing to a newsletter. When new content is published, subscribers are notified and receive updates automatically.
Additional Code Example:
```java
import java.util.ArrayList;
import java.util.List;

// Subject
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class WeatherStation implements Subject {
    private int temperature;
    private List<Observer> observers;

    public WeatherStation() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}

// Observer
interface Observer {
    void update(int temperature);
}

// Concrete Observer
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void update(int temperature) {
        System.out.println("Notification to " + name + ": Temperature is now " + temperature + " degrees.");
    }
}

public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        User user1 = new User("User1");
        User user2 = new User("User2");

        weatherStation.addObserver(user1);
        weatherStation.addObserver(user2);

        weatherStation.setTemperature(25); // Output: Notification to User1: Temperature is now 25 degrees.
                                          //         Notification to User2: Temperature is now 25 degrees.
    }
}
```
### State
* Expanded Definition: The State pattern allows an object to alter its behavior when its internal state changes. The object will appear to change its class.
* Detailed Analogy: Think of a traffic light. It changes behavior (color signals) based on its internal state (green, yellow, red) and external events (pedestrian crossing, vehicle detection).
Additional Code Example:
```java
// Context```
class TrafficLight {
    private TrafficLightState state;

    public TrafficLight() {
        setState(new RedLightState());
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void change() {
        state.change(this);
    }
}

// State
interface TrafficLightState {
    void change(TrafficLight light);
}

// Concrete State
class RedLightState implements TrafficLightState {
    public void change(TrafficLight light) {
        System.out.println("Red Light - Stop");
        light.setState(new GreenLightState());
    }
}

class GreenLightState implements TrafficLightState {
    public void change(TrafficLight light) {
        System.out.println("Green Light - Go");
        light.setState(new YellowLightState());
    }
}

class YellowLightState implements TrafficLightState {
    public void change(TrafficLight light) {
        System.out.println("Yellow Light - Prepare to stop");
        light.setState(new RedLightState());
    }
}

public class StatePatternDemo {
    public static void main(String[] args) {
        TrafficLight light = new TrafficLight();

        light.change(); // Output: Red Light - Stop
        light.change(); // Output: Green Light - Go
        light.change(); // Output: Yellow Light - Prepare to stop
        light.change(); // Output: Red Light - Stop
    }
}
```

### Strategy
* Expanded Definition: The Strategy pattern defines a family of algorithms, encapsulates each algorithm, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.
* Detailed Analogy: Think of sorting algorithms. You can use different strategies (algorithms) like bubble sort, quicksort, or merge sort depending on the context (size of data, performance requirements).
Additional Code Example:
```java
// Strategy
interface SortStrategy {
    void sort(int[] data);
}

// Concrete Strategies
class BubbleSortStrategy implements SortStrategy {
    public void sort(int[] data) {
        System.out.println("Sorting using Bubble Sort");
        // Implementation of bubble sort algorithm
    }
}

class QuickSortStrategy implements SortStrategy {
    public void sort(int[] data) {
        System.out.println("Sorting using Quick Sort");
        // Implementation of quick sort algorithm
    }
}

// Context
class Sorter {
    private SortStrategy strategy;

    public Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void performSort(int[] data) {
        strategy.sort(data);
    }
}

public class StrategyPatternDemo {
    public static void main(String[] args) {
        int[] data = { 7, 2, 5, 1, 9 };

        Sorter sorter = new Sorter(new BubbleSortStrategy());
        sorter.performSort(data); // Output: Sorting using Bubble Sort

        sorter.setStrategy(new QuickSortStrategy());
        sorter.performSort(data); // Output: Sorting using Quick Sort
    }
}
```