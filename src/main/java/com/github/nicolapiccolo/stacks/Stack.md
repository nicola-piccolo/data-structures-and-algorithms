# Stack

## Stack data structure

A stack is a **LIFO** (Last In, First Out) data structure: the last element added is the first one to be removed. Think of a stack of plates — you always add and remove from the top.

## Operations

- **push(value)** — adds an element on top of the stack. Amortized O(1).
- **pop()** — removes and returns the top element. O(1).
- **peek()** — returns the top element without removing it. O(1).

## Dynamic resizing

This implementation is backed by an array with an initial capacity. When the array is full, a new array with double the capacity is created and the existing elements are copied over.

Most push operations are O(1). Occasionally, when a resize is needed, the push is O(N) due to the copy. However, since the array doubles each time, the **amortized** cost of push remains O(1).

Here follows the Java implementation of a stack: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/stacks/Stack.java).
