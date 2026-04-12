# Tree-backed Min-Heap

## Tree-based heap

While the [array-backed min-heap](/src/main/java/com/github/nicolapiccolo/arrays/heaps/ArrayHeap.md) stores nodes in a flat array, this implementation uses an actual binary tree structure where each node holds references to its parent and children.

The min-heap property is the same: each node's value is smaller than or equal to its children's values, so the root always holds the minimum element.

## Operations

### Insert

A new node is added as the next available leaf to maintain the complete tree shape. Then, it is compared with its parent and swapped upward (bottom-up restore) until the heap property is satisfied. Time complexity is O(log N).

### Remove root

The root value is saved, then the last leaf's value is moved to the root. The last leaf is removed, and the new root is compared with its children and swapped downward (top-down restore) with the smallest child until the heap property is satisfied. Time complexity is O(log N).

## Implementation notes

The tree-based approach requires tracking the **next available parent** (for insertion) and the **latest leaf** (for removal). Navigating between these positions involves traversing the tree structure, which adds complexity compared to the array-backed version but avoids the fixed-capacity limitation.

Here follows the Java implementation: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/trees/heaps/TreeHeap.java).
