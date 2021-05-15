# Classic factorization

This sample shows a simple, naive, classic approach to the integer factorization problem.
We introduce the problem space, but there is nothing quantum-related in this chapter.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome looks like this:

```
Factored 7972 in 2 and 3986
```

# What it is doing

This code checks all numbers up to the square root of a given target number, trying to divide the target.
It is a non-performant approach and many optimizations exists, but they are not in scope now.

You can learn more about this sample in Chapter 11 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9).  It is discussed in 11.5: "Problem description".
