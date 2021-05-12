# Classic networking

This app contains a very basic, low-level classic networking sample.

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks like this:

```
[Receiver] Starting to listen for incoming data at port 9753
[Sender] Create a connection to port 9753
[Sender] Write a byte: 8
[Sender] Wrote a byte: 8
[Receiver] Got a byte 8
```

# What it is doing

This sample is simply sending a byte over a socket, using the Java Socket API.
The java Socket and ServerSocket API's are rather low-level, and developers typically use libraries that
are built on top of these API's. 
However, the low-level classic Java API's are a nice introduction when we want to talk about similar
low-level Quantum API's that can be the basis for more complex Quantum network algorithms.

You can learn more about this sample in Chapter 6 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 6.2.1: "Classical networking in Java"
