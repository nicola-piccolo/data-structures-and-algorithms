# Binary Tree Traversals

## Binary trees

A binary tree is a hierarchical data structure where each node has at most two children: a left child and a right child. The topmost node is called the **root**.

## Traversal strategies

Traversing a tree means visiting every node exactly once. There are four common strategies:

### Preorder (root, left, right)

The current node is visited first, then the left subtree, then the right subtree. This is useful for creating a copy of the tree.

### Inorder (left, root, right)

The left subtree is visited first, then the current node, then the right subtree. For a binary search tree, this produces nodes in sorted order.

### Postorder (left, right, root)

The left subtree is visited first, then the right subtree, then the current node. This is useful for deleting a tree or evaluating expressions.

### Breadth-first (level order)

Nodes are visited level by level, from left to right. This uses a queue to process nodes in the order they are discovered.

All four traversals visit every node exactly once, resulting in O(N) time complexity.

Here follow the Java implementations:
- [Preorder iterator](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/trees/BinaryTreePreorderIterator.java)
- [Inorder iterator](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/trees/BinaryTreeInorderIterator.java)
- [Postorder iterator](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/trees/BinaryTreePostorderIterator.java)
- [Breadth-first iterator](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/trees/BinaryTreeBreadthFirstIterator.java)
