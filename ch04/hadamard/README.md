# Hadamard gate

This sample introduces the Hadamard gate. When applied to 
a qubit in a basic state, the qubit will enter a pure state,
in which there is an equal chance to measure 1 or to measure 0.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks like this:

```
==================================================
Single run of a Quantum Circuit with Hadamard Gate
Value = 0
JavaFX Platform initialized
==================================================



==================================================
1000 runs of a Quantum Circuit with Hadamard Gate
Applied Hadamard circuit 1000 times, got 501 times 0 and 499 times 1.
==================================================

```
Apart from this, the quantum circuit will also be rendered,
which results in the following image:
![Hadamard gate](/resources/ch4-hadamard.png)


# What it is doing

This sample shows the impact of a Hadamard gate in a quantum circuit.

You can learn more about this sample in Chapter 4 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9)
