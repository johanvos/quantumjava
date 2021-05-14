# Grover step by step

In this sample, Grover's search algorithm is explained with a very limited number of qubits

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks similar to this

```
dim = 2 hence N = 4
 n = 2, steps = 1.5707963267948966
results after step 1: 0.2499999701976776


JavaFX Platform initialized
Create BO, s = Step with gates [org.redfx.strange.gate.ProbabilitiesGate@3b084709]
Prob for step 1
 = 0.2499999701976776
n = 2 and N = 4, dY = 40.0
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@3224f60b]
Create BO, s = Step with gates [org.redfx.strange.gate.ProbabilitiesGate@6737fd8f]
Prob for step 3
 = 0.2499999701976776
n = 2 and N = 4, dY = 40.0
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@72b6cbcc]
Create BO, s = Step with gates [org.redfx.strange.gate.ProbabilitiesGate@68bbe345]
Prob for step 5
 = 8.881784197001252E-16
n = 2 and N = 4, dY = 40.0

```

The quantum circuit for Grover's algorithm is shown as well:

![grover](/resources/ch10-stepbystepgrover.png)



# What it is doing

The code shows the quantum circuit for Grover's algorithm, for an unrealistic small number of elements. This 
makes it easier though to follow the algorithm and calculate the numbers.

You can learn more about this sample in Chapter 10 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 10.5: "The algorithm behind Grover's search".
