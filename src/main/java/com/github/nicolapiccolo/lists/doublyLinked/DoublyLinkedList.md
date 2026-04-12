# Doubly linked list

## Doubly linked list

A doubly linked list extends the [singly linked list](/src/main/java/com/github/nicolapiccolo/lists/singlyLinked/SinglyLinkedList.md) by adding a **previous node** reference to each node, in addition to the existing next node reference. This creates a bidirectional chain that can be traversed in both directions.

Each node consists of three parts:
- **data payload**
- **reference to the next node**
- **reference to the previous node**

## Operations

### Insertion

Adding as first or last is O(1) thanks to direct head and tail references. Inserting at an arbitrary position requires traversal, resulting in O(N) time complexity.

### Removal

Unlike the singly linked list, removing the **last** element is O(1) because the tail's previous reference gives direct access to the new tail. Removing from the head is also O(1). Removing from an arbitrary position is O(N).

### Inversion

Inverting the list swaps the next and previous references for every node, then swaps head and tail. This requires a single traversal, resulting in O(N) time complexity.

### Reverse iteration

A key advantage over singly linked lists: the list can be iterated backward from tail to head in O(N) time, without needing to invert it first.

## Implementation notes

The implementation follows the same **Strategy pattern** as the singly linked list, with each operation (add, remove, invert) in its own class. A shared abstract base class provides common traversal logic.

Here follows the Java implementation of a doubly linked list: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/lists/doublyLinked/DoublyLinkedList.java).
