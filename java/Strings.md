# Strings

Java provides a range of commonly used `String` methods for various operations. Here are some popular ones:

1. **`.length()`**  
   - Returns the length of the string.
   - Example: `"hello".length()` → `5`

2. **`.charAt(int index)`**  
   - Returns the character at the specified index.
   - Example: `"hello".charAt(1)` → `'e'`

3. **`.substring(int beginIndex, int endIndex)`**  
   - Returns a substring from `beginIndex` to `endIndex - 1`.
   - Example: `"hello".substring(1, 4)` → `"ell"`

4. **`.indexOf(String str)`**  
   - Returns the index of the first occurrence of the specified substring.
   - Example: `"hello".indexOf("l")` → `2`

5. **`.lastIndexOf(String str)`**  
   - Returns the index of the last occurrence of the specified substring.
   - Example: `"hello".lastIndexOf("l")` → `3`

6. **`.toUpperCase()`** and **`.toLowerCase()`**  
   - Converts all characters to upper or lower case.
   - Example: `"hello".toUpperCase()` → `"HELLO"`

7. **`.trim()`**  
   - Removes leading and trailing spaces.
   - Example: `" hello ".trim()` → `"hello"`

8. **`.replace(char oldChar, char newChar)`**  
   - Replaces occurrences of `oldChar` with `newChar`.
   - Example: `"hello".replace('l', 'y')` → `"heyyo"`

9. **`.split(String regex)`**  
   - Splits the string based on the specified regex and returns an array of substrings.
   - Example: `"hello world".split(" ")` → `["hello", "world"]`

10. **`.contains(CharSequence s)`**  
    - Checks if the string contains the specified sequence.
    - Example: `"hello".contains("ell")` → `true`

11. **`.equals(Object anotherObject)`** and **`.equalsIgnoreCase(String anotherString)`**  
    - Checks if two strings are equal, case-sensitive and case-insensitive.
    - Example: `"hello".equals("hello")` → `true`
    - Example: `"hello".equalsIgnoreCase("HELLO")` → `true`

12. **`.startsWith(String prefix)`** and **`.endsWith(String suffix)`**  
    - Checks if the string starts or ends with the specified prefix or suffix.
    - Example: `"hello".startsWith("he")` → `true`
    - Example: `"hello".endsWith("lo")` → `true`

13. **`.compareTo(String anotherString)`** and **`.compareToIgnoreCase(String anotherString)`**  
    - Compares two strings lexicographically.
    - Example: `"apple".compareTo("banana")` → negative value (since `"apple"` comes before `"banana"`)

14. **`.isEmpty()`** and **`.isBlank()`** (Java 11+)  
    - Checks if the string is empty or contains only whitespace.
    - Example: `"".isEmpty()` → `true`
    - Example: `"   ".isBlank()` → `true`

15. **`.repeat(int count)`** (Java 11+)  
    - Repeats the string `count` times.
    - Example: `"hello ".repeat(3)` → `"hello hello hello "`

16. **`.matches(String regex)`**  
    - Checks if the string matches a regular expression.
    - Example: `"hello".matches(".*llo")` → `true`

17. **`.concat(String world)`**  
    - Concatenates string2 to the end of string1.
    - Example: `"hello".concat(" world")` → `hello world`
 
These methods can be combined to perform complex string manipulations, and they are essential for tasks involving text processing. Let me know if you’d like details on any specific function or examples for more advanced operations!