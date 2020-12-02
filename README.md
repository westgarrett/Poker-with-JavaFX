# Poker-with-JavaFX

[![JavaFX Version][javafx-image]][javafx-url]
[![Java JDK Version][java-image]][java-url]

Plays a five card draw poker game. The program contains win conditions based on the hand dealt. Built with a focus on Java action events and animation event loops.

## Installation

1. Download [Java JDK 15.0.1](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html) and [OpenFX 15.0.1](https://gluonhq.com/products/javafx/) for your platform.

2. Set the ```JAVA_HOME``` environment variable to the Java JDK installation directory, and the ```PATH_TO_FX``` environment variable to the JavaFX installation directory.

3. Navigate to the project directory where PokerMain.java is present and run ```javac``` and ```java```.

OS X & Linux:

Setting environment variables:

```
setx -m JAVA_HOME="/path/to/Java/jdkXX.XX.XX"
```

```
setx -m PATH_TO_FX="/path/to/javafx/lib"
```

Running the program:

```
javac --module-path $PATH_TO_FX --add-modules javafx.controls PokerMain.java
```

```
java --module-path $PATH_TO_FX --add-modules javafx.controls PokerMain
```

Windows:

Set the ```JAVA_HOME``` environment variable to the Java JDK installation directory, and the ```PATH_TO_FX``` environment variable to the JavaFX installation directory.

Setting environment variables:

```
setx -m JAVA_HOME="/path/to/Java/jdkXX.XX.XX"
```

```
setx -m PATH_TO_FX="/path/to/javafx/lib"
```

Running the program:

```
javac --module-path %PATH_TO_FX% --add-modules javafx.controls PokerMain.java
```

```
java --module-path %PATH_TO_FX% --add-modules javafx.controls PokerMain
```

## Info

Garrett West - [west.garrett@utexas.edu](west.garrett@utexas.edu)

[https://https://github.com/westgarrett/Poker-with-JavaFX](https://github.com/westgarrett/)

<!-- Markdown link & img dfn's -->
[javafx-image]: https://img.shields.io/badge/JavaFX-v15.0.1-blue
[javafx-url]: https://gluonhq.com/products/javafx/
[java-image]: https://img.shields.io/badge/Java%20JDK-v15.0.1-red
[java-url]: https://www.oracle.com/java/technologies/javase-jdk15-downloads.html
