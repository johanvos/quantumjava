# Quantum search

In this sample, Grover's search algorithm is leveraged.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks similar to this

```
 n = 3, steps = 2.221441469079183
winner = 2 with prob 0.9453116655349731
Result of function Search = Albert
 n = 3, steps = 2.221441469079183
Result of function Search = Albert
winner = 3 with prob 0.9453116655349731
 n = 3, steps = 2.221441469079183
winner = 0 with prob 0.9453116655349731
Result of function Search = Albert
 n = 3, steps = 2.221441469079183
winner = 6 with prob 0.9453116655349731
Result of function Search = Albert
 n = 3, steps = 2.221441469079183
winner = 3 with prob 0.9453116655349731
Result of function Search = Albert
 n = 3, steps = 2.221441469079183
winner = 3 with prob 0.9453116655349731
Result of function Search = Albert
 n = 3, steps = 2.221441469079183
winner = 5 with prob 0.9453116655349731
Result of function Search = Albert
 n = 3, steps = 2.221441469079183
winner = 0 with prob 0.9453116655349731
Result of function Search = Albert
 n = 3, steps = 2.221441469079183
winner = 5 with prob 0.9453116655349731
Result of function Search = Albert
 n = 3, steps = 2.221441469079183
winner = 7 with prob 0.9453116655349731
Result of function Search = Albert
```

# What it is doing

The code searches for an element in a list that satisfies a specific condition. We use the high-level API of
Strange to invoke the quantum search algorithm.

You can learn more about this sample in Chapter 10 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 10.3: "Quantum search: Using Grover's search algorithm".
