# Sorted Hash Table

## Sorted map data structure

A sorted hash table is a key-value store that maintains its keys in **sorted order**, enabling both efficient lookup and ordered access.

## Implementation

This implementation combines two data structures:

- **AVL binary search tree** — provides O(log N) `put`, `get`, and `delete` operations while maintaining key ordering through self-balancing.
- **Sorted list of keys** — maintained alongside the tree using binary search for O(log N) insertion. This enables efficient `firstKey`, `lastKey`, `floorKey`, and `ceilingKey` operations.

## Operations

### Core operations

- **put(key, value)** — inserts or updates a key-value pair. O(log N).
- **get(key)** — retrieves the value for a key. O(log N).
- **delete(key)** — removes a key-value pair. O(log N).

### Sorted access

- **firstKey()** — returns the smallest key. O(1).
- **lastKey()** — returns the largest key. O(1).
- **floorKey(key)** — returns the largest key less than or equal to the given key. O(log N).
- **ceilingKey(key)** — returns the smallest key greater than or equal to the given key. O(log N).

### Iteration

- **iterator()** — iterates over keys in ascending order. O(N).

Here follows the Java implementation of a sorted hash table: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/maps/sortedhashtables/SortedHashTable.java).
