# Grover

In this sample, Grover's search algorithm is explained

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks similar to this

```
dim = 6 hence N = 64
 n = 6, steps = 6.283185307179586
results after step 1: 0.1348266452550888
results after step 2: 0.34389519691467285
results after step 3: 0.5913801789283752
results after step 4: 0.8163770437240601
results after step 5: 0.9635154604911804
results after step 6: 0.9965861439704895


JavaFX Platform initialized
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@3b084709]
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@1efed156]
Create BO, s = Step with gates [org.redfx.strange.gate.ProbabilitiesGate@72b6cbcc]
Prob for step 3
 = 0.01373291201889515
n = 6 and N = 64, dY = 6.625
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@3b084709]
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@1efed156]
Create BO, s = Step with gates [org.redfx.strange.gate.ProbabilitiesGate@30b8a058]
Prob for step 6
 = 0.010414360091090202
n = 6 and N = 64, dY = 6.625
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@3b084709]
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@1efed156]
Create BO, s = Step with gates [org.redfx.strange.gate.ProbabilitiesGate@7c29daf3]
Prob for step 9
 = 0.006486030761152506
n = 6 and N = 64, dY = 6.625
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@3b084709]
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@1efed156]
Create BO, s = Step with gates [org.redfx.strange.gate.ProbabilitiesGate@6c49835d]
Prob for step 12
 = 0.0029146496672183275
n = 6 and N = 64, dY = 6.625
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@3b084709]
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@1efed156]
Create BO, s = Step with gates [org.redfx.strange.gate.ProbabilitiesGate@7d0587f1]
Prob for step 15
 = 5.791197181679308E-4
n = 6 and N = 64, dY = 6.625
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@3b084709]
Create BO, s = Step with gates [org.redfx.strange.gate.Oracle@1efed156]
Create BO, s = Step with gates [org.redfx.strange.gate.ProbabilitiesGate@4f063c0a]
Prob for step 18
 = 5.419565422926098E-5
n = 6 and N = 64, dY = 6.625

```

The quantum circuit for Grover's algorithm is shown as well:

![grover](/resources/ch10-grover.png)



# What it is doing

The code shows the quantum circuit for Grover's algorithm, together with the probability vector. This vector
gradually moves towards a vector where a single element is getting closer to 100%.

You can learn more about this sample in Chapter 10 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 10.5: "The algorithm behind Grover's search".
