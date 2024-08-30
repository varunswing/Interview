Modifier and Type	Method and Description

boolean	add(E e)
Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions, returning true upon success and throwing an IllegalStateException if no space is currently available.

E	element()
Retrieves, but does not remove, the head of this queue.

boolean	offer(E e)
Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions.

E	peek()
Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.

E	poll()
Retrieves and removes the head of this queue, or returns null if this queue is empty.

E	remove()
Retrieves and removes the head of this queue.

All Superinterfaces:
Collection<E>, Iterable<E>
All Known Subinterfaces:
BlockingDeque<E>, BlockingQueue<E>, Deque<E>, TransferQueue<E>
All Known Implementing Classes:
AbstractQueue, ArrayBlockingQueue, ArrayDeque, ConcurrentLinkedDeque, ConcurrentLinkedQueue, 
DelayQueue, LinkedBlockingDeque, LinkedBlockingQueue, LinkedList, LinkedTransferQueue, 
PriorityBlockingQueue, PriorityQueue, SynchronousQueue

Simple Queue
A simple queue is the most basic queue. In this queue, the enqueue operation takes place at the rear, while the dequeue operation takes place at the front:

Circular Queue
A circular queue permits better memory utilization than a simple queue when the queue has a fixed size.

Priority Queue
A priority queue is a special kind of queue in which each item has a predefined priority of service. 
That is to say that an item with a high priority will be dequeued before an item with a low priority.

Double-Ended Queue (Deque)
A deque is also a special type of queue. In this queue, the enqueue and dequeue operations take place at both front and rear. 