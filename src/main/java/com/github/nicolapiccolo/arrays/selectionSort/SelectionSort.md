# Selection sort

## Selection sort algorithm

In this case too, we want to sort an array of integer values in ascending order.

Similarly to the approach taken for the insertion sort, the key idea is to imagine our array split in two parts: the left side is already sorted, and the right side is unsorted.

The algorithm will next search for the smallest item on right side of our array.

Lastly, the first and the smallest items are swapped and the right side of the array size shrinks by one unit.

Let us look at the following animation:

![How selection sort works](https://upload.wikimedia.org/wikipedia/commons/9/94/Selection-Sort-Animation.gif)

_(Source: Wikipedia)_

Selection sort is an _in place_ sorting algorithm too, because it does not require any copy of the original array.

Given an array of N items, selection sort has a time complexity of O(N<sup>2</sup>) because, if we are working with an array that is already sorted in descending order, then at each step we will need to scan the right side of the array until we reach its end and pick the last item.

Here follows the Java implementation of selection sort: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/arrays/selectionSort/SelectionSorter.java).
