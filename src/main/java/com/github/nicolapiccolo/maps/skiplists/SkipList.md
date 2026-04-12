# Skip List

## Skip list data structure

A skip list is a probabilistic data structure that provides O(log N) average-case search, insertion, and deletion. It achieves this by maintaining multiple levels of sorted linked lists, where higher levels act as "express lanes" that skip over large portions of the data.

## How it works

The structure consists of multiple levels:

- **Level 0** contains all elements in sorted order
- **Level 1** contains roughly every 2nd element
- **Level 2** contains roughly every 4th element
- And so on...

Each node has a randomly determined height. When inserting a new node, its height is decided by repeated coin flips — keep going up a level with probability 1/2. This gives an expected O(log N) levels.

**Search** starts at the highest level and moves right until the next node's key is too large, then drops down a level. This pattern efficiently narrows down the search space.

## Operations

### Core operations

- **put(key, value)** — searches for the insertion point, creates a node with a random height, and splices it into each level. O(log N) average.
- **get(key)** — searches for the key using the multi-level traversal. O(log N) average.
- **delete(key)** — searches for the node and removes it from each level. O(log N) average.

### Sorted access

- **firstKey()** — returns the first node at level 0. O(1).
- **lastKey()** — traverses to the rightmost node using the express lanes. O(log N).
- **floorKey(key)** — largest key less than or equal to the given key. O(log N).
- **ceilingKey(key)** — smallest key greater than or equal to the given key. O(log N).

### Iteration

- **iterator()** — traverses level 0 in sorted order. O(N).

## Implementation notes

The implementation is split into focused classes:

- **SkipListNode** — holds key, value, and an array of forward pointers (one per level)
- **SkipListRandomLevelGenerator** — encapsulates the coin-flip logic for determining a new node's height
- **SkipListSearchOperator** — traverses the skip list and collects the predecessor at each level (the "update" array), which is reused by both insert and delete
- **SkipList** — main class that orchestrates all operations

Here follows the Java implementation of a skip list: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/maps/skiplists/SkipList.java).
