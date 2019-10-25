Start the application as follows:
`mvn clean javafx:run`

You'll see Mary in a landscape with some objects that are hidden quantum
gates.
![](https://github.com/johanvos/quantumjava/blob/master/ch05/maryqubit/docs/marylandscape.png)

Navigate using the arrows. When Mary visits the barn, a new lamb (which is a qubit) is added.
For each lamb or qubit, a wire is added in the transparent top-section of the screen.
When a lamb enters a gate, the corresponding gate symbol is added to the wire. On the right-hand side of the
wire, the probability of measuring a "1" is shown.

Gates are activated using keys. An activated gate will be glowing. The `ChickenCoop`, for example, can act
as a Pauli-X or NOT gate.

If you want to learn more about Quantum Computing for Java, have a look at 
<a href="https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9">Quantum Computing for Java Developers</a>

