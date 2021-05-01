# HelloStrange

This sample contains the HelloWorld application for Strange.
It uses the Strange high-level API to obtain 10000 random bits.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome looks similar to this:

```Using high-level Strange API to generate random bits
----------------------------------------------------
Generate one random bit, which can be 0 or 1. Result = 1
Generated 10000 random bits, 4961 of them were 0, and 5039 were 1.
```
Note that the actual numbers will most likely be different, but the amount of `0` and `1` measurements should be more or less similar.

# What it is doing

The main method will be executed, and this will invoke a high-level method of the Strange API's:
```
int randomBit = Classic.randomBit();
```
The `Classic` class is imported from `org.redfx.strange.algorithm.Classic`
The `randomBit()` method uses a quantum algorithm, implemented by the Strange low-level API, to retrieve a random value (either `0` or `1`).

You can learn more about this sample in Chapter 2 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9)
