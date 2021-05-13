# QKD in Java
This sample demonstrate the BB84 algorithm in Java.

# Running the sample
Execute with

```
mvn clean javafx:run
```

This should show output similar to

```
Same bases used. Alice sent 1 and Bob received 1
Same bases used. Alice sent 0 and Bob received 0
Same bases used. Alice sent 1 and Bob received 1
Different bases used, ignore values false and true
Same bases used. Alice sent 1 and Bob received 1
Different bases used, ignore values false and true
Same bases used. Alice sent 1 and Bob received 1
Different bases used, ignore values true and true
Secret key = 10111
JavaFX Platform initialized
```

and a UI similar to this:

![bb84](/resources/ch8-bb84.png)

# What it is doing

This code shows the BB84 algorithm for Quantum Key Distribution, implemented in Java.
You can learn more about this sample in Chapter 8 of [Quantum Computing for Java Developers](https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9). It is discussed in 8.6: "QKD in Java"
