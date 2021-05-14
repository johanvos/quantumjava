# Classic search

This code shows a very simple classic search application

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks similar to this

```
Hello, classical search
Got result after 3 tries
Result of complex search = Albert
Got result after 2 tries
Result of complex search = Albert
Got result after 5 tries
Result of complex search = Albert
Got result after 7 tries
Result of complex search = Albert
Got result after 7 tries
Result of complex search = Albert
Got result after 7 tries
Result of complex search = Albert
Got result after 4 tries
Result of complex search = Albert
Got result after 6 tries
Result of complex search = Albert
Got result after 4 tries
Result of complex search = Albert
Got result after 2 tries
Result of complex search = Albert
Got result after 6 tries
Result of function Search = Albert
Got result after 5 tries
Result of function Search = Albert
Got result after 3 tries
Result of function Search = Albert
Got result after 7 tries
Result of function Search = Albert
Got result after 5 tries
Result of function Search = Albert
Got result after 7 tries
Result of function Search = Albert
Got result after 7 tries
Result of function Search = Albert
Got result after 8 tries
Result of function Search = Albert
Got result after 8 tries
Result of function Search = Albert
Got result after 6 tries
Result of function Search = Albert
```

# What it is doing

In this 100% classic sample, we are searching for an element in a list that satisfies a specific
condition. If you are lucky, you need a single try only, but it may also be required to try 8 times, in case the
list contains 8 elements (and at most one of them satisfies the condition).

You can learn more about this sample in Chapter 10 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 10.2: "Classic search problems"
