# Quantum adder with carry

This sample builds on top of the quantum adder, and extends it with
a carry bit

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks like this:

```
Adding 0 + 0 = 0
Adding 0 + 1 = 1
Adding 1 + 0 = 1
Adding 1 + 1 = 2
```

On top of that, the quantum circuit for adding 1 + 1 is shown:

![quantum carry adder](/resources/ch7-add2.png)

# What it is doing

This sample adds 2 qubits and uses a carry bit in case an overflow occurs

You can learn more about this sample in Chapter 7 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 7.5.2: "Quantum arithmetic with a carry bit"
