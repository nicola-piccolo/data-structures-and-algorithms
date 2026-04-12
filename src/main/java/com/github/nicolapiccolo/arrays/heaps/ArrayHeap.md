# Array Heap

## Min-Heap backed by an array

A min-heap is a complete binary tree where each node's value is smaller than or equal to its children's values. The root always holds the minimum element.

A convenient way to store a heap is by using an array, where for a node at index _i_:
- its left child is at index _2i + 1_
- its right child is at index _2i + 2_
- its parent is at index _⌊(i - 1) / 2⌋_

Let us look at the following animation:

![How a min-heap works](https://upload.wikimedia.org/wikipedia/commons/6/69/Min-heap.png)

_(Source: Wikipedia)_

### Insert

A new element is added as the last leaf of the tree. Then, it is compared with its parent and swapped upward (bottom-up restore) until the heap property is satisfied. This operation has a time complexity of O(log N).

### Remove root

The root is removed and replaced by the last leaf. Then, the new root is compared with its children and swapped downward (top-down restore) with the smallest child until the heap property is satisfied. This operation has a time complexity of O(log N).

Here follows the Java implementation of an array-backed min-heap: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/arrays/heaps/ArrayHeap.java).
