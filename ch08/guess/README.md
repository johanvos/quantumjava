# Guess

This sample describe the possible scenario's that ultimately lead to the BB84 algorithm

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks similar to this

```
Alice sent 0 and Bob received 0
Alice sent 0 and Bob received 1
Alice sent 0 and Bob received 0
Alice sent 0 and Bob received 0
Alice sent 0 and Bob received 1
Alice sent 0 and Bob received 1
Alice sent 0 and Bob received 1
Alice sent 0 and Bob received 1

```

The circuit that is used to generate this is shown below:

![guess](/resources/ch8-guess.png)

# What it is doing

This sample shows the different scenario's that can happen: Alice can apply a Hadamard gate, but
she might decide not to do that. Hence, the eavesdropper doesn't know if she should apply a 
Hadamard gate or not.

You can learn more about this sample in Chapter 8 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 8.5.1: "Confusing Eve"
