# Quantum coins example

This sample shows the possible combination when tossing
two "quantum coins". The
the coins are independent from each other.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks like this:

```
=======================================
We did 1000 experiments.
[AB]: 0 0 occured 236 times.
[AB]: 0 1 occured 237 times.
[AB]: 1 0 occured 253 times.
[AB]: 1 1 occured 274 times.
=======================================

```
Apart from this, the quantum circuit and the probability distribution will also be rendered,
which results in the following image:
![Quantum Coins](/resources/ch5-quantumcoins.png)


# What it is doing

This sample shows how classic coins result in equal probabilities.

You can learn more about this sample in Chapter 5 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 5.2: "Independent probabilities, the classic way"
