# Mary had a little Qubit 

This application demonstrates the concepts explained in <a href="https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9">Quantum Computing for Java Developers</a>.
It leverages the Java-based <a href="https://github.com/redfx-quantum/strange">Strange</a> Quantum Simulator and toolkit.

This game builds on the original "Mary had a little Lambda" game, created by Stephen Chin, which explains the concepts of 
Lambdas and Streams to Java developers.

We extended the game to "Mary had a little Qubit", as it can be useful to explain the concept of quantum computing,
qubits and gates to Java developers.

## Code
The code for this game is in the `src` directory, and can be built and executed using maven.

## Play the game

Start the application as follows:
`mvn clean javafx:run`

You'll see Mary in a landscape with some objects that are hidden quantum
gates.
![](https://github.com/johanvos/quantumjava/blob/master/ch05/maryqubit/docs/marylandscape.png)

Navigate using the arrows. When Mary visits the barn, a new lamb (which is a qubit) is added.
For each lamb or qubit, a wire is added in the transparent top-section of the screen.
When a lamb enters a gate, the corresponding gate symbol is added to the wire. On the right-hand side of the
wire, the probability of measuring a "1" is shown.

For example, when Mary visited the barn twice and walks around with the lambs, you can see something like this:

<img src="https://github.com/johanvos/quantumjava/blob/master/ch05/maryqubit/docs/mary2lambs.png"/>

We have 2 lambs, or qubits, that are both in the "off" state. They are shown with a white color.

Gates are activated using keys. An activated gate will be glowing. The `ChickenCoop`, for example, can act
as a Pauli-X (or quantum NOT) gate.

By default, the chickencoop looks like this:

<img src="https://github.com/johanvos/quantumjava/blob/master/ch05/maryqubit/docs/paulix-inactive.png" width="100px"/>

But if you press the `X` key, the chickencoop becomes a PauliX gate, and it glows:

<img src="https://github.com/johanvos/quantumjava/blob/master/ch05/maryqubit/docs/paulix-active.png" width="100px"/>

Similarly, the `H` key will transform the `Nest` object into a Hadamard gate, and back.

If you active the X gate and have the first lamb entering it, followed by activating the second gate when the second
lamb enters it, your screen might look like this:

<img src="https://github.com/johanvos/quantumjava/blob/master/ch05/maryqubit/docs/mary2lambgates.png"/>

The `Rainbow` is a special one, as it can be transformed into a `C-NOT` gate by pressing the `C` key.
This now requires 2 qubits to visit the gate. 

If you understand how this works, a next exercise would be to create a 3 qubit circuit, with the first 2 qubits in 
an entangled state, and the third one in a superposition state. If you do it correct, you should see an image similar to
this one:

<img src="https://github.com/johanvos/quantumjava/blob/master/ch05/maryqubit/docs/mary3gates.png"/>

If Mary visits the `Church`, all qubits are measured, and the total number is added to the sign in the church, e.g.
like this:

<img src="https://github.com/johanvos/quantumjava/blob/master/ch05/maryqubit/docs/marychurch.png"/>


If you want to learn more about Quantum Computing for Java, have a look at 
<a href="https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9">Quantum Computing for Java Developers 
<img src="https://images.manning.com/720/960/resize/book/8/d11e671-5fe8-41b4-85fc-4fbcf5e4ce99/Vos-QCD-MEAP-HI.png" width=200px/>
</a>

