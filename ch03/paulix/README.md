# PauliX

This sample shows how to create a very basic quantum circuit
with Strange. It consists of a single qubit, and a single
gate (the Pauli-X gate) that is applied to that qubit.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome looks like this:

```
Value = 1
```

# What it is doing

This sample first create a quantum `Program` and a `Step`.
A `X` Gate (short for Pauli-X gate) is added to the step,
and the step is added to the program.
The program is executed on the built-in quantum simulator,
and the result is obtained. The value of the single qubit
is measured and printed. Since initially, the value of the
qubit was `0`, the resulting value will guaranteed be `1`.

You can learn more about this sample in Chapter 3 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9)
