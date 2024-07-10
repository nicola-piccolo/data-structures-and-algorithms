# Matrix multiplication

## Quick recap about matrixes

A two-dimensional matrix, or just *“matrix,”* is a data structure where its entries can be directly accessed using a couple of integer indexes: the first index represents the row index, and the second index is the column index.

In simple words, a matrix is like a table of data and can be implemented as an array of arrays; here follows a Java example of a matrix:

    double[][] inputMatrix = { {1,2,3}, {4,5,6} };
    
Matrixes share the same advantages of arrays, for example constant time when accessing any of its items, and same drawbacks too, like fixed capacity.

## Matrix multiplication algorithm

Let us remember how two matrixes are multiplied and implement the algorithm following the multiplication definition.

The first matrix will have N rows and M columns, while the second matrix will have M rows and P columns: as we can notice, the number of columns in the first matrix is matching the number of rows on the second matrix.

The reason of such a constraint is because each element of the product matrix comes from a multiplication item-by-item of a row from the first matrix with a column from the second matrix:

![How rows and columns are multiplied between matrixes](https://upload.wikimedia.org/wikipedia/commons/1/11/Matrix_multiplication_diagram.svg)

*(Source: Wikipedia)*

The algorithm that applies the definition of matrix multiplication consists of three nested loops:

-	the outermost loop scans the first matrix rows,


-	the central loop scans the second matrix columns,


-	the innermost loop performs the multiplication item-by-item of the row provided by the outermost loop with the column given by the central loop.


This simple algorithm has a time complexity of O(NMP), which is basically a cubic complexity because, if N=M=P, then complexity becomes O(N<sup>3</sup>).

Rather than working directly with a Java array of arrays, I wrapped it into a class with a more readable API.

The wrapper class is also immutable, because I wanted to experiment an approach that leads to a simpler design, thread safety, and easier testing.

Here is the class: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/matrixes/ImmutableMatrix.java)

The Java implementation for the multiplication algorithm follows: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/matrixes/multiplier/MatrixMultiplier.java)

## Clean code tip #4: Classes (and interfaces) with vague names

**Examples**

    class Sender {}
    class Writer {}
    interface Manager {}


**Why is it bad?**

Code that uses a class with a vague name is harder to understand because the class intent is unclear: what is *manager.handle()* supposed to do?

A vague name can lead to definitions of classes with the same name in different packages or folders: a developer that is not familiar with the codebase will have a hard time trying to figure out which class to use.

If a class has a vague name and intent, developers could be tempted to add new methods to it because *“it just looked OK,”* with no solid reason.


**How to fix this?**

We should try to find out a more precise name for each class: try harder to look to the class methods and figure out what the class or interface intent is.

*Be careful!* If changing a vague name is difficult because the class is doing too many things, break it down into smaller classes that handle a single responsibility. 

After this refactoring, it will be easier to find an accurate name for each class.


**Fixed examples**

    class NotificationEmailSender {}
    class PdfFileWriter {}
    interface RemoteConnectionApi {}

