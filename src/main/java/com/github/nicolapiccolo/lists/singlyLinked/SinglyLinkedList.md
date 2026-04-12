# Singly linked list

## Quick recap about lists

If we remember the definition of arrays, we need a contiguous memory of area in which we are storing a collection of entries by using integer indexes.

A list is also a collection of entries, but it does not require a whole block of memory area, because a list is a collection of nodes scattered in memory. 

Each node consists of two parts:
-	**data payload**. It can be a number, a string, or any other data structure we want to store inside the node.
-	**reference to the next node**. Thanks to this reference, we can sequentially attach one node to another and create a "chain" of nodes, *i.e. a list of nodes*.

If we think again to the list of nodes as a chain of rings, there will be a **head** node that marks the list's first node and a **tail** node that represents the list's last node.

The number of nodes in a list is also known as **size** of a list.

![Singly linked list](./Singly linked list.png)

## Operations on a singly linked list

When we are working with a singly linked list, the main constraint is that we need to *traverse* the list by **hopping** nodes: starting from the head, we read the reference to the next node. Then, inside the next node, we read the reference to the next-next node and so on until we reach the tail node.

### Insertion

The two most basic ways of inserting a new node are either from the head (*add as first item*) or from the tail (*add as last item*). Adding as first or last is an O(1) operation because we hold direct references to head and tail. Inserting at an arbitrary position requires traversing the list up to that position, resulting in O(N) time complexity.

### Removal

Similarly, removing the head is O(1). Removing the tail or a node at an arbitrary position requires traversal to find the preceding node, resulting in O(N) time complexity.

### Inversion

Inverting the list reverses the direction of all node references, swapping head and tail. This requires a single traversal of the entire list, resulting in O(N) time complexity.

## Implementation notes

The implementation uses a **Strategy pattern** to separate each operation (add, remove, invert) into its own class. This keeps the main list class focused on managing head, tail, and size, while each operation class encapsulates its own traversal and mutation logic. A shared abstract base class eliminates duplication of common traversal code.

The list is **generic**, accepting any type `T` as the data payload.

Here follows the Java implementation of a singly linked list: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/lists/singlyLinked/SinglyLinkedList.java).
