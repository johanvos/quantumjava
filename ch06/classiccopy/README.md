# Classic copy

This samples shows a verbose way for copying information in a classic computer.
While this is evident for most software developers, this approach does not
work in quantum computing

# Running the sample

To run this application, simply run
`mvn clean javafx:run`

The expected outcome on the console looks like this:

```
Source: true and copy : true
Source: false and copy : false

```

# What it is doing

The code will check a source bit (represented by a java Boolean object) and return a new object
that holds the same value. Both the old and the new object still exist and can be worked with.
We show this extremely simple operation, because we want to highlight something that is not
possible in quantum computing. In the next samples, we will use this to our advantage.

You can learn more about this sample in Chapter 6 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 6.2.2: "No cloning theorem"
