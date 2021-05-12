# Quantum teleportation

This sample introduces quantum teleportation, where a qubit is sent from Alice
to Bob

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

There is no relevant output in the console, but the following UI will be shown:
![Teleport UI](/resources/ch6-teleport.png)


# What it is doing

In this sample, we send a qubit from Alice to Bob. 
Alice holds qubits q[0] and q[1] while bob has q[2]. As you can see from the circuit, q[1] and q[2] are entangled.
The original qubit is at q[0] and the result will be in
q[2]. From the circuit and the probability distribution, you can see that there is no chance that the measured value of q[2]
will be `1`. 
You can modify the sample by adding an `X` Gate to q[0] and you will see that in the end, q[2] will guaranteed
be measured as `1`.
The value from Alice's qubit is thus "teleported" to Bob.

You can learn more about this sample in Chapter 6 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 6.4: "Quantum teleportation"
