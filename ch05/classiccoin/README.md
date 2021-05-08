# Classic coins example

This sample shows the possible combination when tossing
two classic coins. There are no quantum effects, and
the coins are independent from each other.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks like this:

```
=================================
We did 1000 experiments.
0 0 occured 243 times.
0 1 occured 224 times.
1 0 occured 269 times.
1 1 occured 264 times.
=================================
```
Apart from this, the probability distribution will also be rendered,
which results in the following image:
![Classic Coins](/resources/ch5-classic.png)


# What it is doing

This sample shows how classic coins result in equal probabilities.

You can learn more about this sample in Chapter 5 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 5.2: "Independent probabilities, the classic way"
