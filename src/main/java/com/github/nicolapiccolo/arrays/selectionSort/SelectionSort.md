# Selection sort

## Selection sort algorithm

In this case too, we want to sort an array of integer values in ascending order. 

Similarly to the approach taken for the insertion sort, the key idea is to imagine our array split in two parts: the left side is already sorted, and the right side is unsorted.

The algorithm will next search for the smallest item on right side of our array.

Lastly, the first and the smallest items are swapped and the right side of the array size shrinks by one unit.

Let us look at the following animation:

![How selection sort works](https://upload.wikimedia.org/wikipedia/commons/9/94/Selection-Sort-Animation.gif)

*(Source: Wikipedia)*

Selection sort is an *in place* sorting algorithm too, because it does not require any copy of the original array.

Given an array of N items, selection sort has a time complexity of O(N<sup>2</sup>) because, if we are working with an array that is already sorted in descending order, then at each step we will need to scan the right side of the array until we reach its end and pick the last item.

Here follows the Java implementation of selection sort: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/src/main/java/com/github/nicolapiccolo/arrays/selectionSort/SelectionSorter.java).

## Clean code tip #2: Functions with obscure names

**Examples**

    save64(val);
    printObj(list);
    finish(conn);


**Why is it bad?**

A function with a terrible name makes it hard to understand its purpose. 

We must then inspect its code and eventually we will figure out its goal: isn’t that a waste of time? 

Accurate function names can improve overall readability of our code and reduce the time needed for bug fixes or change requests.


**How to fix this?**

Of course, the fix consists in choosing new names that clearly state the goal of each function, without ambiguities or inaccuracies.

Sooner or later, we will find a function that is difficult to name because it does multiple things at once: in this scenario the most appropriate action is to split the original functions into smaller ones that do exactly only one thing. 

Naming them will then be simple.

**Fixed examples**

    encodeAndSaveBinaryFile(file);
    toString(shoppingList);
    closeAndCleanup(connection);
