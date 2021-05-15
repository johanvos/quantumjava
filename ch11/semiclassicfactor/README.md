# Classic factorization

This sample shows a simple, naive, classic approach to the integer factorization problem.
We introduce the problem space, but there is nothing quantum-related in this chapter.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome looks like this:

```
We need to factor 8671
Pick a random number a, a < N: 7455
calculate gcd(a, N):1
period of f = 924
Factored 8671 in 1 and 8671

```

# What it is doing

This sample translate the factorization problem to a problem where we have to find the periodicity 
of a function. Everything is implemented in a classic way, but it lays out the framework for the
quantum implementation in the next sample.

You can learn more about this sample in Chapter 11 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9).  It is discussed in 11.6: "The rationale behind Shor's algorithm".
