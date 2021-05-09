# Bell state

This sample introduces entanglement.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

There is no output on the console, but the quantum circuit
and probability distribution is shown:

![Bell](/resources/ch5-bell.png)


# What it is doing

This sample introduces entanglement. A Bell state is created
by applying a Hadamard gate to a qubit, and a CNot gate to
this qubit and another qubit. As a result, both qubits are now
entangled. Their measurements are not independent from each 
other anymore. Only the |00> or the |11> outcome is possible.

You can learn more about this sample in Chapter 5 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 5.6: "Creating a Bell state: dependent probabilities"
