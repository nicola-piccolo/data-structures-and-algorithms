# Heap Sort

## Heap sort algorithm

Heap sort leverages a min-heap data structure to sort an array of comparable values in ascending order.

The algorithm works in two phases:

1. **Build the heap** — all elements from the input array are inserted into a min-heap, one by one. Each insertion has a time complexity of O(log N).

2. **Extract sorted elements** — the root (minimum element) is repeatedly removed from the heap and placed into the result array. Each removal has a time complexity of O(log N).

Since both phases iterate over all N elements and each operation costs O(log N), heap sort has an overall time complexity of O(N log N).

Unlike selection sort or insertion sort, this implementation is _not in place_ because it requires an auxiliary heap structure and a separate result array.

Here follows the Java implementation of heap sort: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/arrays/heapSort/HeapSorter.java).
