# Quantum factorization

In this sample, we use a quantum implementatin for Shor's algorithm.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome looks like this:

```
We need to factor 15
Pick a random number a, a < N: 8
calculate gcd(a, N):1
Calculate periodicity using org.redfx.strange.local.SimpleQuantumExecutionEnvironment@31ef45e3
period of f = 4
QFactored 15 in 3 and 5

```

# What it is doing

This code uses the low-level Strange API's to create a quantum circuit that allows to find the periodicity of
a function, using Quantum gates. The previous sample showed how period finding corresponds to integer factorization.
The quantum part for finding the periodicity is running on the Strange quantum simulator, but the same code should
also be able to run on real quantum hardware.

You can learn more about this sample in Chapter 11 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9).  It is discussed in 11.7: "The quantum-based implementation".
