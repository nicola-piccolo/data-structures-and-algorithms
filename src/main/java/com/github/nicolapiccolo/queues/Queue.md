# Queue

## Queue data structure

A queue is a **FIFO** (First In, First Out) data structure: the first element added is the first one to be removed. Think of a queue of people waiting in line — the person who arrives first is served first.

## Circular array implementation

This implementation uses a **circular array** with two indexes:

- **head** — points to the front of the queue (next element to dequeue)
- **tail** — points to the next available slot (where the next element will be enqueued)

Both indexes advance forward and wrap around to the beginning of the array using modulo arithmetic: `nextIndex = (currentIndex + 1) % arrayLength`. This avoids wasting space at the front of the array after dequeue operations.

## Operations

- **enqueue(value)** — adds an element at the tail. Amortized O(1).
- **dequeue()** — removes and returns the element at the head. O(1).
- **peek()** — returns the head element without removing it. O(1).

## Dynamic resizing

When the array is full, a new array with double the capacity is created. The elements are copied in FIFO order starting from the current head, and the head and tail indexes are reset. This ensures correct ordering even when the circular buffer has wrapped around.

Most enqueue operations are O(1). The occasional resize is O(N), but since the capacity doubles each time, the **amortized** cost remains O(1).

Here follows the Java implementation of a queue: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/queues/Queue.java).
