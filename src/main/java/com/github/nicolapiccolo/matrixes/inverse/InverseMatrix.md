# Inverse of a square matrix

## Inverse of a square matrix

We already know what an “inverse” of a number is: given a non-zero number x, we say that y is the inverse of x if:

    x * y = 1

The number one is also called *“identity.”*

Something similar can be said also for matrixes.

We define the *“identity matrix”* as a matrix with N rows and N columns whose items are one on the “main diagonal,” and zero elsewhere:

![Identity matrix](https://web.stanford.edu/dept/radiology/cgi-bin/classes/pharmacokinetics/Math_bg/Matrix/IDmatrix.gif)

*(Source: stanford.edu)*

The inverse of a matrix A with N rows and N columns (also known as *“square”* matrix) is a matrix B such that the matrix multiplication of A and B is equal to the identity matrix:

    A * B = I
    
It is customary to use the notation A<sup>-1</sup> for inverse matrixes: A * A<sup>-1</sup> = I

## Matrix inverse calculation algorithm

There are several algorithms for calculating the inverse of a square matrix, and a relatively simple one is the **Gauss-Jordan algorithm**, also known as *Gaussian elimination*.

Given a matrix A with N rows and N columns, the algorithm starts with building a new matrix with N rows and 2*N columns, also called *“augmented matrix”*, by placing the matrix A on the left side and an identity matrix with N rows and N columns on the right:

![Augmented matrix](https://semath.info/img/la/inverse_elimination_ex3_01.webp)

*(Source: semath.info)*

Here is the Java class that builds the augmented matrix:[Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/matrixes/inverse/AugmentedMatrixBuilder.java)

The algorithm now will try to get the identity matrix I on the left part of this augmented matrix and, while doing this, the inverse matrix of A will appear on the right.

Now, the algorithm will perform several steps but the only allowed operations are the *"elementary row operations"*:

1.	swap rows,
2.	multiply or divide each element in a row by a constant,
3.	replace a row by adding or subtracting to it a multiple of another row.

Please note that these operations must be applied to the whole row, not just one half.

Since we have three nested loops that are processing all the N rows, the overall time complexity is going to be O(N<sup>3</sup>).

## Clean code tip #5: Public method names that expose their implementation details

**Examples in Typescript**

    getDateWithYYYYMMDDFormat(date: Date): string
    loadOrderDetailsFromDatabase(orderId: number): Promise<OrderDetails>
    orderArrayItemsByRankWithQuickSort(array: Item[]): void


**Why is it bad?**

When using a class, a developer should be interested only for the question “what can this class do for me?” and never rely on how the class will carry out its operations.

We want to be able to change internal details of a class without renaming its public methods. 

**Please note!** A public method name should always answer to a “what?” question (i.e. explain the meaning of a method) and never to a "how?" question.


**How to fix this?**

Omit the implementation details from the method name and rephrase it. 

The name should explain, without ambiguities, what part of the class features the method is implementing.


**Fixed examples**

    formatDate(dateToFormat: Date): string
    loadOrderDetailsBy(orderId: number): Promise<OrderDetails>
    orderByRank(items: Item[]): void