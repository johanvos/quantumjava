# Quantum Oracle

This code introduces a quantum oracle.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The result of this application is similar to the output below
```
cnt [0]: 542
cnt [1]: 0
cnt [2]: 0
cnt [3]: 458
```

Also, the following quantum circuit and probability chart are shown:

![oracle](/resources/ch9-oracle.png)


# What it is doing

This sample shows that an initial state of 25% chance to measure 0 results in a similar state 
after applying 2 NOT gates.

You can learn more about this sample in Chapter 9 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 9.4: "Defining an Oracle".
