			Homework 6 - Poker 3 Read Me
______________________________________________________________________________________
This assumes you have JavaFX installed and set to a system path. This can be done
from the command line by:

	> set PATH_TO_FX="/path/to/javafx/lib"

After the $PATH_TO_FX (Unix) (%PATH_TO_FX% in Windows) variable is set, 
you can navigate to the project directory where PokerMain.java is present
and run the following two commands from the terminal or command line:

	> javac --module-path $PATH_TO_FX --add-modules javafx.controls PokerMain.java
	> java --module-path $PATH_TO_FX --add-modules javafx.controls PokerMain

When you'd like to close the application, you can click the "X" at the upper
right of the window, or you can input CTRL + C in the command line.
