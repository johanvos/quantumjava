# Grover quantum oracle

In this sample, Grover's search algorithm is explained

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks similar to this

```
Use 00 as input
measured = 0, 0
JavaFX Platform initialized

Use 01 as input
, measured = 1, 0

Use 10 as input
, measured = 0, 1

Use 11 as input
, measured = 1, 1
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@25bbe1b6]
Create BO, s = Step with gates [org.redfx.strange.gate.ProbabilitiesGate@57536d79]
Prob for step 1
 = 0.0
n = 2 and N = 4, dY = 40.0

```

The quantum circuit for the oracle in Grover's algorithm is shown as well:

![grover](/resources/ch10-groveroracle.png)



# What it is doing

The code shows the quantum oracle that corresponds with a classic function.

You can learn more about this sample in Chapter 10 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 10.5.3: "The Quantum Oracle".
