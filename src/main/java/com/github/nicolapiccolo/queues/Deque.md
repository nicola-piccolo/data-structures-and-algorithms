# Deque

## Deque data structure

A deque (double-ended queue) supports insertion and removal at **both** ends. It generalizes both the [stack](/src/main/java/com/github/nicolapiccolo/stacks/Stack.md) (LIFO) and the [queue](/src/main/java/com/github/nicolapiccolo/queues/Queue.md) (FIFO) data structures.

## Circular array implementation

Like the queue, this implementation uses a **circular array** with two indexes:

- **head** — points to the front element
- **tail** — points to the next available slot at the back

The key difference from the queue is that the head index can also move **backward** using `(index - 1 + length) % length`, enabling O(1) insertion at the front.

## Operations

- **addFirst(value)** — moves head backward, then stores the element. Amortized O(1).
- **addLast(value)** — stores the element at tail, then moves tail forward. Amortized O(1).
- **removeFirst()** — returns the head element, then moves head forward. O(1).
- **removeLast()** — moves tail backward, then returns the element. O(1).
- **peekFirst()** — returns the head element without removing it. O(1).
- **peekLast()** — returns the element before tail without removing it. O(1).

## Dynamic resizing

When the array is full, a new array with double the capacity is created. The elements are copied in order starting from the current head, and both indexes are reset. The **amortized** cost of insertion remains O(1).

Here follows the Java implementation of a deque: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/queues/Deque.java).
