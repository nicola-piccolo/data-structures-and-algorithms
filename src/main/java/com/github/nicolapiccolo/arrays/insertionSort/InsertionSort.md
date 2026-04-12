# Insertion sort

## Quick recap about arrays

An array is a data structure where its entries can be directly accessed using integer indexes.

A huge advantage for an array is that the access time to any of its items is always constant, i.e. O(1). Please refer to [Wikipedia](https://simple.wikipedia.org/wiki/Big_O_notation) for an introduction about the big O notation.

On the other side, the main drawbacks of using an array are:

- a fixed capacity and
- slow performances when inserting or deleting an item, since it might require shifting a lot of other items.

## Insertion sort algorithm

Let us suppose that we have an array of integer values, and that we want to run an ascending sort on them.

The key idea here is to imagine our array split in two parts: the left side is already sorted, while the right side is unsorted.

Next, we will pick an item from the right side and find its correct position on the left side.

Lastly, in the left part we will “make room” to our new item by shifting values on the right.

Watch this animation:

![How insertion sort works](https://upload.wikimedia.org/wikipedia/commons/9/9c/Insertion-sort-example.gif)

_(Source: Wikipedia)_

As we can see, this sorting algorithm works “_in place_,” because it does not require to create any copy of the original array.

For an array of N elements, the time required by insertion sort is O(N<sup>2</sup>) because, in the worst-case scenario with an already descending ordered array, for each new element moved on the left side of the array, we must shift the remaining items on the right.

Now please take a look to its [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/arrays/insertionSort/InsertionSorter.java).
