# CNot gate

This sample introduces the CNot gate. We only
apply it to basic quantum states.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks like this:

```
IN = |00>	OUT= |00>
IN = |01>	OUT= |11>
IN = |10>	OUT= |10>
IN = |11>	OUT= |01>

```
Apart from this, the quantum circuit and the probability distribution 
for the 4 scenario's will also be rendered. For example, the
case of |01> as input is shown below:
![CNot](/resources/ch5-cnot01.png)


# What it is doing

This sample shows how the CNot gate acts in a circuit containing
2 qubits. We don't use superposition (yet) in this sample, but we
examine the behavior for the 4 possible input scenario's of the
qubits,

You can learn more about this sample in Chapter 5 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 5.5: "A Gate representation for Quantum Entanglement"
