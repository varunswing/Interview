# Strings

```java
import java.io.*;
import java.util.*;

class Strings {
	public static void main(String[] args) {
		String s = "GeeksforGeeks";
		// String s= new String ("GeeksforGeeks");
	}
}
```
## Returns the number of characters in the String.
```java
System.out.println("String length = " + s.length());// 13
```

## Returns the character at ith index.
```java
System.out.println("Character at 3rd position = "
		+ s.charAt(3));// k

```
## Return the substring from the ith index character to end of string
```java
System.out.println("Substring " + s.substring(3));// ksforGeeks

```
## Returns the substring from i to j-1 index.
```java
System.out.println("Substring = " + s.substring(2, 5));// eks
```

## Concatenates string2 to the end of string1.
```java
String s1 = "Geeks";
String s2 = "forGeeks";
System.out.println("Concatenated string = " +
		s1.concat(s2));// GeeksforGeeks
```
## Returns the index within the string of the first occurrence of the specified string.
```java
String s4 = "Learn Share Learn";
System.out.println("Index of Share " +
		s4.indexOf("Share"));// 6
```
## Returns the index within the string of the first occurrence of the specified string, starting at the specified index.
```java
System.out.println("Index of a = " +
		s4.indexOf('a', 3));// 8
```
## Checking equality of Strings
```java
Boolean out = "Geeks".equals("geeks");
System.out.println("Checking Equality " + out);// false
out = "Geeks".equals("Geeks");
System.out.println("Checking Equality " + out);// true

out = "Geeks".equalsIgnoreCase("gEeks");
System.out.println("Checking Equality " + out);// true
```
## If ASCII difference is zero then the two strings are similar
```java
int out1 = s1.compareTo(s2);
System.out.println("the difference between ASCII value is=" + out1);// -31
```
## Converting cases
```java
String word1 = "GeeKyMe";
System.out.println("Changing to lower Case " +
		word1.toLowerCase());// geekyme
```
## Converting cases
```java
String word2 = "GeekyME";
System.out.println("Changing to UPPER Case " +
		word2.toUpperCase());// GEEKYME
```
## Trimming the word
```java
String word4 = " Learn Share Learn ";
System.out.println("Trim the word " + word4.trim());
```
## Replacing characters
```java
String str1 = "feeksforfeeks";
String str2 = "feeksforfeeks".replace('f', 'g');
System.out.println("Replaced f with g -> " + str2);// geeksgorgeeks
```
