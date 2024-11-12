Method Summary
Modifier and Type	Method and Description

boolean	empty()
Tests if this stack is empty.

E	peek()
Looks at the object at the top of this stack without removing it from the stack.

E	pop()
Removes the object at the top of this stack and returns that object as the value of this function.

E	push(E item)
Pushes an item onto the top of this stack.

int	search(Object o)
Returns the 1-based position where an object is on this stack.

Methods inherited from class java.util.Vector
add, add, addAll, addAll, addElement, capacity, clear, clone, contains, containsAll, copyInto, elementAt, elements, 
ensureCapacity, equals, firstElement, forEach, get, hashCode, indexOf, indexOf, insertElementAt, isEmpty, iterator, 
lastElement, lastIndexOf, lastIndexOf, listIterator, listIterator, remove, remove, removeAll, removeAllElements, 
removeElement, removeElementAt, removeIf, removeRange, replaceAll, retainAll, set, setElementAt, setSize, size, sort, 
spliterator, subList, toArray, toArray, toString, trimToSize

Methods inherited from class java.lang.Object
finalize, getClass, notify, notifyAll, wait, wait, wait

Methods inherited from interface java.util.Collection
parallelStream, stream

Iterable <-(extends) Collection <-(extends) List <-(implements) vector <-(extends) stack