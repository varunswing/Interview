# Java Stream 

* https://satishkathiriya99.medium.com/java-stream-api-cheatsheet-4e8c10d799dc

* https://johndobie.com/blog/java-streams-cheat-sheet-with-examples/

* https://cheatography.com/carlmig/cheat-sheets/java-8-streams/

Certainly! Here's a cheat sheet for common Stream operations in Java:
1. ### Creating Streams:

    * Stream.of(T... values): Creates a stream from specified values.
    * Collection.stream(): Creates a stream from a collection.
    * Arrays.stream(T[] array): Creates a stream from an array.
    * Stream.iterate(T seed, UnaryOperator<T> f): Generates an infinite stream by applying a function to a seed value.
2. ### Intermediate Operations:

    * filter(Predicate<T> predicate): Filters elements based on a predicate.
    * map(Function<T, R> mapper): Transforms elements using a mapper function.
    * flatMap(Function<T, Stream<R>> mapper): Transforms elements into streams and flattens the results.
    * distinct(): Removes duplicates from the stream.
    * sorted(): Sorts elements.
    * limit(long maxSize): Truncates the stream to a maximum size.
    * skip(long n): Skips the first n elements of the stream.
3. ### Terminal Operations:

    * forEach(Consumer<T> action): Performs an action for each element of the stream.
    * collect(Collector<T, A, R> collector): Collects elements into a collection.
    * reduce(BinaryOperator<T> accumulator): Reduces the stream to a single value using an accumulator function.
    * min(Comparator<T> comparator): Finds the minimum element based on a comparator.
    * max(Comparator<T> comparator): Finds the maximum element based on a comparator.
    * count(): Counts the number of elements in the stream.
    * anyMatch(Predicate<T> predicate): Checks if any element matches a predicate.
    * allMatch(Predicate<T> predicate): Checks if all elements match a predicate.
    * noneMatch(Predicate<T> predicate): Checks if no elements match a predicate.
    * findFirst(): Finds the first element in the stream.
    * findAny(): Finds any element in the stream.
4. ### Short-Circuiting Operations:

    * Short-circuiting operations terminate the stream early if a certain condition is met, improving performance for infinite streams.
    * Examples: anyMatch(), allMatch(), noneMatch(), findFirst(), findAny(), limit(), skip().


```java
List<Integer> even = numbers.stream()
                                .map(s -> Integer.valueOf(s))
                                .filter(number -> number % 2 == 0)
                                .collect(Collectors.toList());
```
```java
List<String> surname =
           library.stream()
           .map(book -> book.getAuthor())
           .filter(author -> author.getAge() >= 50)
           .map(Author::getSurname)
           .map(String::toUpperCase)
           .distinct()
           .limit(15)
           .collect(toList()));
```
```java
int sum = library.stream()
          .map(Book::getAuthor)
          .filter(author -> author.getGender() == Gender.FEMALE)
          .map(Author::getAge)
          .filter(age -> age < 25)
          .reduce(0, Integer::sum)
```
```java
Employee employee = 
      Stream.of(empIds)
      .map(employeeRepository::findById)
      .filter(e -> e != null)
      .filter(e -> e.getSalary() > 100000)
      .findFirst()
      .orElse(null);
```
```java
Employee[] employees = empList.stream().toArray(Employee[]::new);
```
```java
List<Integer> evenInts = IntStream.rangeClosed(1, 10)
  .filter(i -> i % 2 == 0)
  .boxed()
  .collect(Collectors.toList());
```
```java
int sum = Arrays.asList(33,45)
  .stream()
  .mapToInt(i -> i)
  .sum();
```

```java
import java.util.*;
import java.util.stream.*;

class StreamApi {
	public static void main(String args[])
	{
		List<Integer> number = Arrays.asList(2, 3, 4, 5); 

        number.stream().forEach(y -> System.out.println(y));// 2, 3, 4, 5

		List<Integer> square 
		= number.stream()
			.map(x -> x * x)
			.collect(Collectors.toList());

		List<String> names = Arrays.asList(
			"Reflection", "Collection", "Stream");

		List<String> result
		= names.stream()
			.filter(s -> s.startsWith("S"))
			.collect(Collectors.toList());
	
		System.out.println(result); // [Stream]

		List<String> show 
		= names.stream()
			.sorted()
			.collect(Collectors.toList());
	
		System.out.println(show); // [Collection, Reflection, Stream]

				List<Integer> numbers
			= Arrays.asList(2, 3, 4, 5, 2);

		Set<Integer> squareSet
		= numbers.stream()
			.map(x -> x * x)
			.collect(Collectors.toSet());
	
		System.out.println(squareSet);//[16, 4, 9, 25]

		number.stream()
			.map(x -> x * x)
			.forEach(y -> System.out.println(y));//4, 9, 16, 25


		int even 
		= number.stream()
			.filter(x -> x % 2 == 0)
			.reduce(0, (ans, i) -> ans + i);

		System.out.println(even);//6
	}
}
```