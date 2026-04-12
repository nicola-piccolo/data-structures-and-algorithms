# Binary Search Tree

## Binary search tree

A binary search tree (BST) is a binary tree where each node's key is greater than all keys in its left subtree and smaller than all keys in its right subtree. This property enables efficient lookup, insertion, and deletion.

## Operations

- **get(key)** — starting from the root, compare the key with the current node: go left if smaller, right if larger. Time complexity is O(h), where h is the tree height.

- **put(key, value)** — find the correct position using the same search logic, then insert a new node as a leaf. If the key already exists, the value is replaced. Time complexity is O(h).

- **delete(key)** — the node is marked as deleted (lazy deletion). When the number of deleted nodes exceeds a configurable threshold, the tree is rebuilt into a balanced structure. This amortizes the cost of physical removal.

## AVL self-balancing

An unbalanced BST can degenerate into a linked list with O(N) operations. An **AVL tree** prevents this by maintaining the invariant that, for every node, the heights of its left and right subtrees differ by at most 1.

After each insertion, the tree checks for imbalanced nodes by walking from the inserted node up to the root. If an imbalance is found, it is corrected using **rotations**:

- **Single rotation** — used when the imbalance is on the outer side (left-left or right-right).
- **Double rotation** — used when the imbalance is on the inner side (left-right or right-left). This is a combination of two single rotations.

After rebalancing, node heights are updated accordingly.

With AVL balancing, the tree height is guaranteed to be O(log N), ensuring O(log N) time complexity for all operations.

## Implementation notes

The implementation uses a **post-processor pattern**: the core BST delegates rebalancing and height updates to pluggable post-processors. This keeps the core tree logic clean and allows the AVL behavior to be composed via a factory.

Here follow the Java implementations:
- [Binary search tree interface](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/trees/binarysearch/BinarySearchTree.java)
- [Core tree implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/trees/binarysearch/BinarySearchCoreTree.java)
- [AVL tree factory](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/trees/binarysearch/avl/BinarySearchAVLTreeFactory.java)
