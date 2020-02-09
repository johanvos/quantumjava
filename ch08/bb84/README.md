This sample demonstrate the BB84 algorithm in Java.
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

<img src="https://github.com/johanvos/quantumjava/blob/master/ch08/bb84/bb84.png"/>

More sample info at the root of this project.
This sample uses the <a href="https://github.com/gluonhq/strange">Strange</a>
Quantum Simulator.
It is discussed in <a href="https://www.manning.com/books/quantum-computing-for-java-developers?a_aid=quantumjava&a_bid=e5166ab9">Quantum Computing for Java Developers</a>.
